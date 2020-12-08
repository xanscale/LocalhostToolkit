package it.localhostsoftware.maps.cameraUpdateFactory;

import android.content.Context;
import android.graphics.Point;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import it.localhostsoftware.maps.CameraUpdate;

public interface CameraUpdateFactory {
    static CameraUpdateFactory getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new GoogleCameraUpdateFactory();
        else return null;
    }

    CameraUpdate zoomIn();

    CameraUpdate zoomOut();

    CameraUpdate scrollBy(float var0, float var1);

    CameraUpdate zoomTo(float var0);

    CameraUpdate zoomBy(float var0);

    CameraUpdate zoomBy(float var0, Point var1);

    CameraUpdate newCameraPosition(CameraPosition var0);

    CameraUpdate newLatLng(LatLng var0);

    CameraUpdate newLatLngZoom(LatLng var0, float var1);

    CameraUpdate newLatLngBounds(LatLngBounds var0, int var1);

    CameraUpdate newLatLngBounds(LatLngBounds var0, int var1, int var2, int var3);
}
