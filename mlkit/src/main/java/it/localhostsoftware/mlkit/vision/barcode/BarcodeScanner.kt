package it.localhostsoftware.mlkit.vision.barcode

import android.media.Image
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer

/**
To use this class add this dependency
implementation("com.google.mlkit:barcode-scanning:17.0.2")
 */
class BarcodeScanner : AbstractImageAnalyzer<List<Barcode>>() {
    override fun process(image: Image, rotationDegrees: Int) =
        BarcodeScanning.getClient().process(InputImage.fromMediaImage(image, rotationDegrees))
}