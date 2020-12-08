package it.localhostsoftware.maps.google;

import android.graphics.Point;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

import it.localhostsoftware.maps.CameraUpdate;
import it.localhostsoftware.maps.CameraUpdateFactory;
import it.localhostsoftware.maps.model.LatLng;


public class GoogleCameraUpdateFactory implements CameraUpdateFactory {
    @Override
    public CameraUpdate<?> zoomIn() {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.zoomIn());
    }

    @Override
    public CameraUpdate<?> zoomOut() {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.zoomOut());
    }

    @Override
    public CameraUpdate<?> scrollBy(float var0, float var1) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.scrollBy(var0, var1));
    }

    @Override
    public CameraUpdate<?> zoomTo(float var0) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.zoomTo(var0));
    }

    @Override
    public CameraUpdate<?> zoomBy(float var0) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.zoomBy(var0));
    }

    @Override
    public CameraUpdate<?> zoomBy(float var0, Point var1) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.zoomBy(var0, var1));
    }

    @Override
    public CameraUpdate<?> newCameraPosition(CameraPosition var0) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition(var0));
    }

    @Override
    public CameraUpdate<?> newLatLng(LatLng<?> var0) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.newLatLng((com.google.android.gms.maps.model.LatLng) var0.getLatLng()));
    }

    @Override
    public CameraUpdate<?> newLatLngZoom(LatLng<?> var0, float var1) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom((com.google.android.gms.maps.model.LatLng) var0.getLatLng(), var1));
    }

    @Override
    public CameraUpdate<?> newLatLngBounds(LatLngBounds var0, int var1) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(var0, var1));
    }

    @Override
    public CameraUpdate<?> newLatLngBounds(LatLngBounds var0, int var1, int var2, int var3) {
        return new CameraUpdate<>(com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(var0, var1, var2, var3));
    }
}
