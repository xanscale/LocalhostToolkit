package it.localhostsoftware.maps.google;

import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import it.localhostsoftware.maps.CameraUpdate;
import it.localhostsoftware.maps.Map;
import it.localhostsoftware.maps.UiSettings;
import it.localhostsoftware.maps.google.model.GoogleCameraPosition;
import it.localhostsoftware.maps.google.model.GoogleLatLng;
import it.localhostsoftware.maps.google.model.GoogleMarker;
import it.localhostsoftware.maps.google.model.GoogleMarkerOptions;
import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.Marker;
import it.localhostsoftware.maps.model.MarkerOptions;

public class GoogleMap extends Map<com.google.android.gms.maps.GoogleMap> {
    public GoogleMap(com.google.android.gms.maps.GoogleMap googleMap) {
        super(googleMap);
    }

    @Override
    public CameraPosition<?> getCameraPosition() {
        return new GoogleCameraPosition(getMap().getCameraPosition());
    }

    @Override
    public float getMaxZoomLevel() {
        return getMap().getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        return getMap().getMinZoomLevel();
    }

    @Override
    public void moveCamera(CameraUpdate<?> var1) {
        getMap().moveCamera((com.google.android.gms.maps.CameraUpdate) var1.getCameraUpdate());
    }

    @Override
    public void animateCamera(CameraUpdate<?> var1) {
        getMap().animateCamera((com.google.android.gms.maps.CameraUpdate) var1.getCameraUpdate());
    }

    @Override
    public void animateCamera(CameraUpdate<?> var1, CancelableCallback var2) {
        getMap().animateCamera((com.google.android.gms.maps.CameraUpdate) var1.getCameraUpdate(), new com.google.android.gms.maps.GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                var2.onFinish();
            }

            @Override
            public void onCancel() {
                var2.onCancel();
            }
        });
    }

    @Override
    public void animateCamera(CameraUpdate<?> var1, int var2, CancelableCallback var3) {
        getMap().animateCamera((com.google.android.gms.maps.CameraUpdate) var1.getCameraUpdate(), var2, new com.google.android.gms.maps.GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                var3.onFinish();
            }

            @Override
            public void onCancel() {
                var3.onCancel();
            }
        });
    }

    @Override
    public void stopAnimation() {
        getMap().stopAnimation();
    }

    @Override
    public Marker<?> addMarker(MarkerOptions<?> var1) {
        return new GoogleMarker(getMap().addMarker(((GoogleMarkerOptions) var1).getMarkerOptions()));
    }

    @Override
    public void clear() {
        getMap().clear();
    }

    @Override
    public int getMapType() {
        return getMap().getMapType();
    }

    @Override
    public void setMapType(int i) {
        getMap().setMapType(i);
    }

    @Override
    public boolean isTrafficEnabled() {
        return getMap().isTrafficEnabled();
    }

    @Override
    public void setTrafficEnabled(boolean b) {
        getMap().setTrafficEnabled(b);
    }

    @Override
    public boolean isIndoorEnabled() {
        return getMap().isIndoorEnabled();
    }

    @Override
    public boolean setIndoorEnabled(boolean b) {
        return getMap().setIndoorEnabled(b);
    }

    @Override
    public boolean isBuildingsEnabled() {
        return getMap().isBuildingsEnabled();
    }

    @Override
    public void setBuildingsEnabled(boolean b) {
        getMap().setBuildingsEnabled(b);
    }

    @Override
    public boolean isMyLocationEnabled() {
        return getMap().isMyLocationEnabled();
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    @Override
    public void setMyLocationEnabled(boolean b) {
        getMap().setMyLocationEnabled(b);
    }

    @Override
    public UiSettings<?> getUiSettings() {
        return new GoogleUiSettings(getMap().getUiSettings());
    }

    @Override
    public void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener var1) {
        getMap().setOnCameraMoveStartedListener(var1::onCameraMoveStarted);
    }

    @Override
    public void setOnCameraMoveListener(@Nullable OnCameraMoveListener var1) {
        getMap().setOnCameraMoveListener(var1::onCameraMove);
    }

    @Override
    public void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener var1) {
        getMap().setOnCameraMoveCanceledListener(var1::onCameraMoveCanceled);
    }

    @Override
    public void setOnCameraIdleListener(@Nullable OnCameraIdleListener var1) {
        getMap().setOnCameraIdleListener(var1::onCameraIdle);
    }

    @Override
    public void setOnMapClickListener(@Nullable OnMapClickListener var1) {
        getMap().setOnMapClickListener(latLng -> var1.onMapClick(new GoogleLatLng(latLng)));
    }

    @Override
    public void setOnMapLongClickListener(@Nullable OnMapLongClickListener var1) {
        getMap().setOnMapLongClickListener(latLng -> var1.onMapLongClick(new GoogleLatLng(latLng)));
    }

    @Override
    public void setOnMarkerClickListener(@Nullable OnMarkerClickListener var1) {
        getMap().setOnMarkerClickListener(marker -> var1.onMarkerClick(new GoogleMarker(marker)));
    }

    @Override
    public void setOnMarkerDragListener(@Nullable OnMarkerDragListener var1) {
        getMap().setOnMarkerDragListener(new com.google.android.gms.maps.GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(com.google.android.gms.maps.model.Marker marker) {
                var1.onMarkerDragStart(new GoogleMarker(marker));
            }

            @Override
            public void onMarkerDrag(com.google.android.gms.maps.model.Marker marker) {
                var1.onMarkerDrag(new GoogleMarker(marker));
            }

            @Override
            public void onMarkerDragEnd(com.google.android.gms.maps.model.Marker marker) {
                var1.onMarkerDragEnd(new GoogleMarker(marker));
            }
        });
    }

    @Override
    public void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener var1) {
        getMap().setOnInfoWindowClickListener(marker -> var1.onInfoWindowClick(new GoogleMarker(marker)));
    }

    @Override
    public void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener var1) {
        getMap().setOnInfoWindowLongClickListener(marker -> var1.onInfoWindowLongClick(new GoogleMarker(marker)));

    }

    @Override
    public void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener var1) {
        getMap().setOnInfoWindowCloseListener(marker -> var1.onInfoWindowClose(new GoogleMarker(marker)));
    }

    @Override
    public void setInfoWindowAdapter(InfoWindowAdapter var1) {
        getMap().setInfoWindowAdapter(new com.google.android.gms.maps.GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(com.google.android.gms.maps.model.Marker marker) {
                return var1.getInfoWindow(new GoogleMarker(marker));
            }

            @Override
            public View getInfoContents(com.google.android.gms.maps.model.Marker marker) {
                return var1.getInfoContents(new GoogleMarker(marker));
            }
        });
    }

    @Override
    public void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener var1) {
        getMap().setOnMyLocationButtonClickListener(var1::onMyLocationButtonClick);
    }

    @Override
    public void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener var1) {
        getMap().setOnMyLocationClickListener(var1::onMyLocationClick);
    }

    @Override
    public void setOnMapLoadedCallback(OnMapLoadedCallback var1) {
        getMap().setOnMapLoadedCallback(var1::onMapLoaded);
    }

    @Override
    public void snapshot(SnapshotReadyCallback var1) {
        getMap().snapshot(var1::onSnapshotReady);
    }

    @Override
    public void snapshot(SnapshotReadyCallback var1, Bitmap var2) {
        getMap().snapshot(var1::onSnapshotReady, var2);
    }

    @Override
    public void setPadding(int i, int i1, int i2, int i3) {
        getMap().setPadding(i, i1, i2, i3);
    }

    @Override
    public void setContentDescription(String s) {
        getMap().setContentDescription(s);
    }

    @Override
    public void setMinZoomPreference(float v) {
        getMap().setMinZoomPreference(v);
    }

    @Override
    public void setMaxZoomPreference(float v) {
        getMap().setMaxZoomPreference(v);
    }

    @Override
    public void resetMinMaxZoomPreference() {
        getMap().resetMinMaxZoomPreference();
    }
}