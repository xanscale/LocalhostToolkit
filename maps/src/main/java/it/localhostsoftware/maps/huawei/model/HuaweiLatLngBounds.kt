package it.localhostsoftware.maps.huawei.model;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.LatLngBounds;

public class HuaweiLatLngBounds extends LatLngBounds<com.huawei.hms.maps.model.LatLngBounds> {
    public HuaweiLatLngBounds(com.huawei.hms.maps.model.LatLngBounds latLngBounds) {
        super(latLngBounds);
    }

    @Override
    public LatLng<?> getSouthwest() {
        return new HuaweiLatLng(getLatLngBounds().southwest);
    }

    @Override
    public LatLng<?> getNortheast() {
        return new HuaweiLatLng(getLatLngBounds().northeast);
    }

    @Override
    public boolean contains(LatLng<?> var1) {
        return getLatLngBounds().contains((com.huawei.hms.maps.model.LatLng) var1.getLatLng());
    }

    @Override
    public LatLngBounds<?> including(LatLng<?> var1) {
        getLatLngBounds().including((com.huawei.hms.maps.model.LatLng) var1.getLatLng());
        return this;
    }

    @Override
    public LatLng<?> getCenter() {
        return new HuaweiLatLng(getLatLngBounds().getCenter());
    }

    public static class HuaweiBuilder extends Builder<com.huawei.hms.maps.model.LatLngBounds.Builder> {
        public HuaweiBuilder(com.huawei.hms.maps.model.LatLngBounds.Builder builder) {
            super(builder);
        }

        @Override
        public Builder<?> include(LatLng<?> var1) {
            getBuilder().include((com.huawei.hms.maps.model.LatLng) var1.getLatLng());
            return this;
        }

        @Override
        public LatLngBounds<?> build() {
            return new HuaweiLatLngBounds(getBuilder().build());
        }
    }
}
