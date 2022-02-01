package it.localhostsoftware.mlkit.vision.barcode;

import android.media.Image;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer;

public class BarcodeScanner extends AbstractImageAnalyzer<List<Barcode>> {
    @Override
    protected Task<List<Barcode>> process(@NonNull Image image, int rotationDegrees) {
        return BarcodeScanning.getClient().process(InputImage.fromMediaImage(image, rotationDegrees));
    }
}
