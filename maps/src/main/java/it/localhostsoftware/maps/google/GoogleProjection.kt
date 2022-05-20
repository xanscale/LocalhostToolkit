package it.localhostsoftware.maps.google;

import android.graphics.Point;

import it.localhostsoftware.maps.Projection;
import it.localhostsoftware.maps.google.model.GoogleLatLng;
import it.localhostsoftware.maps.google.model.GoogleVisibleRegion;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.VisibleRegion;

public class GoogleProjection extends Projection<com.google.android.gms.maps.Projection> {
    public GoogleProjection(com.google.android.gms.maps.Projection projection) {
        super(projection);
    }

    @Override
    public LatLng<?> fromScreenLocation(Point var1) {
        return new GoogleLatLng(getProjection().fromScreenLocation(var1));
    }

    @Override
    public Point toScreenLocation(LatLng<?> var1) {
        return getProjection().toScreenLocation((com.google.android.gms.maps.model.LatLng) var1.getLatLng());
    }

    @Override
    public VisibleRegion<?> getVisibleRegion() {
        return new GoogleVisibleRegion(getProjection().getVisibleRegion());
    }
}
