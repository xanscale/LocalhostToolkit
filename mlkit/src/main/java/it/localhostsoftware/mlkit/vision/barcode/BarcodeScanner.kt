package it.localhostsoftware.mlkit.vision.barcode

import android.media.Image
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import it.localhostsoftware.mlkit.vision.AbstractImageAnalyzer

class BarcodeScanner : AbstractImageAnalyzer<List<Barcode>>() {
    override fun process(image: Image, rotationDegrees: Int) =
            BarcodeScanning.getClient().process(InputImage.fromMediaImage(image, rotationDegrees))
}