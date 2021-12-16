package it.localhostsoftware.mlkit.vision.barcode;

import android.annotation.SuppressLint;
import android.media.Image;

import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer;

public class BarcodeScanner extends AbstractImageAnalyzer<List<Barcode>> {
    @Override
    protected void process(ImageProxy imageProxy, OnSuccessListener<List<Barcode>> onSuccessListener) {
        @SuppressLint("UnsafeOptInUsageError") Image image = imageProxy.getImage();
        if (onSuccessListener != null && image != null)
            BarcodeScanning.getClient().process(InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees()))
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener(Throwable::printStackTrace)
                    .addOnCompleteListener(task -> imageProxy.close());
    }
}
