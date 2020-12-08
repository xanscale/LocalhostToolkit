package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.BitmapDescriptor;

public class GoogleBitmapDescriptor implements BitmapDescriptor {
    private final com.google.android.gms.maps.model.BitmapDescriptor bitmapDescriptor;

    public GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptor bitmapDescriptor) {
        this.bitmapDescriptor = bitmapDescriptor;
    }

    public com.google.android.gms.maps.model.BitmapDescriptor getBitmapDescriptor() {
        return bitmapDescriptor;
    }
}
