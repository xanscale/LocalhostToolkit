package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

public class ButtCap<C> extends Cap<C> {
    public static ButtCap<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new ButtCap<>(new com.google.android.gms.maps.model.ButtCap());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new ButtCap<>(new com.huawei.hms.maps.model.ButtCap());
        else throw new IllegalStateException();
    }

    public ButtCap(C c) {
        super(c);
    }
}
