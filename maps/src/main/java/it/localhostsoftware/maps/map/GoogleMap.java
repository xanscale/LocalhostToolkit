package it.localhostsoftware.maps.map;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import com.google.android.gms.maps.model.CameraPosition;

import it.localhostsoftware.maps.CameraUpdate;

public class GoogleMap implements AbstractMap {
    private com.google.android.gms.maps.GoogleMap map;

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
    public void moveCamera(CameraUpdate var1) {
        map.moveCamera(var1.getGoogleCameraUpdate());
    }

    @Override
    public void animateCamera(CameraUpdate var1) {
        map.animateCamera(var1.getGoogleCameraUpdate());
    }

    @Override
    public void animateCamera(CameraUpdate var1, CancelableCallback var2) {
        map.animateCamera(var1.getGoogleCameraUpdate(), new com.google.android.gms.maps.GoogleMap.CancelableCallback() {
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
    public void animateCamera(CameraUpdate var1, int var2, CancelableCallback var3) {
        map.animateCamera(var1.getGoogleCameraUpdate(), var2, new com.google.android.gms.maps.GoogleMap.CancelableCallback() {
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