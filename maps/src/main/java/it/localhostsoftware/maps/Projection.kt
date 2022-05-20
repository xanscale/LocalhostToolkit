package it.localhostsoftware.maps;

import android.graphics.Point;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.VisibleRegion;

public abstract class Projection<P> {
    private final P p;

    public Projection(P p) {
        this.p = p;
    }

    public P getProjection() {
        return p;
    }

    public abstract LatLng<?> fromScreenLocation(Point var1);

    public abstract Point toScreenLocation(LatLng<?> var1);

    public abstract VisibleRegion<?> getVisibleRegion();
}