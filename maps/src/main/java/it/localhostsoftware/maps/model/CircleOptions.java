package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleCircleOptions;
import it.localhostsoftware.maps.huawei.model.HuaweiCircleOptions;

public abstract class CircleOptions<CO> {
    public static CircleOptions<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GoogleCircleOptions(new com.google.android.gms.maps.model.CircleOptions());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiCircleOptions(new com.huawei.hms.maps.model.CircleOptions());
        else throw new IllegalStateException();
    }

    private final CO co;

    public CircleOptions(CO co) {
        this.co = co;
    }

    public CO getCircleOptions() {
        return co;
    }

    public abstract CircleOptions<?> center(LatLng<?> var1);

    public abstract CircleOptions<?> radius(double var1);

    public abstract CircleOptions<?> strokeWidth(float var1);

    public abstract CircleOptions<?> strokeColor(int var1);

    //  public abstract CircleOptions<?> strokePattern(@Nullable List<PatternItem> var1);

    public abstract CircleOptions<?> fillColor(int var1);

    public abstract CircleOptions<?> zIndex(float var1);

    public abstract CircleOptions<?> visible(boolean var1);

    public abstract CircleOptions<?> clickable(boolean var1);

    public abstract LatLng<?> getCenter();

    public abstract double getRadius();

    public abstract float getStrokeWidth();

    public abstract int getStrokeColor();

    // @Nullable public abstract List<PatternItem> getStrokePattern();

    public abstract int getFillColor();

    public abstract float getZIndex();

    public abstract boolean isVisible();

    public abstract boolean isClickable();
}
