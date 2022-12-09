package it.localhostsoftware.mlkit.vision

import android.Manifest
import android.content.Context
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.PreviewView
import androidx.core.util.Consumer
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.MoreExecutors
import com.google.mlkit.vision.interfaces.Detector
import kotlinx.coroutines.guava.await

/**
To use BarcodeScanner
implementation("com.google.mlkit:barcode-scanning:{latestVersion}")
BarcodeScanning.getClient().bindToLifecycle(...)

To use ImageLabeler
implementation("com.google.mlkit:image-labeling-custom:{latestVersion}")
ImageLabeling.getClient(...).bindToLifecycle(...)

To use TextRecognizer
implementation("com.google.mlkit:text-recognition:{latestVersion}")
TextRecognition.getClient().bindToLifecycle(...)
 */
@RequiresPermission(Manifest.permission.CAMERA)
suspend fun <DetectionResultT> Detector<DetectionResultT>.bindToLifecycle(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    cameraSelector: CameraSelector,
    enableScaleGestureDetector: Boolean = true,
    targetCoordinateSystem: Int = COORDINATE_SYSTEM_VIEW_REFERENCED,
    consumer: Consumer<Pair<DetectionResultT?, Throwable?>>
) {
    Preview.Builder().build().let { preview ->
        preview.setSurfaceProvider(previewView.surfaceProvider)
        ImageAnalysis.Builder().build().let { imageAnalysis ->
            imageAnalysis.setAnalyzer(MoreExecutors.directExecutor(), MlKitAnalyzer(listOf(this@bindToLifecycle), targetCoordinateSystem, MoreExecutors.directExecutor()) {
                consumer.accept(Pair(it.getValue(this@bindToLifecycle), it.getThrowable(this@bindToLifecycle)))
            })
            ProcessCameraProvider.getInstance(context).await().bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalysis).let {
                if (enableScaleGestureDetector) setScaleGestureDetector(context, it, previewView)
            }
        }
    }
}

private fun setScaleGestureDetector(context: Context, camera: Camera, previewView: PreviewView) {
    ScaleGestureDetector(context, object : SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            camera.cameraControl.setZoomRatio(detector.scaleFactor * (camera.cameraInfo.zoomState.value?.zoomRatio ?: 1.0f))
            return true
        }
    }).let {
        previewView.setOnTouchListener { v: View, event: MotionEvent ->
            it.onTouchEvent(event)
            if (event.action == MotionEvent.ACTION_UP) v.performClick()
            true
        }
    }
}
