package it.localhostsoftware.maps.model.bitmapDescriptorFactory;

import android.graphics.Bitmap;

import it.localhostsoftware.maps.model.bitmapDescriptor.BitmapDescriptor;
import it.localhostsoftware.maps.model.bitmapDescriptor.GoogleBitmapDescriptor;

public class GoogleBitmapDescriptorFactory implements BitmapDescriptorFactory {
    @Override
    public BitmapDescriptor fromResource(int var0) {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(var0));
    }

    @Override
    public BitmapDescriptor fromAsset(String var0) {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromAsset(var0));
    }

    @Override
    public BitmapDescriptor fromFile(String var0) {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromFile(var0));
    }

    @Override
    public BitmapDescriptor fromPath(String var0) {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromPath(var0));
    }

    @Override
    public BitmapDescriptor defaultMarker() {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker());
    }

    @Override
    public BitmapDescriptor defaultMarker(float var0) {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(var0));
    }

    @Override
    public BitmapDescriptor fromBitmap(Bitmap var0) {
        return new GoogleBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(var0));
    }
}
