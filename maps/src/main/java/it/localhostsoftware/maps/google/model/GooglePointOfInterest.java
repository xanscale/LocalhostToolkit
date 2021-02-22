package it.localhostsoftware.maps.google.model;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PointOfInterest;

public class GooglePointOfInterest extends PointOfInterest<com.google.android.gms.maps.model.PointOfInterest> {
    public GooglePointOfInterest(com.google.android.gms.maps.model.PointOfInterest POI) {
        super(POI);
    }

    @Override
    public LatLng<?> getLatLng() {
        return new GoogleLatLng(getPointOfInterest().latLng);
    }

    @Override
    public String getPlaceId() {
        return getPointOfInterest().placeId;
    }

    @Override
    public String getName() {
        return getPointOfInterest().name;
    }
}
