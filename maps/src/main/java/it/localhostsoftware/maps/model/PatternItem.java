package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;


public class PatternItem<PI> {
    public static PatternItem<?> getInstance(Context context, int var1, float var2) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new PatternItem<>(new com.google.android.gms.maps.model.PatternItem(var1, var2));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new PatternItem<>(new com.huawei.hms.maps.model.PatternItem(var1, var2));
        else throw new IllegalStateException();
    }

    private final PI pi;

    public PatternItem(PI pi) {
        this.pi = pi;
    }

    public PI getPatternItem() {
        return pi;
    }
}