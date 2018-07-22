package it.localhostsoftware.visiontextdetectorview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.media.ExifInterface;
import android.util.AttributeSet;

import com.google.android.cameraview.CameraView;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class VisionTextDetectorView extends CameraView implements Runnable {
	private Handler handler;
	private List<Pattern> patterns;
	private Callback callback;
	private long delayMillis;
	private Rect r;

	public VisionTextDetectorView(Context context) {
		this(context, null);
	}

	public VisionTextDetectorView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public VisionTextDetectorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		handler = new Handler();
		addCallback(new CameraView.Callback() {
			@Override public void onCameraOpened(CameraView cameraView) {
				detectInImage();
			}

			@Override public void onPictureTaken(CameraView cameraView, byte[] data) {
				super.onPictureTaken(cameraView, data);
				new VisionTextDetector(VisionTextDetectorView.this).execute(data);
			}
		});
	}

	public void setPatterns(Pattern... patterns) {
		this.patterns = Arrays.asList(patterns);
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public void setDelayMillis(long delayMillis) {
		this.delayMillis = delayMillis;
	}

	public void setRect(Rect rect) {
		r = rect;
	}

	@Override public void run() {
		try {
			takePicture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void detectInImage() {
		handler.postDelayed(this, delayMillis);
	}

	@Override public void stop() {
		handler.removeCallbacks(this);
		super.stop();
	}

	private Bitmap decodeByteArray(byte[] data) throws IOException {
		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		int orientation = new ExifInterface(bais).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
		bais.close();
		if (orientation != ExifInterface.ORIENTATION_UNDEFINED && orientation != ExifInterface.ORIENTATION_NORMAL) {
			Matrix matrix = new Matrix();
			switch (orientation) {
				case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
					matrix.setScale(-1, 1);
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					matrix.setRotate(180);
					break;
				case ExifInterface.ORIENTATION_FLIP_VERTICAL:
					matrix.setRotate(180);
					matrix.postScale(-1, 1);
					break;
				case ExifInterface.ORIENTATION_TRANSPOSE:
					matrix.setRotate(90);
					matrix.postScale(-1, 1);
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					matrix.setRotate(90);
					break;
				case ExifInterface.ORIENTATION_TRANSVERSE:
					matrix.setRotate(-90);
					matrix.postScale(-1, 1);
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					matrix.setRotate(-90);
					break;
			}
			Bitmap temp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			bitmap.recycle();
			bitmap = temp;
		}
		if (r != null) {
			Bitmap temp = Bitmap.createBitmap(bitmap, r.getX(bitmap.getWidth()), r.getY(bitmap.getHeight()), r.getWidth(bitmap.getWidth()), r.getHeight(bitmap.getHeight()));
			bitmap.recycle();
			bitmap = temp;
		}
		return bitmap;
	}

	public interface Callback {
		void onTextDetected(VisionTextResult visionTextResult);
	}

	private static class VisionTextDetector extends AsyncTask<byte[], Void, VisionTextResult> {
		private WeakReference<VisionTextDetectorView> ref;

		VisionTextDetector(VisionTextDetectorView view) {
			this.ref = new WeakReference<>(view);
		}

		@Override protected VisionTextResult doInBackground(byte[]... data) {
			VisionTextDetectorView view = ref.get();
			if (view != null)
				try {
					VisionTextResult visionTextResult = new VisionTextResult(view.decodeByteArray(data[0]));
					FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(visionTextResult.bitmap);
					FirebaseVisionText firebaseVisionText = Tasks.await(FirebaseVision.getInstance().getVisionTextDetector().detectInImage(image));
					for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks())
						for (FirebaseVisionText.Line line : block.getLines())
							for (FirebaseVisionText.Element element : line.getElements())
								for (Pattern pattern : view.patterns)
									if (pattern.matcher(element.getText()).matches()) {
										visionTextResult.matched.add(element.getText());
										break;
									}
					return visionTextResult;
				} catch (Exception e) {
					e.printStackTrace();
				}
			return null;
		}

		@Override protected void onPostExecute(VisionTextResult visionTextResult) {
			VisionTextDetectorView view = ref.get();
			if (view != null) {
				if (visionTextResult != null && !visionTextResult.matched.isEmpty())
					view.callback.onTextDetected(visionTextResult);
				view.detectInImage();
			}
		}
	}

	public static class Rect {
		private double x, y, width, height;

		/**
		 * @param x      The x coordinate of the first pixel as perc.
		 * @param y      The y coordinate of the first pixel as perc.
		 * @param width  The number of pixels in each row as perc.
		 * @param height The number of rows as perc.
		 */
		public Rect(double x, double y, double width, double height) {
			if (x + width > 1)
				throw new RuntimeException("x + width > 1 are invalid!");
			if (y + height > 1)
				throw new RuntimeException("y + height > 1 are invalid!");
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		public int getX(int sourceWidth) {
			return (int) (x * sourceWidth);
		}

		public int getY(int sourceHeight) {
			return (int) (y * sourceHeight);
		}

		public int getWidth(int sourceWidth) {
			return (int) (width * sourceWidth);
		}

		public int getHeight(int sourceHeight) {
			return (int) (height * sourceHeight);
		}
	}

	public static class VisionTextResult {
		private Set<String> matched;
		private Bitmap bitmap;

		public VisionTextResult(Bitmap bitmap) {
			this.matched = new HashSet<>();
			this.bitmap = bitmap;
		}

		public Set<String> getMatched() {
			return matched;
		}

		public Bitmap getBitmap() {
			return bitmap;
		}
	}
}