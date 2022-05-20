package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

public class RoundCap<C> extends Cap<C> {
    public static RoundCap<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new RoundCap<>(new com.google.android.gms.maps.model.RoundCap());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new RoundCap<>(new com.huawei.hms.maps.model.RoundCap());
        else throw new IllegalStateException();
    }

    public RoundCap(C c) {
        super(c);
    }
}