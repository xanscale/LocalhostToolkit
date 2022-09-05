package it.localhostsoftware.mlkit.vision

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.guava.await
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

abstract class AbstractImageAnalyzer<TResult> {
    private var camera: Camera? = null

    @RequiresPermission(Manifest.permission.CAMERA)
    fun bindToLifecycle(
            context: Context,
            lifecycleOwner: LifecycleOwner,
            previewView: PreviewView,
            cameraSelector: CameraSelector,
            enableScaleGestureDetector: Boolean = true,
            onSuccessListener: OnSuccessListener<TResult>
    ) {
        lifecycleOwner.lifecycleScope.launch {
            Preview.Builder().build().let { preview ->
                preview.setSurfaceProvider(previewView.surfaceProvider)
                ImageAnalysis.Builder().build().let { imageAnalysis ->
                    imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor()) { analyze(it, onSuccessListener) }
                    camera = ProcessCameraProvider.getInstance(context).await().bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageAnalysis)
                    if (enableScaleGestureDetector) setScaleGestureDetector(context, previewView)
                }
            }
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun analyze(imageProxy: ImageProxy, onSuccessListener: OnSuccessListener<TResult>) {
        imageProxy.image?.let { image ->
            process(image, imageProxy.imageInfo.rotationDegrees)
                    .addOnSuccessListener(onSuccessListener)
                    .addOnFailureListener { it.printStackTrace() }
                    .addOnCompleteListener { imageProxy.close() }
        }
    }

    private fun setScaleGestureDetector(context: Context, previewView: PreviewView) {
        ScaleGestureDetector(context, object : SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector) = camera?.let {
                it.cameraControl.setZoomRatio(detector.scaleFactor * (it.cameraInfo.zoomState.value?.zoomRatio ?: 1.0f))
                true
            } ?: false
        }).let {
            previewView.setOnTouchListener { v: View, event: MotionEvent ->
                it.onTouchEvent(event)
                if (event.action == MotionEvent.ACTION_UP) v.performClick()
                true
            }
        }
    }

    protected abstract fun process(image: Image, rotationDegrees: Int): Task<TResult>
}