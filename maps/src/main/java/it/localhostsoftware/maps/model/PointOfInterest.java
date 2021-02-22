package it.localhostsoftware.maps.model;

public abstract class PointOfInterest<POI> {
    private final POI POI;

    public PointOfInterest(POI POI) {
        this.POI = POI;
    }

    public POI getPointOfInterest() {
        return POI;
    }

    public abstract LatLng<?> getLatLng();

    public abstract String getPlaceId();

    public abstract String getName();
}