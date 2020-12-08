package it.localhostsoftware.maps.model.marker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import it.localhostsoftware.maps.model.bitmapDescriptor.BitmapDescriptor;
import it.localhostsoftware.maps.model.latLng.LatLng;

public interface Marker {
    void remove();

    String getId();

    void setPosition(@NonNull LatLng var1);

    LatLng getPosition();

    void setZIndex(float var1);

    float getZIndex();

    void setIcon(@Nullable BitmapDescriptor var1);

    void setAnchor(float var1, float var2);

    void setInfoWindowAnchor(float var1, float var2);

    void setTitle(@Nullable String var1);

    String getTitle();

    void setSnippet(@Nullable String var1);

    String getSnippet();

    void setDraggable(boolean var1);

    boolean isDraggable();

    void showInfoWindow();

    void hideInfoWindow();

    boolean isInfoWindowShown();

    void setVisible(boolean var1);

    boolean isVisible();

    void setFlat(boolean var1);

    boolean isFlat();

    void setRotation(float var1);

    float getRotation();

    void setAlpha(float var1);

    float getAlpha();

    void setTag(@Nullable Object var1);

    @Nullable
    Object getTag();

    boolean equals(Object var1);

    int hashCode();
}
