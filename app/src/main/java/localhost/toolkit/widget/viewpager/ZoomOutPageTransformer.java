package localhost.toolkit.widget.viewpager;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class ZoomOutPageTransformer implements ViewPager.PageTransformer, ViewPager2.PageTransformer {
    private final float minScale;
    private final float minAlpha;

    public ZoomOutPageTransformer() {
        minScale = 0.85f;
        minAlpha = 0.5f;
    }

    public ZoomOutPageTransformer(float minScale, float minAlpha) {
        this.minScale = minScale;
        this.minAlpha = minAlpha;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        if (position < -1)
            view.setAlpha(0);
        else if (position <= 1) {
            float scaleFactor = Math.max(minScale, 1 - Math.abs(position));
            float vMargin = view.getHeight() * (1 - scaleFactor) / 2;
            float hMargin = view.getWidth() * (1 - scaleFactor) / 2;
            if (position < 0)
                view.setTranslationX(hMargin - vMargin / 2);
            else
                view.setTranslationX(-hMargin + vMargin / 2);
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setAlpha(minAlpha + (scaleFactor - minScale) / (1 - minScale) * (1 - minAlpha));
        } else
            view.setAlpha(0);
    }
}