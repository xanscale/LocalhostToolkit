package it.localhostsoftware.maps.huawei;

import android.graphics.Point;

import it.localhostsoftware.maps.CameraUpdate;
import it.localhostsoftware.maps.CameraUpdateFactory;
import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.LatLngBounds;

public class HuaweiCameraUpdateFactory implements CameraUpdateFactory {
    @Override
    public CameraUpdate<?> zoomIn() {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.zoomIn());
    }

    @Override
    public CameraUpdate<?> zoomOut() {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.zoomOut());
    }

    @Override
    public CameraUpdate<?> scrollBy(float var0, float var1) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.scrollBy(var0, var1));
    }

    @Override
    public CameraUpdate<?> zoomTo(float var0) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.zoomTo(var0));
    }

    @Override
    public CameraUpdate<?> zoomBy(float var0) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.zoomBy(var0));
    }

    @Override
    public CameraUpdate<?> zoomBy(float var0, Point var1) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.zoomBy(var0, var1));
    }

    @Override
    public CameraUpdate<?> newCameraPosition(CameraPosition<?> var0) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.newCameraPosition((com.huawei.hms.maps.model.CameraPosition) var0.getCameraPosition()));
    }

    @Override
    public CameraUpdate<?> newLatLng(LatLng<?> var0) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.newLatLng((com.huawei.hms.maps.model.LatLng) var0.getLatLng()));
    }

    @Override
    public CameraUpdate<?> newLatLngZoom(LatLng<?> var0, float var1) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.newLatLngZoom((com.huawei.hms.maps.model.LatLng) var0.getLatLng(), var1));
    }

    @Override
    public CameraUpdate<?> newLatLngBounds(LatLngBounds<?> var0, int var1) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds((com.huawei.hms.maps.model.LatLngBounds) var0.getLatLngBounds(), var1));
    }

    @Override
    public CameraUpdate<?> newLatLngBounds(LatLngBounds<?> var0, int var1, int var2, int var3) {
        return new CameraUpdate<>(com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds((com.huawei.hms.maps.model.LatLngBounds) var0.getLatLngBounds(), var1, var2, var3));
    }
}
