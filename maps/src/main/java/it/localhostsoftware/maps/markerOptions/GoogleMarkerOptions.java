package it.localhostsoftware.maps.markerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.localhostsoftware.maps.LatLng;

public class GoogleMarkerOptions implements MarkerOptions {
    private final com.google.android.gms.maps.model.MarkerOptions markerOptions;

    public GoogleMarkerOptions() {
        this.markerOptions = new com.google.android.gms.maps.model.MarkerOptions();
    }

    public com.google.android.gms.maps.model.MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    @Override
    public MarkerOptions position(@NonNull LatLng latLng) {
        markerOptions.position(latLng.getGoogleLatLng());
        return this;
    }

    @Override
    public MarkerOptions zIndex(float v) {
        markerOptions.zIndex(v);
        return this;
    }

    @Override
    public MarkerOptions anchor(float v, float v1) {
        markerOptions.anchor(v, v1);
        return this;
    }

    @Override
    public MarkerOptions infoWindowAnchor(float v, float v1) {
        markerOptions.infoWindowAnchor(v, v1);
        return this;
    }

    @Override
    public MarkerOptions title(@Nullable String s) {
        markerOptions.title(s);
        return this;
    }

    @Override
    public MarkerOptions snippet(@Nullable String s) {
        markerOptions.snippet(s);
        return this;
    }

    @Override
    public MarkerOptions draggable(boolean b) {
        markerOptions.draggable(b);
        return this;
    }

    @Override
    public MarkerOptions visible(boolean b) {
        markerOptions.visible(b);
        return this;
    }

    @Override
    public MarkerOptions flat(boolean b) {
        markerOptions.flat(b);
        return this;
    }

    @Override
    public MarkerOptions rotation(float v) {
        markerOptions.rotation(v);
        return this;
    }

    @Override
    public MarkerOptions alpha(float v) {
        markerOptions.alpha(v);
        return this;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(markerOptions.getPosition());
    }

    @Override
    public String getTitle() {
        return markerOptions.getTitle();
    }

    @Override
    public String getSnippet() {
        return markerOptions.getSnippet();
    }

    @Override
    public float getAnchorU() {
        return markerOptions.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return markerOptions.getAnchorV();
    }

    @Override
    public boolean isDraggable() {
        return markerOptions.isDraggable();
    }

    @Override
    public boolean isVisible() {
        return markerOptions.isVisible();
    }

    @Override
    public boolean isFlat() {
        return markerOptions.isFlat();
    }

    @Override
    public float getRotation() {
        return markerOptions.getRotation();
    }

    @Override
    public float getInfoWindowAnchorU() {
        return markerOptions.getInfoWindowAnchorU();
    }

    @Override
    public float getInfoWindowAnchorV() {
        return markerOptions.getInfoWindowAnchorV();
    }

    @Override
    public float getAlpha() {
        return markerOptions.getAlpha();
    }

    @Override
    public float getZIndex() {
        return markerOptions.getZIndex();
    }
}
