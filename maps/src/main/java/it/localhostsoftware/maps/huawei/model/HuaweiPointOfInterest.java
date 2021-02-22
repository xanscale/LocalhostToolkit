package it.localhostsoftware.maps.huawei.model;

import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.PointOfInterest;

public class HuaweiPointOfInterest extends PointOfInterest<com.huawei.hms.maps.model.PointOfInterest> {
    public HuaweiPointOfInterest(com.huawei.hms.maps.model.PointOfInterest POI) {
        super(POI);
    }

    @Override
    public LatLng<?> getLatLng() {
        return new HuaweiLatLng(getPointOfInterest().latLng);
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
