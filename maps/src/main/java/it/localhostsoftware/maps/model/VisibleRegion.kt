package it.localhostsoftware.maps.model;

import androidx.annotation.NonNull;

public abstract class VisibleRegion<VR> {
    private final VR VR;

    public VisibleRegion(VR VR) {
        this.VR = VR;
    }

    public VR getVisibleRegion() {
        return VR;
    }

    public abstract LatLng<?> getNearLeft();

    public abstract LatLng<?> getNearRight();

    public abstract LatLng<?> getFarLeft();

    public abstract LatLng<?> getFarRight();

    public abstract LatLngBounds<?> getLatLngBounds();

    public abstract int hashCode();

    public abstract boolean equals(Object var1);

    @NonNull
    public abstract String toString();
}