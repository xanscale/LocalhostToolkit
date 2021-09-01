package it.localhostsoftware.mlkit.vision.text;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer;

public class TextRecognizer extends AbstractImageAnalyzer<Text> {
    @Override
    protected void process(ImageProxy imageProxy, OnSuccessListener<Text> onSuccessListener) {
        @SuppressLint("UnsafeOptInUsageError") Image image = imageProxy.getImage();
        if (onSuccessListener != null && image != null)
            TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees()))
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(Throwable::printStackTrace)
                    .addOnCompleteListener(task -> imageProxy.close());
    }
}
