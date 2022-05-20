package it.localhostsoftware.maps.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import java.util.List;

import it.localhostsoftware.maps.google.model.GooglePolygonOptions;
import it.localhostsoftware.maps.huawei.model.HuaweiPolygonOptions;

public abstract class PolygonOptions<PO> {
    public static PolygonOptions<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GooglePolygonOptions(new com.google.android.gms.maps.model.PolygonOptions());
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiPolygonOptions(new com.huawei.hms.maps.model.PolygonOptions());
        else throw new IllegalStateException();
    }

    private final PO po;

    public PolygonOptions(PO po) {
        this.po = po;
    }

    public PO getPolygonOptions() {
        return po;
    }

    @NonNull
    public abstract PolygonOptions<?> add(@NonNull LatLng<?> point);

    @NonNull
    public abstract PolygonOptions<?> add(@NonNull LatLng<?>... points);

    @NonNull
    public abstract PolygonOptions<?> addAll(@NonNull Iterable<LatLng<?>> points);

    @NonNull
    public abstract PolygonOptions<?> addHole(@NonNull Iterable<LatLng<?>> points);

    @NonNull
    public abstract PolygonOptions<?> strokeWidth(float width);

    @NonNull
    public abstract PolygonOptions<?> strokeColor(int color);

    @NonNull
    public abstract PolygonOptions<?> strokeJointType(int jointType);

    @NonNull
    public abstract PolygonOptions<?> strokePattern(@Nullable List<PatternItem<?>> pattern);

    @NonNull
    public abstract PolygonOptions<?> fillColor(int color);

    @NonNull
    public abstract PolygonOptions<?> zIndex(float zIndex);

    @NonNull
    public abstract PolygonOptions<?> visible(boolean visible);

    @NonNull
    public abstract PolygonOptions<?> geodesic(boolean geodesic);

    @NonNull
    public abstract PolygonOptions<?> clickable(boolean clickable);

    @NonNull
    public abstract List<LatLng<?>> getPoints();

    @NonNull
    public abstract List<List<LatLng<?>>> getHoles();

    public abstract float getStrokeWidth();

    public abstract int getStrokeColor();

    public abstract int getStrokeJointType();

    @Nullable
    public abstract List<PatternItem<?>> getStrokePattern();

    public abstract int getFillColor();

    public abstract float getZIndex();

    public abstract boolean isVisible();

    public abstract boolean isGeodesic();

    public abstract boolean isClickable();
}