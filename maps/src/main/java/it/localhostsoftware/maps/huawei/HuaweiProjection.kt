package it.localhostsoftware.maps.huawei;

import android.graphics.Point;

import it.localhostsoftware.maps.Projection;
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng;
import it.localhostsoftware.maps.huawei.model.HuaweiVisibleRegion;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.VisibleRegion;

public class HuaweiProjection extends Projection<com.huawei.hms.maps.Projection> {
    public HuaweiProjection(com.huawei.hms.maps.Projection projection) {
        super(projection);
    }

    @Override
    public LatLng<?> fromScreenLocation(Point var1) {
        return new HuaweiLatLng(getProjection().fromScreenLocation(var1));
    }

    @Override
    public Point toScreenLocation(LatLng<?> var1) {
        return getProjection().toScreenLocation((com.huawei.hms.maps.model.LatLng) var1.getLatLng());
    }

    @Override
    public VisibleRegion<?> getVisibleRegion() {
        return new HuaweiVisibleRegion(getProjection().getVisibleRegion());
    }
}
