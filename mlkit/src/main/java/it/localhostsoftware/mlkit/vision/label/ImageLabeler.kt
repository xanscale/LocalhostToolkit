package it.localhostsoftware.mlkit.vision.label;

import android.media.Image;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabelerOptionsBase;
import com.google.mlkit.vision.label.ImageLabeling;

import java.util.List;

import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer;

public class ImageLabeler extends AbstractImageAnalyzer<List<ImageLabel>> {
    private final ImageLabelerOptionsBase options;

    public ImageLabeler(ImageLabelerOptionsBase options) {
        this.options = options;
    }

    @Override
    protected Task<List<ImageLabel>> process(@NonNull Image image, int rotationDegrees) {
        return ImageLabeling.getClient(options).process(InputImage.fromMediaImage(image, rotationDegrees));
    }
}
