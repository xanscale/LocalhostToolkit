package it.localhostsoftware.maps.huawei.model;

import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.LatLng;

public class HuaweiCameraPosition extends CameraPosition<com.huawei.hms.maps.model.CameraPosition> {
    public HuaweiCameraPosition(com.huawei.hms.maps.model.CameraPosition cameraPosition) {
        super(cameraPosition);
    }

    @Override
    public LatLng<?> getTarget() {
        return new HuaweiLatLng(getCameraPosition().target);
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
