package it.localhostsoftware.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public abstract class Polygon<P> {
    private final P p;

    public Polygon(P p) {
        this.p = p;
    }

    public P getPolygon() {
        return p;
    }

    public abstract void remove();

    @NonNull
    public abstract String getId();

    public abstract void setPoints(@NonNull List<LatLng<?>> param1);

    @NonNull
    public abstract List<LatLng<?>> getPoints();

    public abstract void setHoles(@NonNull List<? extends List<LatLng<?>>> param1);

    @NonNull
    public abstract List<List<LatLng<?>>> getHoles();

    public abstract void setStrokeWidth(float param1);

    public abstract float getStrokeWidth();

    public abstract void setStrokeColor(int param1);

    public abstract int getStrokeColor();

    public abstract void setStrokeJointType(int param1);

    public abstract int getStrokeJointType();

    public abstract void setStrokePattern(@Nullable List<PatternItem<?>> param1);

    @Nullable
    public abstract List<PatternItem<?>> getStrokePattern();

    public abstract void setFillColor(int param1);

    public abstract int getFillColor();

    public abstract void setZIndex(float param1);

    public abstract float getZIndex();

    public abstract void setVisible(boolean param1);

    public abstract boolean isVisible();

    public abstract void setGeodesic(boolean param1);

    public abstract boolean isGeodesic();

    public abstract void setClickable(boolean param1);

    public abstract boolean isClickable();

    public abstract void setTag(@Nullable Object param1);

    @Nullable
    public abstract Object getTag();
}