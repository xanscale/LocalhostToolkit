package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

public class CustomCap<C> extends Cap<C> {
    public static CustomCap<?> getInstance(Context context, BitmapDescriptor<?> var1, float var2) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new CustomCap<>(new com.google.android.gms.maps.model.CustomCap((com.google.android.gms.maps.model.BitmapDescriptor) var1.getBitmapDescriptor(), var2));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new CustomCap<>(new com.huawei.hms.maps.model.CustomCap((com.huawei.hms.maps.model.BitmapDescriptor) var1.getBitmapDescriptor(), var2));
        else throw new IllegalStateException();
    }

    public static CustomCap<?> getInstance(Context context, BitmapDescriptor<?> var1) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new CustomCap<>(new com.google.android.gms.maps.model.CustomCap((com.google.android.gms.maps.model.BitmapDescriptor) var1.getBitmapDescriptor()));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new CustomCap<>(new com.huawei.hms.maps.model.CustomCap((com.huawei.hms.maps.model.BitmapDescriptor) var1.getBitmapDescriptor()));
        else throw new IllegalStateException();
    }

    public CustomCap(C c) {
        super(c);
    }
}