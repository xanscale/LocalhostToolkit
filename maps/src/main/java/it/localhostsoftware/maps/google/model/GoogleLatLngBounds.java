package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.LatLngBounds;

public class GoogleLatLngBounds extends LatLngBounds<com.google.android.gms.maps.model.LatLngBounds> {
    public GoogleLatLngBounds(com.google.android.gms.maps.model.LatLngBounds latLngBounds) {
        super(latLngBounds);
    }

    @Override
    public LatLng<?> getSouthwest() {
        return new GoogleLatLng(getLatLngBounds().southwest);
    }

    @Override
    public LatLng<?> getNortheast() {
        return new GoogleLatLng(getLatLngBounds().northeast);
    }

    @Override
    public boolean contains(LatLng<?> var1) {
        return getLatLngBounds().contains((com.google.android.gms.maps.model.LatLng) var1.getLatLng());
    }

    @Override
    public LatLngBounds<?> including(LatLng<?> var1) {
        getLatLngBounds().including((com.google.android.gms.maps.model.LatLng) var1.getLatLng());
        return this;
    }

    @Override
    public LatLng<?> getCenter() {
        return new GoogleLatLng(getLatLngBounds().getCenter());
    }

    public static class GoogleBuilder extends Builder<com.google.android.gms.maps.model.LatLngBounds.Builder> {
        public GoogleBuilder(com.google.android.gms.maps.model.LatLngBounds.Builder builder) {
            super(builder);
        }

        @Override
        public Builder<?> include(LatLng<?> var1) {
            getBuilder().include((com.google.android.gms.maps.model.LatLng) var1.getLatLng());
            return this;
        }

        @Override
        public LatLngBounds<?> build() {
            return new GoogleLatLngBounds(getBuilder().build());
        }
    }
}
