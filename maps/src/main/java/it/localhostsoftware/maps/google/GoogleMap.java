package it.localhostsoftware.maps.google;

import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import it.localhostsoftware.maps.CameraUpdate;
import it.localhostsoftware.maps.LocationSource;
import it.localhostsoftware.maps.Map;
import it.localhostsoftware.maps.UiSettings;
import it.localhostsoftware.maps.google.model.GoogleCameraPosition;
import it.localhostsoftware.maps.google.model.GoogleCircle;
import it.localhostsoftware.maps.google.model.GoogleIndoorBuilding;
import it.localhostsoftware.maps.google.model.GoogleLatLng;
import it.localhostsoftware.maps.google.model.GoogleMarker;
import it.localhostsoftware.maps.google.model.GoogleMarkerOptions;
import it.localhostsoftware.maps.google.model.GooglePointOfInterest;
import it.localhostsoftware.maps.google.model.GooglePolyline;
import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.Circle;
import it.localhostsoftware.maps.model.CircleOptions;
import it.localhostsoftware.maps.model.IndoorBuilding;
import it.localhostsoftware.maps.model.LatLngBounds;
import it.localhostsoftware.maps.model.Marker;
import it.localhostsoftware.maps.model.MarkerOptions;
import it.localhostsoftware.maps.model.Polyline;
import it.localhostsoftware.maps.model.PolylineOptions;

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
    public Polyline<?> addPolyline(PolylineOptions<?> var1) {
        return new GooglePolyline(getMap().addPolyline((com.google.android.gms.maps.model.PolylineOptions) var1.getPolylineOptions()));
    }

    @Override
    public Circle<?> addCircle(CircleOptions<?> var1) {
        return new GoogleCircle(getMap().addCircle((com.google.android.gms.maps.model.CircleOptions) var1.getCircleOptions()));
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
    public IndoorBuilding<?> getFocusedBuilding() {
        return new GoogleIndoorBuilding(getMap().getFocusedBuilding());
    }

    @Override
    public void setOnIndoorStateChangeListener(OnIndoorStateChangeListener var1) {
        if (var1 == null)
            getMap().setOnIndoorStateChangeListener(null);
        else {
            getMap().setOnIndoorStateChangeListener(new com.google.android.gms.maps.GoogleMap.OnIndoorStateChangeListener() {
                @Override
                public void onIndoorBuildingFocused() {
                    var1.onIndoorBuildingFocused();
                }

                @Override
                public void onIndoorLevelActivated(com.google.android.gms.maps.model.IndoorBuilding indoorBuilding) {
                    var1.onIndoorLevelActivated(new GoogleIndoorBuilding(indoorBuilding));
                }
            });
        }
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
    public void setLocationSource(LocationSource var1) {
        getMap().setLocationSource(var1 == null ? null : new com.google.android.gms.maps.LocationSource() {
            @Override
            public void activate(OnLocationChangedListener onLocationChangedListener) {
                var1.activate(onLocationChangedListener::onLocationChanged);
            }

            @Override
            public void deactivate() {
                var1.deactivate();
            }
        });
    }

    @Override
    public UiSettings<?> getUiSettings() {
        return new GoogleUiSettings(getMap().getUiSettings());
    }

    @Override
    public void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener var1) {
        getMap().setOnCameraMoveStartedListener(var1 == null ? null : var1::onCameraMoveStarted);
    }

    @Override
    public void setOnCameraMoveListener(@Nullable OnCameraMoveListener var1) {
        getMap().setOnCameraMoveListener(var1 == null ? null : var1::onCameraMove);
    }

    @Override
    public void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener var1) {
        getMap().setOnCameraMoveCanceledListener(var1 == null ? null : var1::onCameraMoveCanceled);
    }

    @Override
    public void setOnCameraIdleListener(@Nullable OnCameraIdleListener var1) {
        getMap().setOnCameraIdleListener(var1 == null ? null : var1::onCameraIdle);
    }

    @Override
    public void setOnMapClickListener(@Nullable OnMapClickListener var1) {
        getMap().setOnMapClickListener(var1 == null ? null : latLng -> var1.onMapClick(new GoogleLatLng(latLng)));
    }

    @Override
    public void setOnMapLongClickListener(@Nullable OnMapLongClickListener var1) {
        getMap().setOnMapLongClickListener(var1 == null ? null : latLng -> var1.onMapLongClick(new GoogleLatLng(latLng)));
    }

    @Override
    public void setOnMarkerClickListener(@Nullable OnMarkerClickListener var1) {
        getMap().setOnMarkerClickListener(var1 == null ? null : marker -> var1.onMarkerClick(new GoogleMarker(marker)));
    }

    @Override
    public void setOnMarkerDragListener(@Nullable OnMarkerDragListener var1) {
        getMap().setOnMarkerDragListener(var1 == null ? null : new com.google.android.gms.maps.GoogleMap.OnMarkerDragListener() {
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
        getMap().setOnInfoWindowClickListener(var1 == null ? null : marker -> var1.onInfoWindowClick(new GoogleMarker(marker)));
    }

    @Override
    public void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener var1) {
        getMap().setOnInfoWindowLongClickListener(var1 == null ? null : marker -> var1.onInfoWindowLongClick(new GoogleMarker(marker)));

    }

    @Override
    public void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener var1) {
        getMap().setOnInfoWindowCloseListener(var1 == null ? null : marker -> var1.onInfoWindowClose(new GoogleMarker(marker)));
    }

    @Override
    public void setInfoWindowAdapter(@Nullable InfoWindowAdapter var1) {
        getMap().setInfoWindowAdapter(var1 == null ? null : new com.google.android.gms.maps.GoogleMap.InfoWindowAdapter() {
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
        getMap().setOnMyLocationButtonClickListener(var1 == null ? null : var1::onMyLocationButtonClick);
    }

    @Override
    public void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener var1) {
        getMap().setOnMyLocationClickListener(var1 == null ? null : var1::onMyLocationClick);
    }

    @Override
    public void setOnMapLoadedCallback(@Nullable OnMapLoadedCallback var1) {
        getMap().setOnMapLoadedCallback(var1 == null ? null : var1::onMapLoaded);
    }

    @Override
    public void setOnCircleClickListener(OnCircleClickListener var1) {
        getMap().setOnCircleClickListener(var1 == null ? null : circle -> var1.onCircleClick(new GoogleCircle(circle)));

    }

    @Override
    public void setOnPolylineClickListener(Map.OnPolylineClickListener var1) {
        getMap().setOnPolylineClickListener(var1 == null ? null : polyline -> var1.onPolylineClick(new GooglePolyline(polyline)));
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
    public void setOnPoiClickListener(OnPoiClickListener var1) {
        getMap().setOnPoiClickListener(var1 == null ? null : pointOfInterest -> var1.onPoiClick(new GooglePointOfInterest(pointOfInterest)));
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

    @Override
    public void setLatLngBoundsForCameraTarget(LatLngBounds<?> var1) {
        getMap().setLatLngBoundsForCameraTarget(var1 == null ? null : (com.google.android.gms.maps.model.LatLngBounds) var1.getLatLngBounds());
    }
}