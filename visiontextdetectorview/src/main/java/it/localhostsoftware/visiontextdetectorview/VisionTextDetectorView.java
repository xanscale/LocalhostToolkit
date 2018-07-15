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
		return bitmap;
	}

	public interface Callback {
		void onTextDetected(Set<String> values);
	}

	private static class VisionTextDetector extends AsyncTask<byte[], Void, Set<String>> {
		private WeakReference<VisionTextDetectorView> ref;

		VisionTextDetector(VisionTextDetectorView view) {
			this.ref = new WeakReference<>(view);
		}

		@Override protected Set<String> doInBackground(byte[]... data) {
			Set<String> matched = new HashSet<>();
			VisionTextDetectorView view = ref.get();
			if (view != null)
				try {
					FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(view.decodeByteArray(data[0]));
					FirebaseVisionText firebaseVisionText = Tasks.await(FirebaseVision.getInstance().getVisionTextDetector().detectInImage(image));
					for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks())
						for (FirebaseVisionText.Line line : block.getLines())
							for (FirebaseVisionText.Element element : line.getElements())
								for (Pattern pattern : view.patterns)
									if (pattern.matcher(element.getText()).matches()) {
										matched.add(element.getText());
										break;
									}
				} catch (Exception e) {
					e.printStackTrace();
				}
			return matched;
		}

		@Override protected void onPostExecute(Set<String> matched) {
			VisionTextDetectorView view = ref.get();
			if (view != null) {
				if (!matched.isEmpty())
					view.callback.onTextDetected(matched);
				view.detectInImage();
			}
		}
	}
}