package it.localhostsoftware.maps;

public class CameraUpdate {
    private final com.google.android.gms.maps.CameraUpdate googleCameraUpdate;

    public CameraUpdate(com.google.android.gms.maps.CameraUpdate googleCameraUpdate) {
        this.googleCameraUpdate = googleCameraUpdate;
    }

    public com.google.android.gms.maps.CameraUpdate getGoogleCameraUpdate() {
        return googleCameraUpdate;
    }
}
