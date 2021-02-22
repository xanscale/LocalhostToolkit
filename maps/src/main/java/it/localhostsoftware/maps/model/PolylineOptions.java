package it.localhostsoftware.maps.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import java.util.List;

import it.localhostsoftware.maps.google.model.GooglePolylineOptions;
import it.localhostsoftware.maps.huawei.model.HuaweiPolylineOptions;

public abstract class PolylineOptions<PO> {
    public static PolylineOptions<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GooglePolylineOptions(new com.google.android.gms.maps.model.PolylineOptions());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiPolylineOptions(new com.huawei.hms.maps.model.PolylineOptions());
        else throw new IllegalStateException();
    }

    private final PO po;

    public PolylineOptions(PO po) {
        this.po = po;
    }

    public PO getPolylineOptions() {
        return po;
    }

    public abstract PolylineOptions<?> add(LatLng<?> var1);

    public abstract PolylineOptions<?> add(LatLng<?>... var1);

    public abstract PolylineOptions<?> addAll(Iterable<LatLng<?>> var1);

    public abstract PolylineOptions<?> width(float var1);

    public abstract PolylineOptions<?> color(int var1);

    public abstract PolylineOptions<?> startCap(@NonNull Cap<?> var1);

    public abstract PolylineOptions<?> endCap(@NonNull Cap<?> var1);

    public abstract PolylineOptions<?> jointType(int var1);

    public abstract PolylineOptions<?> pattern(@Nullable List<PatternItem<?>> var1);

    public abstract PolylineOptions<?> zIndex(float var1);

    public abstract PolylineOptions<?> visible(boolean var1);

    public abstract PolylineOptions<?> geodesic(boolean var1);

    public abstract PolylineOptions<?> clickable(boolean var1);

    public abstract List<LatLng<?>> getPoints();

    public abstract float getWidth();

    public abstract int getColor();

    @NonNull
    public abstract Cap<?> getStartCap();

    @NonNull
    public abstract Cap<?> getEndCap();

    public abstract int getJointType();

    @Nullable
    public abstract List<PatternItem<?>> getPattern();

    public abstract float getZIndex();

    public abstract boolean isVisible();

    public abstract boolean isGeodesic();

    public abstract boolean isClickable();
}
