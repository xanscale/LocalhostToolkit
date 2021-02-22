package it.localhostsoftware.maps.google.model;

import androidx.annotation.NonNull;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.LatLngBounds;
import it.localhostsoftware.maps.model.VisibleRegion;

public class GoogleVisibleRegion extends VisibleRegion<com.google.android.gms.maps.model.VisibleRegion> {

    public GoogleVisibleRegion(com.google.android.gms.maps.model.VisibleRegion VR) {
        super(VR);
    }

    @Override
    public LatLng<?> getNearLeft() {
        return new GoogleLatLng(getVisibleRegion().nearLeft);
    }

    @Override
    public LatLng<?> getNearRight() {
        return new GoogleLatLng(getVisibleRegion().nearRight);
    }

    @Override
    public LatLng<?> getFarLeft() {
        return new GoogleLatLng(getVisibleRegion().farLeft);
    }

    @Override
    public LatLng<?> getFarRight() {
        return new GoogleLatLng(getVisibleRegion().farRight);
    }

    @Override
    public LatLngBounds<?> getLatLngBounds() {
        return new GoogleLatLngBounds(getVisibleRegion().latLngBounds);
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
