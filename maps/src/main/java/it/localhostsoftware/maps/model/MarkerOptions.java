package it.localhostsoftware.maps.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleMarkerOptions;

public interface MarkerOptions {
    static MarkerOptions getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new GoogleMarkerOptions();
        else return null;
    }

    MarkerOptions position(@NonNull LatLng var1);

    MarkerOptions zIndex(float var1);

    MarkerOptions icon(@Nullable BitmapDescriptor<?> var1);

    MarkerOptions anchor(float var1, float var2);

    MarkerOptions infoWindowAnchor(float var1, float var2);

    MarkerOptions title(@Nullable String var1);

    MarkerOptions snippet(@Nullable String var1);

    MarkerOptions draggable(boolean var1);

    MarkerOptions visible(boolean var1);

    MarkerOptions flat(boolean var1);

    MarkerOptions rotation(float var1);

    MarkerOptions alpha(float var1);

    LatLng getPosition();

    String getTitle();

    String getSnippet();

    BitmapDescriptor<?> getIcon();

    float getAnchorU();

    float getAnchorV();

    boolean isDraggable();

    boolean isVisible();

    boolean isFlat();

    float getRotation();

    float getInfoWindowAnchorU();

    float getInfoWindowAnchorV();

    float getAlpha();

    float getZIndex();
}
