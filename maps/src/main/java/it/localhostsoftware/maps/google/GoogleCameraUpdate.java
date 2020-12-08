package it.localhostsoftware.maps.google;

import it.localhostsoftware.maps.CameraUpdate;

public class GoogleCameraUpdate extends CameraUpdate<com.google.android.gms.maps.CameraUpdate> {
    public GoogleCameraUpdate(com.google.android.gms.maps.CameraUpdate cu) {
        super(cu);
    }
}
