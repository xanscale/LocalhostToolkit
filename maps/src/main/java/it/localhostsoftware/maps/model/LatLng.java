package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleLatLng;

public abstract class LatLng<LL> {
    public static LatLng<?> getInstance(Context context, double var1, double var3) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new GoogleLatLng(new com.google.android.gms.maps.model.LatLng(var1, var3));
        else return null;
    }

    private final LL ll;

    public LatLng(LL ll) {
        this.ll = ll;
    }

    public LL getLatLng() {
        return ll;
    }

    public abstract double getLatitude();

    public abstract double getLongitude();
}
