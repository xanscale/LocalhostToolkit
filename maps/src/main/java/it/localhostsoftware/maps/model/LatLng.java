package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class LatLng<LL> {
    public static LatLng<?> getInstance(Context context, double var1, double var3) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new LatLng<>(new com.google.android.gms.maps.model.LatLng(var1, var3));
        else return null;
    }

    private final LL ll;

    public LatLng(LL ll) {
        this.ll = ll;
    }

    public LL getLatLng() {
        return ll;
    }

    public double getLatitude() {
        if (ll instanceof com.google.android.gms.maps.model.LatLng)
            return ((com.google.android.gms.maps.model.LatLng) ll).latitude;
        else throw new IllegalStateException();
    }

    public double getLongitude() {
        if (ll instanceof com.google.android.gms.maps.model.LatLng)
            return ((com.google.android.gms.maps.model.LatLng) ll).longitude;
        else throw new IllegalStateException();
    }
}
