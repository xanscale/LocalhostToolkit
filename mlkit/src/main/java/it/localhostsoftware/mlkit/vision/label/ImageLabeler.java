package it.localhostsoftware.mlkit.vision.label;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;

import java.util.List;

import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer;

public class ImageLabeler extends AbstractImageAnalyzer<List<ImageLabel>> {
    @Override
    protected void process(ImageProxy imageProxy, OnSuccessListener<List<ImageLabel>> onSuccessListener) {
        @SuppressLint("UnsafeOptInUsageError") Image image = imageProxy.getImage();
        if (onSuccessListener != null && image != null)
            ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS).process(InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees()))
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(Throwable::printStackTrace)
                    .addOnCompleteListener(task -> imageProxy.close());
    }
}
