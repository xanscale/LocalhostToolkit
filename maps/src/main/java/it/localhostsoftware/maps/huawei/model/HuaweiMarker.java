package it.localhostsoftware.maps.huawei.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.localhostsoftware.maps.model.BitmapDescriptor;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.Marker;

public class HuaweiMarker extends Marker<com.huawei.hms.maps.model.Marker> {
    public HuaweiMarker(com.huawei.hms.maps.model.Marker marker) {
        super(marker);
    }

    @Override
    public void remove() {
        getMarker().remove();
    }

    @Override
    public String getId() {
        return getMarker().getId();
    }

    @Override
    public void setPosition(@NonNull LatLng<?> latLng) {
        getMarker().setPosition((com.huawei.hms.maps.model.LatLng) latLng.getLatLng());
    }

    @Override
    public LatLng<?> getPosition() {
        return new HuaweiLatLng(getMarker().getPosition());
    }

    @Override
    public void setZIndex(float v) {
        getMarker().setZIndex(v);
    }

    @Override
    public float getZIndex() {
        return getMarker().getZIndex();
    }

    @Override
    public void setIcon(@Nullable BitmapDescriptor<?> bitmapDescriptor) {
        getMarker().setIcon((com.huawei.hms.maps.model.BitmapDescriptor) bitmapDescriptor.getBitmapDescriptor());
    }

    @Override
    public void setAnchor(float v, float v1) {
        getMarker().setMarkerAnchor(v, v1);
    }

    @Override
    public void setInfoWindowAnchor(float v, float v1) {
        getMarker().setInfoWindowAnchor(v, v1);
    }

    @Override
    public void setTitle(@Nullable String s) {
        getMarker().setTitle(s);
    }

    @Override
    public String getTitle() {
        return getMarker().getTitle();
    }

    @Override
    public void setSnippet(@Nullable String s) {
        getMarker().setSnippet(s);
    }

    @Override
    public String getSnippet() {
        return getMarker().getSnippet();
    }

    @Override
    public void setDraggable(boolean b) {
        getMarker().setDraggable(b);
    }

    @Override
    public boolean isDraggable() {
        return getMarker().isDraggable();
    }

    @Override
    public void showInfoWindow() {
        getMarker().showInfoWindow();
    }

    @Override
    public void hideInfoWindow() {
        getMarker().hideInfoWindow();
    }

    @Override
    public boolean isInfoWindowShown() {
        return getMarker().isInfoWindowShown();
    }

    @Override
    public void setVisible(boolean b) {
        getMarker().setVisible(b);
    }

    @Override
    public boolean isVisible() {
        return getMarker().isVisible();
    }

    @Override
    public void setFlat(boolean b) {
        getMarker().setFlat(b);
    }

    @Override
    public boolean isFlat() {
        return getMarker().isFlat();
    }

    @Override
    public void setRotation(float v) {
        getMarker().setRotation(v);
    }

    @Override
    public float getRotation() {
        return getMarker().getRotation();
    }

    @Override
    public void setAlpha(float v) {
        getMarker().setAlpha(v);
    }

    @Override
    public float getAlpha() {
        return getMarker().getAlpha();
    }

    @Override
    public void setTag(@Nullable Object o) {
        getMarker().setTag(o);
    }

    @Nullable
    @Override
    public Object getTag() {
        return getMarker().getTag();
    }
}
