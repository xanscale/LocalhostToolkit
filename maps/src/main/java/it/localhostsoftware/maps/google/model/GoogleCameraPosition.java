package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.LatLng;

public class GoogleCameraPosition extends CameraPosition<com.google.android.gms.maps.model.CameraPosition> {
    public GoogleCameraPosition(com.google.android.gms.maps.model.CameraPosition cameraPosition) {
        super(cameraPosition);
    }

    @Override
    public LatLng<?> getTarget() {
        return new GoogleLatLng(getCameraPosition().target);
    }

    @Override
    public float getZoom() {
        return getCameraPosition().zoom;
    }

    @Override
    public float getTilt() {
        return getCameraPosition().tilt;
    }

    @Override
    public float getBearing() {
        return getCameraPosition().bearing;
    }
}
