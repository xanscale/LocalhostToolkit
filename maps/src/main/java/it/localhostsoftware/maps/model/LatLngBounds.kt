package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleLatLngBounds;
import it.localhostsoftware.maps.huawei.model.HuaweiLatLngBounds;

public abstract class LatLngBounds<LB> {
    private final LB lb;

    public LatLngBounds(LB lb) {
        this.lb = lb;
    }

    public LB getLatLngBounds() {
        return lb;
    }

    public abstract LatLng<?> getSouthwest();

    public abstract LatLng<?> getNortheast();

    public abstract boolean contains(LatLng<?> var1);

    public abstract LatLngBounds<?> including(LatLng<?> var1);

    public abstract LatLng<?> getCenter();

    public abstract static class Builder<B> {
        public static Builder<?> getInstance(Context context) {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
                return new GoogleLatLngBounds.GoogleBuilder(new com.google.android.gms.maps.model.LatLngBounds.Builder());
            else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
                return new HuaweiLatLngBounds.HuaweiBuilder(new com.huawei.hms.maps.model.LatLngBounds.Builder());
            else throw new IllegalStateException();
        }

        private final B b;

        public Builder(B b) {
            this.b = b;
        }

        public B getBuilder() {
            return b;
        }

        public abstract LatLngBounds.Builder<?> include(LatLng<?> var1);

        public abstract LatLngBounds<?> build();
    }
}
