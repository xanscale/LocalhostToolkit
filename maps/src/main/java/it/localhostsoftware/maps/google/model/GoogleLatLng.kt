package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.LatLng;

public class GoogleLatLng extends LatLng<com.google.android.gms.maps.model.LatLng> {
    public GoogleLatLng(com.google.android.gms.maps.model.LatLng latLng) {
        super(latLng);
    }

    @Override
    public double getLatitude() {
        return getLatLng().latitude;
    }

    @Override
    public double getLongitude() {
        return getLatLng().longitude;
    }
}
