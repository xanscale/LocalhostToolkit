package it.localhostsoftware.maps.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class Marker<M> {
    private final M m;

    public Marker(M m) {
        this.m = m;
    }

    public M getMarker() {
        return m;
    }

    abstract public void remove();

    abstract public String getId();

    abstract public void setPosition(@NonNull LatLng<?> var1);

    abstract public LatLng<?> getPosition();

    abstract public void setZIndex(float var1);

    abstract public float getZIndex();

    abstract public void setIcon(@Nullable BitmapDescriptor<?> var1);

    abstract public void setAnchor(float var1, float var2);

    abstract public void setInfoWindowAnchor(float var1, float var2);

    abstract public void setTitle(@Nullable String var1);

    abstract public String getTitle();

    abstract public void setSnippet(@Nullable String var1);

    abstract public String getSnippet();

    abstract public void setDraggable(boolean var1);

    abstract public boolean isDraggable();

    abstract public void showInfoWindow();

    abstract public void hideInfoWindow();

    abstract public boolean isInfoWindowShown();

    abstract public void setVisible(boolean var1);

    abstract public boolean isVisible();

    abstract public void setFlat(boolean var1);

    abstract public boolean isFlat();

    abstract public void setRotation(float var1);

    abstract public float getRotation();

    abstract public void setAlpha(float var1);

    abstract public float getAlpha();

    abstract public void setTag(@Nullable Object var1);

    @Nullable
    abstract public Object getTag();
}
