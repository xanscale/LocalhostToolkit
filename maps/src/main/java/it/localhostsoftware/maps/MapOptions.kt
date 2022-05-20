package it.localhostsoftware.maps;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.GoogleMapOptions;
import it.localhostsoftware.maps.huawei.HuaweiMapOptions;
import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.LatLngBounds;

public abstract class MapOptions<MO> {
    public static MapOptions<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GoogleMapOptions(new com.google.android.gms.maps.GoogleMapOptions());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiMapOptions(new com.huawei.hms.maps.HuaweiMapOptions());
        else throw new IllegalStateException();
    }

    private final MO mo;

    public MapOptions(MO mo) {
        this.mo = mo;
    }

    public MO getMapOptions() {
        return mo;
    }

    public abstract MapOptions<?> zOrderOnTop(boolean var1);

    public abstract MapOptions<?> useViewLifecycleInFragment(boolean var1);

    public abstract MapOptions<?> mapType(int var1);

    public abstract MapOptions<?> camera(CameraPosition<?> var1);

    public abstract MapOptions<?> zoomControlsEnabled(boolean var1);

    public abstract MapOptions<?> compassEnabled(boolean var1);

    public abstract MapOptions<?> scrollGesturesEnabled(boolean var1);

    public abstract MapOptions<?> zoomGesturesEnabled(boolean var1);

    public abstract MapOptions<?> tiltGesturesEnabled(boolean var1);

    public abstract MapOptions<?> rotateGesturesEnabled(boolean var1);

    public abstract MapOptions<?> liteMode(boolean var1);

    public abstract MapOptions<?> mapToolbarEnabled(boolean var1);

    public abstract MapOptions<?> ambientEnabled(boolean var1);

    public abstract MapOptions<?> minZoomPreference(float var1);

    public abstract MapOptions<?> maxZoomPreference(float var1);

    public abstract MapOptions<?> latLngBoundsForCameraTarget(LatLngBounds<?> var1);

    public abstract Boolean getZOrderOnTop();

    public abstract Boolean getUseViewLifecycleInFragment();

    public abstract int getMapType();

    public abstract CameraPosition<?> getCamera();

    public abstract Boolean getZoomControlsEnabled();

    public abstract Boolean getCompassEnabled();

    public abstract Boolean getScrollGesturesEnabled();

    public abstract Boolean getZoomGesturesEnabled();

    public abstract Boolean getTiltGesturesEnabled();

    public abstract Boolean getRotateGesturesEnabled();

    public abstract Boolean getLiteMode();

    public abstract Boolean getMapToolbarEnabled();

    public abstract Boolean getAmbientEnabled();

    public abstract Float getMinZoomPreference();

    public abstract Float getMaxZoomPreference();

    public abstract LatLngBounds<?> getLatLngBoundsForCameraTarget();
}
