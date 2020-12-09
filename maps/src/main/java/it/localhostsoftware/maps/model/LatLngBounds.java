package it.localhostsoftware.maps.model;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleLatLngBounds;

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
            else return null;
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
