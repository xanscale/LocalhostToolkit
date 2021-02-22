package it.localhostsoftware.mlkit.vision;

import android.Manifest;
import android.content.Context;

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
            imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(), imageProxy -> process(imageProxy, onSuccessListener));
            try {
                future.get().bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalysis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(context));
    }

    protected abstract void process(ImageProxy imageProxy, OnSuccessListener<TResult> onSuccessListener);
}
