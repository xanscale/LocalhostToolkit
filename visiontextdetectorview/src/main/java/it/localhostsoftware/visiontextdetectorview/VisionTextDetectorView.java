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
import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

public class VisionTextDetectorView extends CameraView implements Runnable {
	private Handler handler;
	private Pattern pattern;
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

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
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

	public interface Callback {
		void onTextDetected(String text);
	}

	private static class VisionTextDetector extends AsyncTask<byte[], Void, String> {
		private WeakReference<VisionTextDetectorView> ref;

		VisionTextDetector(VisionTextDetectorView view) {
			this.ref = new WeakReference<>(view);
		}

		@Override protected String doInBackground(byte[]... data) {
			VisionTextDetectorView view = ref.get();
			if (view != null)
				try {
					Bitmap bitmap = BitmapFactory.decodeByteArray(data[0], 0, data[0].length);
					ByteArrayInputStream bais = new ByteArrayInputStream(data[0]);
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
						bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
					}
					FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
					FirebaseVisionText firebaseVisionText = Tasks.await(FirebaseVision.getInstance().getVisionTextDetector().detectInImage(image));
					for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks())
						for (FirebaseVisionText.Line line : block.getLines())
							for (FirebaseVisionText.Element element : line.getElements())
								if (view.pattern.matcher(element.getText()).matches())
									return element.getText();
				} catch (Exception e) {
					e.printStackTrace();
				}
			return null;
		}

		@Override protected void onPostExecute(String s) {
			VisionTextDetectorView view = ref.get();
			if (view != null) {
				if (s != null)
					view.callback.onTextDetected(s);
				view.detectInImage();
			}
		}
	}
}