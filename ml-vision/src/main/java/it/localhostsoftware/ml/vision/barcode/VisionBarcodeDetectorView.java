package it.localhostsoftware.ml.vision.barcode;

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
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import it.localhostsoftware.ml.vision.text.R;

public class VisionBarcodeDetectorView extends FrameLayout implements Runnable {
    private CameraView cameraView;
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

    public VisionBarcodeDetectorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(21)
    public VisionBarcodeDetectorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.vision_barcode_detector_view, this);
        cameraView = findViewById(R.id.cameraView);
        delayMillis = 1000;
        handler = new Handler();
    }

    @RequiresPermission(Manifest.permission.CAMERA)
    public void bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        cameraView.bindToLifecycle(lifecycleOwner);
        lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_RESUME) {
                    handler.postDelayed(VisionBarcodeDetectorView.this, delayMillis);
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    handler.removeCallbacks(VisionBarcodeDetectorView.this);
                }
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
        cameraView.takePicture(new File(getContext().getCacheDir(), "picture"), ContextCompat.getMainExecutor(getContext()), new ImageCapture.OnImageSavedCallback(){
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                if (onSuccessListener != null && outputFileResults.getSavedUri() != null) {
                    try {
                        FirebaseVision.getInstance().getVisionBarcodeDetector().detectInImage(FirebaseVisionImage.fromFilePath(getContext(), outputFileResults.getSavedUri())).addOnSuccessListener(onSuccessListener);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                handler.postDelayed(VisionBarcodeDetectorView.this, delayMillis);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                exception.printStackTrace();
            }
        });
    }
}