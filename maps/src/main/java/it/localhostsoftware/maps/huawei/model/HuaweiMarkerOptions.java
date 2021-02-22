package it.localhostsoftware.maps.huawei.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.localhostsoftware.maps.model.BitmapDescriptor;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.MarkerOptions;

public class HuaweiMarkerOptions extends MarkerOptions<com.huawei.hms.maps.model.MarkerOptions> {
    public HuaweiMarkerOptions(com.huawei.hms.maps.model.MarkerOptions markerOptions) {
        super(markerOptions);
    }

    @Override
    public MarkerOptions<?> position(@NonNull LatLng<?> latLng) {
        getMarkerOptions().position((com.huawei.hms.maps.model.LatLng) latLng.getLatLng());
        return this;
    }

    @Override
    public MarkerOptions<?> zIndex(float v) {
        getMarkerOptions().zIndex(v);
        return this;
    }

    @Override
    public MarkerOptions<?> icon(@Nullable BitmapDescriptor<?> var1) {
        getMarkerOptions().icon(var1 == null ? null : (com.huawei.hms.maps.model.BitmapDescriptor) var1.getBitmapDescriptor());
        return this;
    }

    @Override
    public MarkerOptions<?> anchor(float v, float v1) {
        getMarkerOptions().anchorMarker(v, v1);
        return this;
    }

    @Override
    public MarkerOptions<?> infoWindowAnchor(float v, float v1) {
        getMarkerOptions().infoWindowAnchor(v, v1);
        return this;
    }

    @Override
    public MarkerOptions<?> title(@Nullable String s) {
        getMarkerOptions().title(s);
        return this;
    }

    @Override
    public MarkerOptions<?> snippet(@Nullable String s) {
        getMarkerOptions().snippet(s);
        return this;
    }

    @Override
    public MarkerOptions<?> draggable(boolean b) {
        getMarkerOptions().draggable(b);
        return this;
    }

    @Override
    public MarkerOptions<?> visible(boolean b) {
        getMarkerOptions().visible(b);
        return this;
    }

    @Override
    public MarkerOptions<?> flat(boolean b) {
        getMarkerOptions().flat(b);
        return this;
    }

    @Override
    public MarkerOptions<?> rotation(float v) {
        getMarkerOptions().rotation(v);
        return this;
    }

    @Override
    public MarkerOptions<?> alpha(float v) {
        getMarkerOptions().alpha(v);
        return this;
    }

    @Override
    public LatLng<?> getPosition() {
        return new HuaweiLatLng(getMarkerOptions().getPosition());
    }

    @Override
    public String getTitle() {
        return getMarkerOptions().getTitle();
    }

    @Override
    public String getSnippet() {
        return getMarkerOptions().getSnippet();
    }

    @Override
    public BitmapDescriptor<?> getIcon() {
        return new BitmapDescriptor<>(getMarkerOptions().getIcon());
    }

    @Override
    public float getAnchorU() {
        return getMarkerOptions().getMarkerAnchorU();
    }

    @Override
    public float getAnchorV() {
        return getMarkerOptions().getMarkerAnchorV();
    }

    @Override
    public boolean isDraggable() {
        return getMarkerOptions().isDraggable();
    }

    @Override
    public boolean isVisible() {
        return getMarkerOptions().isVisible();
    }

    @Override
    public boolean isFlat() {
        return getMarkerOptions().isFlat();
    }

    @Override
    public float getRotation() {
        return getMarkerOptions().getRotation();
    }

    @Override
    public float getInfoWindowAnchorU() {
        return getMarkerOptions().getInfoWindowAnchorU();
    }

    @Override
    public float getInfoWindowAnchorV() {
        return getMarkerOptions().getInfoWindowAnchorV();
    }

    @Override
    public float getAlpha() {
        return getMarkerOptions().getAlpha();
    }

    @Override
    public float getZIndex() {
        return getMarkerOptions().getZIndex();
    }
}
