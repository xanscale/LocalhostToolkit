package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleLatLng;
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng;

public abstract class LatLng<LL> {
    public static LatLng<?> getInstance(Context context, double var1, double var3) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GoogleLatLng(new com.google.android.gms.maps.model.LatLng(var1, var3));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiLatLng(new com.huawei.hms.maps.model.LatLng(var1, var3));
        else throw new IllegalStateException();
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
