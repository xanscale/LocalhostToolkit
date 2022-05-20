package it.localhostsoftware.maps.huawei.model;

import it.localhostsoftware.maps.model.LatLng;

public class HuaweiLatLng extends LatLng<com.huawei.hms.maps.model.LatLng> {
    public HuaweiLatLng(com.huawei.hms.maps.model.LatLng latLng) {
        super(latLng);
    }

    @Override
    public double getLatitude() {
        return getLatLng().latitude;
    }

    @Override
    public double getLongitude() {
        return getLatLng().longitude;
    }
}
