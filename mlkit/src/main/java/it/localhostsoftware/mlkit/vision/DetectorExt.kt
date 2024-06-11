package it.localhostsoftware.mlkit.vision

import android.Manifest
import android.content.Context
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.interfaces.Detector

@RequiresPermission(Manifest.permission.CAMERA)
fun <DetectionResultT> Detector<DetectionResultT>.bindToLifecycle(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    selector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    enableScaleGestureDetector: Boolean = true,
    targetCoordinateSystem: Int = ImageAnalysis.COORDINATE_SYSTEM_VIEW_REFERENCED,
    block: (Result<DetectionResultT?>) -> Unit
) {
    LifecycleCameraController(context).apply {
        previewView.controller = this
        cameraSelector = selector
        setImageAnalysisAnalyzer(ContextCompat.getMainExecutor(context), MlKitAnalyzer(listOf(this@bindToLifecycle), targetCoordinateSystem, ContextCompat.getMainExecutor(context)) { res ->
            block(res.getThrowable(this@bindToLifecycle).let { if (it != null) Result.failure(it) else Result.success(res.getValue(this@bindToLifecycle)) })
        })
        if (enableScaleGestureDetector) setScaleGestureDetector(context, previewView)
        bindToLifecycle(lifecycleOwner)
    }
}

fun LifecycleCameraController.setScaleGestureDetector(context: Context, previewView: PreviewView) {
    ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            cameraControl?.setZoomRatio(detector.scaleFactor * (cameraInfo?.zoomState?.value?.zoomRatio ?: 1.0f))
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