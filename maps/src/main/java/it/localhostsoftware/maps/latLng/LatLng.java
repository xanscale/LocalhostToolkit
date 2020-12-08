package it.localhostsoftware.maps.latLng;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public interface LatLng {
    static LatLng getInstance(Context context, double var1, double var3) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new GoogleLatLng(var1, var3);
        else return null;
    }

    double getLatitude();

    double getLongitude();
}
