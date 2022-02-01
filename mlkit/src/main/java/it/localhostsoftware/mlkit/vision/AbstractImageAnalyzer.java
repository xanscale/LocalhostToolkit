package it.localhostsoftware.mlkit.vision;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executors;

public abstract class AbstractImageAnalyzer<TResult> {
    @RequiresPermission(Manifest.permission.CAMERA)
    public void bindToLifecycle(
            @NonNull Context context,
            @NonNull LifecycleOwner lifecycleOwner,
            @NonNull PreviewView previewView,
            @NonNull CameraSelector cameraSelector,
            @NonNull OnSuccessListener<TResult> onSuccessListener
    ) {
        ListenableFuture<ProcessCameraProvider> future = ProcessCameraProvider.getInstance(context);
        future.addListener(() -> {
            Preview preview = new Preview.Builder().build();
            preview.setSurfaceProvider(previewView.getSurfaceProvider());
            ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().build();
            imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(), imageProxy -> analyze(imageProxy, onSuccessListener));
            try {
                future.get().bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalysis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(context));
    }

    private void analyze(@NonNull ImageProxy imageProxy, @NonNull OnSuccessListener<TResult> onSuccessListener) {
        @SuppressLint("UnsafeOptInUsageError") Image image = imageProxy.getImage();
        if (image != null)
            process(image, imageProxy.getImageInfo().getRotationDegrees())
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(Throwable::printStackTrace)
                    .addOnCompleteListener(task -> imageProxy.close());
    }

    protected abstract Task<TResult> process(@NonNull Image image, int rotationDegrees);
}
