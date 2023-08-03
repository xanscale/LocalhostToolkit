package it.localhostsoftware.core.widget.viewpager

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class ZoomOutPageTransformer(
        private val minScale: Float = 0.85f,
        private val minAlpha: Float = 0.5f
) : ViewPager.PageTransformer, ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.alpha = if (position in -1f..1f) {
            max(minScale, 1 - abs(position)).let { scaleFactor ->
                (view.height * (1 - scaleFactor) / 2).let { vMargin ->
                    (view.width * (1 - scaleFactor) / 2).let { hMargin ->
                        view.translationX = if (position < 0) hMargin - vMargin / 2 else -hMargin + vMargin / 2
                    }
                }
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
                minAlpha + (scaleFactor - minScale) / (1 - minScale) * (1 - minAlpha)
            }
        } else 0f
    }
}