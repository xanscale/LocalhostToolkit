package it.localhostsoftware.maps.huawei.model;

import android.graphics.Bitmap;

import it.localhostsoftware.maps.model.BitmapDescriptor;
import it.localhostsoftware.maps.model.BitmapDescriptorFactory;

public class HuaweiBitmapDescriptorFactory implements BitmapDescriptorFactory {
    @Override
    public BitmapDescriptor<?> fromResource(int var0) {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(var0));
    }

    @Override
    public BitmapDescriptor<?> fromAsset(String var0) {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromAsset(var0));
    }

    @Override
    public BitmapDescriptor<?> fromFile(String var0) {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromFile(var0));
    }

    @Override
    public BitmapDescriptor<?> fromPath(String var0) {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromPath(var0));
    }

    @Override
    public BitmapDescriptor<?> defaultMarker() {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker());
    }

    @Override
    public BitmapDescriptor<?> defaultMarker(float var0) {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(var0));
    }

    @Override
    public BitmapDescriptor<?> fromBitmap(Bitmap var0) {
        return new BitmapDescriptor<>(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(var0));
    }
}
