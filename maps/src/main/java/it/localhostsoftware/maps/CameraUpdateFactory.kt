package it.localhostsoftware.maps;

import android.content.Context;
import android.graphics.Point;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

import it.localhostsoftware.maps.google.GoogleCameraUpdateFactory;
import it.localhostsoftware.maps.huawei.HuaweiCameraUpdateFactory;
import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.LatLngBounds;

public interface CameraUpdateFactory {
    static CameraUpdateFactory getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == com.google.android.gms.common.ConnectionResult.SUCCESS)
            return new GoogleCameraUpdateFactory();
        else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS)
            return new HuaweiCameraUpdateFactory();
        else throw new IllegalStateException();
    }

    CameraUpdate<?> zoomIn();

    CameraUpdate<?> zoomOut();

    CameraUpdate<?> scrollBy(float var0, float var1);

    CameraUpdate<?> zoomTo(float var0);

    CameraUpdate<?> zoomBy(float var0);

    CameraUpdate<?> zoomBy(float var0, Point var1);

    CameraUpdate<?> newCameraPosition(CameraPosition<?> var0);

    CameraUpdate<?> newLatLng(LatLng<?> var0);

    CameraUpdate<?> newLatLngZoom(LatLng<?> var0, float var1);

    CameraUpdate<?> newLatLngBounds(LatLngBounds<?> var0, int var1);

    CameraUpdate<?> newLatLngBounds(LatLngBounds<?> var0, int var1, int var2, int var3);
}
