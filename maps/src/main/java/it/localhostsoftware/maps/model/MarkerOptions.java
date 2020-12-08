package it.localhostsoftware.maps.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import it.localhostsoftware.maps.google.model.GoogleMarkerOptions;

public abstract class MarkerOptions<MO> {
    static MarkerOptions<?> getInstance(Context context) {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS)
            return new GoogleMarkerOptions();
        else return null;
    }

    private final MO mo;

    public MarkerOptions(MO mo) {
        this.mo = mo;
    }

    public MO getMarkerOptions() {
        return mo;
    }

    abstract public MarkerOptions<?> position(@NonNull LatLng<?> var1);

    abstract public MarkerOptions<?> zIndex(float var1);

    abstract public MarkerOptions<?> icon(@Nullable BitmapDescriptor<?> var1);

    abstract public MarkerOptions<?> anchor(float var1, float var2);

    abstract public MarkerOptions<?> infoWindowAnchor(float var1, float var2);

    abstract public MarkerOptions<?> title(@Nullable String var1);

    abstract public MarkerOptions<?> snippet(@Nullable String var1);

    abstract public MarkerOptions<?> draggable(boolean var1);

    abstract public MarkerOptions<?> visible(boolean var1);

    abstract public MarkerOptions<?> flat(boolean var1);

    abstract public MarkerOptions<?> rotation(float var1);

    abstract public MarkerOptions<?> alpha(float var1);

    abstract public LatLng<?> getPosition();

    abstract public String getTitle();

    abstract public String getSnippet();

    abstract public BitmapDescriptor<?> getIcon();

    abstract public float getAnchorU();

    abstract public float getAnchorV();

    abstract public boolean isDraggable();

    abstract public boolean isVisible();

    abstract public boolean isFlat();

    abstract public float getRotation();

    abstract public float getInfoWindowAnchorU();

    abstract public float getInfoWindowAnchorV();

    abstract public float getAlpha();

    abstract public float getZIndex();
}
