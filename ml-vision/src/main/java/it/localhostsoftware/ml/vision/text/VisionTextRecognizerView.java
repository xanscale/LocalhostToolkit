/*package it.localhostsoftware.ml.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.util.AttributeSet;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VisionTextRecognizerView extends CameraView implements Runnable, BitmapCallback {
	private Handler handler;
	private List<Pattern> patterns;
	private Callback callback;
	private long delayMillis;
	private Rect r;

	public VisionTextRecognizerView(@NonNull Context context) {
		super(context);
		init();
	}

	public VisionTextRecognizerView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		handler = new Handler();
		addCameraListener(new CameraListener() {
			@Override public void onPictureTaken(@NonNull PictureResult result) {
				CameraUtils.decodeBitmap(result.getData(), VisionTextRecognizerView.this);
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
		takePictureSnapshot();
	}

	private void detectInCamera() {
		handler.postDelayed(this, delayMillis);
	}

	@Override public void open() {
		super.open();
		detectInCamera();
	}

	@Override public void close() {
		handler.removeCallbacks(this);
		super.close();
	}

	@Override public void onBitmapReady(Bitmap bitmap) {
		if (r != null) {
			Matrix matrix = new Matrix();
			matrix.postRotate(r.getDegrees());
			Bitmap temp = Bitmap.createBitmap(bitmap, r.getX(bitmap.getWidth()), r.getY(bitmap.getHeight()), r.getWidth(bitmap.getWidth()), r.getHeight(bitmap.getHeight()), matrix, false);
			bitmap.recycle();
			bitmap = temp;
		}
		processImage(bitmap);
		detectInCamera();
	}

	public void processImage(Bitmap bitmap) {
		try {
			final VisionTextResult visionTextResult = new VisionTextResult(bitmap);
			FirebaseVision.getInstance().getOnDeviceTextRecognizer().processImage(FirebaseVisionImage.fromBitmap(bitmap)).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
				@Override public void onSuccess(FirebaseVisionText firebaseVisionText) {
					for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks())
						for (FirebaseVisionText.Line line : block.getLines())
							for (FirebaseVisionText.Element element : line.getElements())
								for (Pattern pattern : patterns)
									if (pattern.matcher(element.getText()).matches()) {
										visionTextResult.matched.add(element.getText());
										break;
									}
					callback.onTextDetected(visionTextResult);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public interface Callback {
		void onTextDetected(VisionTextResult visionTextResult);
	}

	public static class Rect {
		private float x, y, width, height, degrees;

		/**
		 * @param x      The x coordinate of the first pixel as perc.
		 * @param y      The y coordinate of the first pixel as perc.
		 * @param width  The number of pixels in each row as perc.
		 * @param height The number of rows as perc.
		 */
	/*	public Rect(float x, float y, float width, float height, float degrees) {
			if (x + width > 1)
				throw new IllegalArgumentException("x + width > 1 are invalid!");
			if (y + height > 1)
				throw new IllegalArgumentException("y + height > 1 are invalid!");
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.degrees = degrees;
		}

		int getX(int sourceWidth) {
			return (int) (x * sourceWidth);
		}

		int getY(int sourceHeight) {
			return (int) (y * sourceHeight);
		}

		int getWidth(int sourceWidth) {
			return (int) (width * sourceWidth);
		}

		int getHeight(int sourceHeight) {
			return (int) (height * sourceHeight);
		}

		float getDegrees() {
			return degrees;
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
}*/