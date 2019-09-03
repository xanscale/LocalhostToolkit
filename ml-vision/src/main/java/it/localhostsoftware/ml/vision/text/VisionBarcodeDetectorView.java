package it.localhostsoftware.ml.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;

import java.util.List;

public class VisionBarcodeDetectorView extends CameraView implements Runnable, BitmapCallback {
    private Handler handler;
    private OnSuccessListener<List<FirebaseVisionBarcode>> onSuccessListener;
    private long delayMillis;

    public VisionBarcodeDetectorView(@NonNull Context context) {
        super(context);
        init();
    }

    public VisionBarcodeDetectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        handler = new Handler();
        addCameraListener(new CameraListener() {
            @Override
            public void onCameraOpened(@NonNull CameraOptions options) {
                super.onCameraOpened(options);
                takePictureSnapshot();
            }

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                CameraUtils.decodeBitmap(result.getData(), VisionBarcodeDetectorView.this);
            }
        });
    }

    public void setOnSuccessListener(OnSuccessListener<List<FirebaseVisionBarcode>> onSuccessListener) {
        this.onSuccessListener = onSuccessListener;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    public void run() {
        super.takePictureSnapshot();
    }

    @Override
    public void close() {
        handler.removeCallbacks(this);
        super.close();
    }

    @Override
    public void onBitmapReady(Bitmap bitmap) {
        if (onSuccessListener != null && bitmap != null)
            FirebaseVision.getInstance().getVisionBarcodeDetector().detectInImage(FirebaseVisionImage.fromBitmap(bitmap)).addOnSuccessListener(onSuccessListener);
        handler.postDelayed(this, delayMillis);
    }
}