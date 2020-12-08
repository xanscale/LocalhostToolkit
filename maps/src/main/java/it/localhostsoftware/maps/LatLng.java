package it.localhostsoftware.maps;

public class LatLng {
    private com.google.android.gms.maps.model.LatLng googleLatLng;

    public LatLng(com.google.android.gms.maps.model.LatLng googleLatLng) {
        this.googleLatLng = googleLatLng;
    }

    public com.google.android.gms.maps.model.LatLng getGoogleLatLng() {
        return googleLatLng;
    }
}
