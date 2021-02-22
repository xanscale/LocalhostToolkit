package it.localhostsoftware.maps.model;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.model.GooglePatternItem;
import it.localhostsoftware.maps.huawei.model.HuaweiPatternItem;


public class PatternItem<PI> {
    public static PatternItem<?> getInstance(Context context, int var1, float var2) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GooglePatternItem(new com.google.android.gms.maps.model.PatternItem(var1, var2));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiPatternItem(new com.huawei.hms.maps.model.PatternItem(var1, var2));
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