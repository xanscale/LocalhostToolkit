package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

public class MapStyleOptions<MSO> {
    private final MSO mso;

    public MapStyleOptions(MSO mso) {
        this.mso = mso;
    }

    public MSO getMapStyleOptions() {
        return mso;
    }

    public static MapStyleOptions<?> getInstance(Context context, String googleJson, String huaweiJson) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new MapStyleOptions<>(new com.google.android.gms.maps.model.MapStyleOptions(googleJson));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new MapStyleOptions<>(new com.huawei.hms.maps.model.MapStyleOptions(huaweiJson));
        else throw new IllegalStateException();
    }

    public static MapStyleOptions<?> loadRawResourceStyle(Context context, int googleResId, int huaweiResId) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new MapStyleOptions<>(com.google.android.gms.maps.model.MapStyleOptions.loadRawResourceStyle(context, googleResId));
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new MapStyleOptions<>(com.huawei.hms.maps.model.MapStyleOptions.loadRawResourceStyle(context, huaweiResId));
        else throw new IllegalStateException();
    }
}
