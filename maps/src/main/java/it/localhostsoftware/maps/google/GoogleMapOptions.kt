package it.localhostsoftware.maps.google;

import it.localhostsoftware.maps.MapOptions;
import it.localhostsoftware.maps.google.model.GoogleCameraPosition;
import it.localhostsoftware.maps.google.model.GoogleLatLngBounds;
import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.LatLngBounds;

public class GoogleMapOptions extends MapOptions<com.google.android.gms.maps.GoogleMapOptions> {
    public GoogleMapOptions(com.google.android.gms.maps.GoogleMapOptions googleMapOptions) {
        super(googleMapOptions);
    }

    @Override
    public MapOptions<?> zOrderOnTop(boolean var1) {
        getMapOptions().zOrderOnTop(var1);
        return this;
    }

    @Override
    public MapOptions<?> useViewLifecycleInFragment(boolean var1) {
        getMapOptions().useViewLifecycleInFragment(var1);
        return this;
    }

    @Override
    public MapOptions<?> mapType(int var1) {
        getMapOptions().mapType(var1);
        return this;
    }

    @Override
    public MapOptions<?> camera(CameraPosition<?> var1) {
        getMapOptions().camera((com.google.android.gms.maps.model.CameraPosition) var1.getCameraPosition());
        return this;
    }

    @Override
    public MapOptions<?> zoomControlsEnabled(boolean var1) {
        getMapOptions().zoomControlsEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> compassEnabled(boolean var1) {
        getMapOptions().compassEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> scrollGesturesEnabled(boolean var1) {
        getMapOptions().scrollGesturesEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> zoomGesturesEnabled(boolean var1) {
        getMapOptions().zoomGesturesEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> tiltGesturesEnabled(boolean var1) {
        getMapOptions().tiltGesturesEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> rotateGesturesEnabled(boolean var1) {
        getMapOptions().rotateGesturesEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> liteMode(boolean var1) {
        getMapOptions().liteMode(var1);
        return this;
    }

    @Override
    public MapOptions<?> mapToolbarEnabled(boolean var1) {
        getMapOptions().mapToolbarEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> ambientEnabled(boolean var1) {
        getMapOptions().ambientEnabled(var1);
        return this;
    }

    @Override
    public MapOptions<?> minZoomPreference(float var1) {
        getMapOptions().minZoomPreference(var1);
        return this;
    }

    @Override
    public MapOptions<?> maxZoomPreference(float var1) {
        getMapOptions().maxZoomPreference(var1);
        return this;
    }

    @Override
    public MapOptions<?> latLngBoundsForCameraTarget(LatLngBounds<?> var1) {
        getMapOptions().latLngBoundsForCameraTarget((com.google.android.gms.maps.model.LatLngBounds) var1.getLatLngBounds());
        return this;
    }

    @Override
    public Boolean getZOrderOnTop() {
        return getMapOptions().getZOrderOnTop();
    }

    @Override
    public Boolean getUseViewLifecycleInFragment() {
        return getMapOptions().getUseViewLifecycleInFragment();
    }

    @Override
    public int getMapType() {
        return getMapOptions().getMapType();
    }

    @Override
    public CameraPosition<?> getCamera() {
        return new GoogleCameraPosition(getMapOptions().getCamera());
    }

    @Override
    public Boolean getZoomControlsEnabled() {
        return getMapOptions().getZoomControlsEnabled();
    }

    @Override
    public Boolean getCompassEnabled() {
        return getMapOptions().getCompassEnabled();
    }

    @Override
    public Boolean getScrollGesturesEnabled() {
        return getMapOptions().getScrollGesturesEnabled();
    }

    @Override
    public Boolean getZoomGesturesEnabled() {
        return getMapOptions().getZoomGesturesEnabled();
    }

    @Override
    public Boolean getTiltGesturesEnabled() {
        return getMapOptions().getTiltGesturesEnabled();
    }

    @Override
    public Boolean getRotateGesturesEnabled() {
        return getMapOptions().getRotateGesturesEnabled();
    }

    @Override
    public Boolean getLiteMode() {
        return getMapOptions().getLiteMode();
    }

    @Override
    public Boolean getMapToolbarEnabled() {
        return getMapOptions().getMapToolbarEnabled();
    }

    @Override
    public Boolean getAmbientEnabled() {
        return getMapOptions().getAmbientEnabled();
    }

    @Override
    public Float getMinZoomPreference() {
        return getMapOptions().getMinZoomPreference();
    }

    @Override
    public Float getMaxZoomPreference() {
        return getMapOptions().getMaxZoomPreference();
    }

    @Override
    public LatLngBounds<?> getLatLngBoundsForCameraTarget() {
        return new GoogleLatLngBounds(getMapOptions().getLatLngBoundsForCameraTarget());
    }
}
