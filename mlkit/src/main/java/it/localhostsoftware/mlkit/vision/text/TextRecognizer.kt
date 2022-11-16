package it.localhostsoftware.mlkit.vision.text

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer

/**
To use this class add this dependency
implementation("com.google.mlkit:text-recognition:16.0.0-beta6")
 */
class TextRecognizer : AbstractImageAnalyzer<Text>() {
    override fun process(image: Image, rotationDegrees: Int) =
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(InputImage.fromMediaImage(image, rotationDegrees))
}