package it.localhostsoftware.ml.vision.text;

import android.Manifest;
import android.content.Context;
import android.media.Image;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.CameraView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.localhostsoftware.ml.vision.R;

public class VisionTextRecognizerView extends FrameLayout implements Runnable {
    private CameraView cameraView;
    private Handler handler;
    private OnSuccessListener<FirebaseVisionText> onSuccessListener;
    private long delayMillis;
    private ExecutorService cameraExecutor;

    public VisionTextRecognizerView(@NonNull Context context) {
        super(context);
        init();
    }

    public VisionTextRecognizerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VisionTextRecognizerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(21)
    public VisionTextRecognizerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.vision_barcode_detector_view, this);
        cameraView = findViewById(R.id.cameraView);
        delayMillis = 1000;
        handler = new Handler();
        cameraExecutor = Executors.newSingleThreadExecutor();
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    public void bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        cameraView.bindToLifecycle(lifecycleOwner);
        lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    handler.postDelayed(VisionTextRecognizerView.this, delayMillis);
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    handler.removeCallbacks(VisionTextRecognizerView.this);
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    cameraExecutor.shutdown();
                }
            }
        });
    }

    public void setOnSuccessListener(OnSuccessListener<FirebaseVisionText> onSuccessListener) {
        this.onSuccessListener = onSuccessListener;
    }

    public void setDelayMillis(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    @Override
    public void run() {
        cameraView.takePicture(cameraExecutor, new ImageCapture.OnImageCapturedCallback() {
            @Override
            @ExperimentalGetImage
            public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
                Image image = imageProxy.getImage();
                if (onSuccessListener != null && image != null) {
                    image.getPlanes()[0].getBuffer().rewind();
                    FirebaseVision.getInstance().getOnDeviceTextRecognizer()
                            .processImage(FirebaseVisionImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees() / 90))
                            .addOnSuccessListener(onSuccessListener);
                }
                handler.postDelayed(VisionTextRecognizerView.this, delayMillis);
                super.onCaptureSuccess(imageProxy);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                super.onError(exception);
                exception.printStackTrace();
            }
        });
    }
}