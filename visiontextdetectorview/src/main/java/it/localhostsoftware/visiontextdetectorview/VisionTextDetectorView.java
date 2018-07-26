package it.localhostsoftware.visiontextdetectorview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class VisionTextDetectorView extends CameraView implements Runnable, CameraUtils.BitmapCallback {
	private Handler handler;
	private List<Pattern> patterns;
	private Callback callback;
	private long delayMillis;
	private Rect r;

	public VisionTextDetectorView(@NonNull Context context) {
		super(context);
		init();
	}

	public VisionTextDetectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void init() {
		handler = new Handler();
		addCameraListener(new CameraListener() {
			@Override
			public void onPictureTaken(byte[] picture) {
				CameraUtils.decodeBitmap(picture, VisionTextDetectorView.this);
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
		captureSnapshot();
	}

	private void detectInCamera() {
		handler.postDelayed(this, delayMillis);
	}

	@Override public void start() {
		super.start();
		detectInCamera();
	}

	@Override public void stop() {
		handler.removeCallbacks(this);
		super.stop();
	}

	@Override public void onBitmapReady(Bitmap bitmap) {
		if (r != null) {
			Matrix matrix = new Matrix();
			matrix.postRotate(r.getDegrees());
			Bitmap temp = Bitmap.createBitmap(bitmap, r.getX(bitmap.getWidth()), r.getY(bitmap.getHeight()), r.getWidth(bitmap.getWidth()), r.getHeight(bitmap.getHeight()), matrix, false);
			bitmap.recycle();
			bitmap = temp;
		}
		try {
			final VisionTextResult visionTextResult = new VisionTextResult(bitmap);
			FirebaseVision.getInstance().getVisionTextDetector().detectInImage(FirebaseVisionImage.fromBitmap(bitmap)).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
				@Override public void onSuccess(FirebaseVisionText firebaseVisionText) {
					for (FirebaseVisionText.Block block : firebaseVisionText.getBlocks())
						for (FirebaseVisionText.Line line : block.getLines())
							for (FirebaseVisionText.Element element : line.getElements())
								for (Pattern pattern : patterns)
									if (pattern.matcher(element.getText()).matches()) {
										visionTextResult.matched.add(element.getText());
										break;
									}
					if (!visionTextResult.matched.isEmpty())
						callback.onTextDetected(visionTextResult);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		detectInCamera();
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
		public Rect(float x, float y, float width, float height, float degrees) {
			if (x + width > 1)
				throw new RuntimeException("x + width > 1 are invalid!");
			if (y + height > 1)
				throw new RuntimeException("y + height > 1 are invalid!");
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
}