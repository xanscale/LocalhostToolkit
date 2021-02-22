package it.localhostsoftware.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public abstract class Polyline<P> {
    private final P p;

    public Polyline(P p) {
        this.p = p;
    }

    public P getPolyline() {
        return p;
    }

    public abstract void remove();

    public abstract String getId();

    public abstract void setPoints(List<LatLng<?>> var1);

    public abstract List<LatLng<?>> getPoints();

    public abstract void setWidth(float var1);

    public abstract float getWidth();

    public abstract void setColor(int var1);

    public abstract int getColor();

    public abstract void setStartCap(@NonNull Cap<?> var1);

    @NonNull
    public abstract Cap<?> getStartCap();

    public abstract void setEndCap(@NonNull Cap<?> var1);

    @NonNull
    public abstract Cap<?> getEndCap();

    public abstract void setJointType(int var1);

    public abstract int getJointType();

    public abstract void setPattern(@Nullable List<PatternItem<?>> var1);

    @Nullable
    public abstract List<PatternItem<?>> getPattern();

    public abstract void setZIndex(float var1);

    public abstract float getZIndex();

    public abstract void setVisible(boolean var1);

    public abstract boolean isVisible();

    public abstract void setGeodesic(boolean var1);

    public abstract boolean isGeodesic();

    public abstract void setClickable(boolean var1);

    public abstract boolean isClickable();

    public abstract void setTag(Object var1);

    @Nullable
    public abstract Object getTag();

    public abstract boolean equals(Object var1);

    public abstract int hashCode();
}
