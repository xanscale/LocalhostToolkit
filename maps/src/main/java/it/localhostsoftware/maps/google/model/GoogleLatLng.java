package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.LatLng;

public class GoogleLatLng implements LatLng {
    private final com.google.android.gms.maps.model.LatLng googleLatLng;

    public GoogleLatLng(com.google.android.gms.maps.model.LatLng googleLatLng) {
        this.googleLatLng = googleLatLng;
    }

    public GoogleLatLng(double var1, double var3) {
        this.googleLatLng = new com.google.android.gms.maps.model.LatLng(var1, var3);
    }

    public com.google.android.gms.maps.model.LatLng getGoogleLatLng() {
        return googleLatLng;
    }

    @Override
    public double getLatitude() {
        return googleLatLng.latitude;
    }

    @Override
    public double getLongitude() {
        return googleLatLng.longitude;
    }
}
