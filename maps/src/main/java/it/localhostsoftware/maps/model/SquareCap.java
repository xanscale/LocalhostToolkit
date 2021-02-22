package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

public class SquareCap<C> extends Cap<C> {
    public static SquareCap<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new SquareCap<>(new com.google.android.gms.maps.model.SquareCap());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new SquareCap<>(new com.huawei.hms.maps.model.SquareCap());
        else throw new IllegalStateException();
    }

    public SquareCap(C c) {
        super(c);
    }
}