package it.localhostsoftware.maps.model;

public abstract class CameraPosition<CP> {
    private final CP cp;

    public CameraPosition(CP cp) {
        this.cp = cp;
    }

    public CP getCameraPosition() {
        return cp;
    }

    abstract public LatLng<?> getTarget();

    abstract public float getZoom();

    abstract public float getTilt();

    abstract public float getBearing();
}
