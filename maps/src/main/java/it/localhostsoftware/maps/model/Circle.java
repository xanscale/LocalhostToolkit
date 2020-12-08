package it.localhostsoftware.maps.model;

import androidx.annotation.Nullable;

public abstract class Circle<C> {
    private final C c;

    public Circle(C c) {
        this.c = c;
    }

    public C getCircle() {
        return c;
    }

    public abstract void remove();

    public abstract String getId();

    public abstract void setCenter(LatLng<?> var1);

    public abstract LatLng<?> getCenter();

    public abstract void setRadius(double var1);

    public abstract double getRadius();

    public abstract void setStrokeWidth(float var1);

    public abstract float getStrokeWidth();

    public abstract void setStrokeColor(int var1);

    public abstract int getStrokeColor();

    // public abstract void setStrokePattern(@Nullable List<PatternItem> var1);

    // @Nullable public abstract List<PatternItem> getStrokePattern();

    public abstract void setFillColor(int var1);

    public abstract int getFillColor();

    public abstract void setZIndex(float var1);

    public abstract float getZIndex();

    public abstract void setVisible(boolean var1);

    public abstract boolean isVisible();

    public abstract void setClickable(boolean var1);

    public abstract boolean isClickable();

    public abstract void setTag(@Nullable Object var1);

    @Nullable
    public abstract Object getTag();
}
