package it.localhostsoftware.maps.cameraUpdate;

public class GoogleCameraUpdate implements CameraUpdate {
    private final com.google.android.gms.maps.CameraUpdate googleCameraUpdate;

    public GoogleCameraUpdate(com.google.android.gms.maps.CameraUpdate googleCameraUpdate) {
        this.googleCameraUpdate = googleCameraUpdate;
    }

    public com.google.android.gms.maps.CameraUpdate getGoogleCameraUpdate() {
        return googleCameraUpdate;
    }
}
