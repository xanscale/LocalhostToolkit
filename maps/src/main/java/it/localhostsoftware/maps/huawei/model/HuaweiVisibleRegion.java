package it.localhostsoftware.maps.huawei.model;

import androidx.annotation.NonNull;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.LatLngBounds;
import it.localhostsoftware.maps.model.VisibleRegion;

public class HuaweiVisibleRegion extends VisibleRegion<com.huawei.hms.maps.model.VisibleRegion> {

    public HuaweiVisibleRegion(com.huawei.hms.maps.model.VisibleRegion VR) {
        super(VR);
    }

    @Override
    public LatLng<?> getNearLeft() {
        return new HuaweiLatLng(getVisibleRegion().nearLeft);
    }

    @Override
    public LatLng<?> getNearRight() {
        return new HuaweiLatLng(getVisibleRegion().nearRight);
    }

    @Override
    public LatLng<?> getFarLeft() {
        return new HuaweiLatLng(getVisibleRegion().farLeft);
    }

    @Override
    public LatLng<?> getFarRight() {
        return new HuaweiLatLng(getVisibleRegion().farRight);
    }

    @Override
    public LatLngBounds<?> getLatLngBounds() {
        return new HuaweiLatLngBounds(getVisibleRegion().latLngBounds);
    }

    @Override
    public int hashCode() {
        return getVisibleRegion().hashCode();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object var1) {
        return getVisibleRegion().equals(var1);
    }

    @NonNull
    @Override
    public String toString() {
        return getVisibleRegion().toString();
    }
}
