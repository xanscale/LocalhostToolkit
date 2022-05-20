package it.localhostsoftware.mlkit.vision.label

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabelerOptionsBase
import com.google.mlkit.vision.label.ImageLabeling
import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer

class ImageLabeler(private val options: ImageLabelerOptionsBase) : AbstractImageAnalyzer<List<ImageLabel>>() {
    override fun process(image: Image, rotationDegrees: Int) =
            ImageLabeling.getClient(options).process(InputImage.fromMediaImage(image, rotationDegrees))
}