package it.localhostsoftware.maps.google;

import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import com.google.android.gms.maps.model.CameraPosition;

import it.localhostsoftware.maps.CameraUpdate;
import it.localhostsoftware.maps.Map;
import it.localhostsoftware.maps.google.model.GoogleMarker;
import it.localhostsoftware.maps.model.Marker;
import it.localhostsoftware.maps.google.model.GoogleMarkerOptions;
import it.localhostsoftware.maps.model.MarkerOptions;
import it.localhostsoftware.maps.UiSettings;

public class GoogleMap implements Map {
    private final com.google.android.gms.maps.GoogleMap map;

    public GoogleMap(com.google.android.gms.maps.GoogleMap map) {
        this.map = map;
    }

    public CameraPosition getCameraPosition() {
        return map.getCameraPosition();
    }

    @Override
    public float getMaxZoomLevel() {
        return map.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        return map.getMinZoomLevel();
    }

    @Override
    public void moveCamera(CameraUpdate<?> var1) {
        map.moveCamera(((GoogleCameraUpdate) var1).getCameraUpdate());
    }

    @Override
    public void animateCamera(CameraUpdate<?> var1) {
        map.animateCamera(((GoogleCameraUpdate) var1).getCameraUpdate());
    }

    @Override
    public void animateCamera(CameraUpdate<?> var1, CancelableCallback var2) {
        map.animateCamera(((GoogleCameraUpdate) var1).getCameraUpdate(), new com.google.android.gms.maps.GoogleMap.CancelableCallback() {
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
        map.animateCamera(((GoogleCameraUpdate) var1).getCameraUpdate(), var2, new com.google.android.gms.maps.GoogleMap.CancelableCallback() {
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
        map.stopAnimation();
    }

    @Override
    public Marker addMarker(MarkerOptions var1) {
        return new GoogleMarker(map.addMarker(((GoogleMarkerOptions) var1).getMarkerOptions()));
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int getMapType() {
        return map.getMapType();
    }

    @Override
    public void setMapType(int i) {
        map.setMapType(i);
    }

    @Override
    public boolean isTrafficEnabled() {
        return map.isTrafficEnabled();
    }

    @Override
    public void setTrafficEnabled(boolean b) {
        map.setTrafficEnabled(b);
    }

    @Override
    public boolean isIndoorEnabled() {
        return map.isIndoorEnabled();
    }

    @Override
    public boolean setIndoorEnabled(boolean b) {
        return map.setIndoorEnabled(b);
    }

    @Override
    public boolean isBuildingsEnabled() {
        return map.isBuildingsEnabled();
    }

    @Override
    public void setBuildingsEnabled(boolean b) {
        map.setBuildingsEnabled(b);
    }

    @Override
    public boolean isMyLocationEnabled() {
        return map.isMyLocationEnabled();
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    @Override
    public void setMyLocationEnabled(boolean b) {
        map.setMyLocationEnabled(b);
    }

    @Override
    public UiSettings getUiSettings() {
        return new GoogleUiSettings(map.getUiSettings());
    }

    @Override
    public void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener var1) {
        map.setOnCameraMoveStartedListener(var1::onCameraMoveStarted);
    }

    @Override
    public void setOnCameraMoveListener(@Nullable OnCameraMoveListener var1) {
        map.setOnCameraMoveListener(var1::onCameraMove);
    }

    @Override
    public void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener var1) {
        map.setOnCameraMoveCanceledListener(var1::onCameraMoveCanceled);
    }

    @Override
    public void setOnCameraIdleListener(@Nullable OnCameraIdleListener var1) {
        map.setOnCameraIdleListener(var1::onCameraIdle);
    }

    @Override
    public void setOnMarkerClickListener(@Nullable OnMarkerClickListener var1) {
        map.setOnMarkerClickListener(marker -> var1.onMarkerClick(new GoogleMarker(marker)));
    }

    @Override
    public void setOnMarkerDragListener(@Nullable OnMarkerDragListener var1) {
        map.setOnMarkerDragListener(new com.google.android.gms.maps.GoogleMap.OnMarkerDragListener() {
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
        map.setOnInfoWindowClickListener(marker -> var1.onInfoWindowClick(new GoogleMarker(marker)));
    }

    @Override
    public void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener var1) {
        map.setOnInfoWindowLongClickListener(marker -> var1.onInfoWindowLongClick(new GoogleMarker(marker)));

    }

    @Override
    public void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener var1) {
        map.setOnInfoWindowCloseListener(marker -> var1.onInfoWindowClose(new GoogleMarker(marker)));
    }

    @Override
    public void setInfoWindowAdapter(InfoWindowAdapter var1) {
        map.setInfoWindowAdapter(new com.google.android.gms.maps.GoogleMap.InfoWindowAdapter() {
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
        map.setOnMyLocationButtonClickListener(var1::onMyLocationButtonClick);
    }

    @Override
    public void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener var1) {
        map.setOnMyLocationClickListener(var1::onMyLocationClick);
    }

    @Override
    public void setOnMapLoadedCallback(OnMapLoadedCallback var1) {
        map.setOnMapLoadedCallback(var1::onMapLoaded);
    }

    @Override
    public void snapshot(SnapshotReadyCallback var1) {
        map.snapshot(var1::onSnapshotReady);
    }

    @Override
    public void snapshot(SnapshotReadyCallback var1, Bitmap var2) {
        map.snapshot(var1::onSnapshotReady, var2);
    }

    @Override
    public void setPadding(int i, int i1, int i2, int i3) {
        map.setPadding(i, i1, i2, i3);
    }

    @Override
    public void setContentDescription(String s) {
        map.setContentDescription(s);
    }

    @Override
    public void setMinZoomPreference(float v) {
        map.setMinZoomPreference(v);
    }

    @Override
    public void setMaxZoomPreference(float v) {
        map.setMaxZoomPreference(v);
    }

    @Override
    public void resetMinMaxZoomPreference() {
        map.resetMinMaxZoomPreference();
    }
}