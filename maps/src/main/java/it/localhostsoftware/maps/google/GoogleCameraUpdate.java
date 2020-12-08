package it.localhostsoftware.maps.google;

import it.localhostsoftware.maps.CameraUpdate;

public class GoogleCameraUpdate implements CameraUpdate {
    private final com.google.android.gms.maps.CameraUpdate googleCameraUpdate;

    public GoogleCameraUpdate(com.google.android.gms.maps.CameraUpdate googleCameraUpdate) {
        this.googleCameraUpdate = googleCameraUpdate;
    }

    public com.google.android.gms.maps.CameraUpdate getGoogleCameraUpdate() {
        return googleCameraUpdate;
    }
}
