package it.localhostsoftware.maps.model.bitmapDescriptorFactory;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import it.localhostsoftware.maps.model.bitmapDescriptor.BitmapDescriptor;

public interface BitmapDescriptorFactory {
    float HUE_RED = 0.0F;
    float HUE_ORANGE = 30.0F;
    float HUE_YELLOW = 60.0F;
    float HUE_GREEN = 120.0F;
    float HUE_CYAN = 180.0F;
    float HUE_AZURE = 210.0F;
    float HUE_BLUE = 240.0F;
    float HUE_VIOLET = 270.0F;
    float HUE_MAGENTA = 300.0F;
    float HUE_ROSE = 330.0F;

    static BitmapDescriptorFactory getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new GoogleBitmapDescriptorFactory();
        else return null;
    }

    BitmapDescriptor fromResource(int var0);

    BitmapDescriptor fromAsset(String var0);

    BitmapDescriptor fromFile(String var0);

    BitmapDescriptor fromPath(String var0);

    BitmapDescriptor defaultMarker();

    BitmapDescriptor defaultMarker(float var0);

    BitmapDescriptor fromBitmap(Bitmap var0);
}
