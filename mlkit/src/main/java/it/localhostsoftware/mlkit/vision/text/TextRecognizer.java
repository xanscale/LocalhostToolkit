package it.localhostsoftware.mlkit.vision.text;

import android.media.Image;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer;

public class TextRecognizer extends AbstractImageAnalyzer<Text> {
    @Override
    protected Task<Text> process(@NonNull Image image, int rotationDegrees) {
        return TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(InputImage.fromMediaImage(image, rotationDegrees));
    }
}
