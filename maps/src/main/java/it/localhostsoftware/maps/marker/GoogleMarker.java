package it.localhostsoftware.maps.marker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.BitmapDescriptor;

import it.localhostsoftware.maps.LatLng;

public class GoogleMarker implements Marker {
    private final com.google.android.gms.maps.model.Marker marker;

    public GoogleMarker(com.google.android.gms.maps.model.Marker marker) {
        this.marker = marker;
    }

    @Override
    public void remove() {
        marker.remove();
    }

    @Override
    public String getId() {
        return marker.getId();
    }

    @Override
    public void setPosition(@NonNull LatLng latLng) {
        marker.setPosition(latLng.toGoogleLatLng());
    }

    @Override
    public LatLng getPosition() {
        return LatLng.fromGoogleLatLng(marker.getPosition());
    }

    @Override
    public void setZIndex(float v) {
        marker.setZIndex(v);
    }

    @Override
    public float getZIndex() {
        return marker.getZIndex();
    }

    @Override
    public void setIcon(@Nullable BitmapDescriptor bitmapDescriptor) {
        marker.setIcon(bitmapDescriptor);
    }

    @Override
    public void setAnchor(float v, float v1) {
        marker.setAnchor(v, v1);
    }

    @Override
    public void setInfoWindowAnchor(float v, float v1) {
        marker.setInfoWindowAnchor(v, v1);
    }

    @Override
    public void setTitle(@Nullable String s) {
        marker.setTitle(s);
    }

    @Override
    public String getTitle() {
        return marker.getTitle();
    }

    @Override
    public void setSnippet(@Nullable String s) {
        marker.setSnippet(s);
    }

    @Override
    public String getSnippet() {
        return marker.getSnippet();
    }

    @Override
    public void setDraggable(boolean b) {
        marker.setDraggable(b);
    }

    @Override
    public boolean isDraggable() {
        return marker.isDraggable();
    }

    @Override
    public void showInfoWindow() {
        marker.showInfoWindow();
    }

    @Override
    public void hideInfoWindow() {
        marker.hideInfoWindow();
    }

    @Override
    public boolean isInfoWindowShown() {
        return marker.isInfoWindowShown();
    }

    @Override
    public void setVisible(boolean b) {
        marker.setVisible(b);
    }

    @Override
    public boolean isVisible() {
        return marker.isVisible();
    }

    @Override
    public void setFlat(boolean b) {
        marker.setFlat(b);
    }

    @Override
    public boolean isFlat() {
        return marker.isFlat();
    }

    @Override
    public void setRotation(float v) {
        marker.setRotation(v);
    }

    @Override
    public float getRotation() {
        return marker.getRotation();
    }

    @Override
    public void setAlpha(float v) {
        marker.setAlpha(v);
    }

    @Override
    public float getAlpha() {
        return marker.getAlpha();
    }

    @Override
    public void setTag(@Nullable Object o) {
        marker.setTag(o);
    }

    @Nullable
    @Override
    public Object getTag() {
        return marker.getTag();
    }
}
