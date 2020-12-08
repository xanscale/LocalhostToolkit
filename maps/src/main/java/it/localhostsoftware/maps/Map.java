package it.localhostsoftware.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import it.localhostsoftware.maps.model.Marker;
import it.localhostsoftware.maps.model.MarkerOptions;

public interface Map {
    int MAP_TYPE_NONE = 0;
    int MAP_TYPE_NORMAL = 1;
    int MAP_TYPE_SATELLITE = 2;
    int MAP_TYPE_TERRAIN = 3;
    int MAP_TYPE_HYBRID = 4;

    // CameraPosition getCameraPosition();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    void moveCamera(CameraUpdate<?> var1);

    void animateCamera(CameraUpdate<?> var1);

    void animateCamera(CameraUpdate<?> var1, CancelableCallback var2);

    void animateCamera(CameraUpdate<?> var1, int var2, CancelableCallback var3);

    void stopAnimation();

    //  Polyline addPolyline(PolylineOptions var1);

    // Polygon addPolygon(PolygonOptions var1);

    //  Circle addCircle(CircleOptions var1);

    Marker addMarker(MarkerOptions var1);

    //  GroundOverlay addGroundOverlay(GroundOverlayOptions var1);

    //  TileOverlay addTileOverlay(TileOverlayOptions var1);

    void clear();

    // IndoorBuilding getFocusedBuilding();

    //  void setOnIndoorStateChangeListener(OnIndoorStateChangeListener var1);

    int getMapType();

    void setMapType(int var1);

    boolean isTrafficEnabled();

    void setTrafficEnabled(boolean var1);

    boolean isIndoorEnabled();

    boolean setIndoorEnabled(boolean var1);

    boolean isBuildingsEnabled();

    void setBuildingsEnabled(boolean var1);

    boolean isMyLocationEnabled();

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    void setMyLocationEnabled(boolean var1);

    // void setLocationSource(LocationSource var1);

    UiSettings getUiSettings();

    // Projection getProjection();

    void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener var1);

    void setOnCameraMoveListener(@Nullable OnCameraMoveListener var1);

    void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener var1);

    void setOnCameraIdleListener(@Nullable OnCameraIdleListener var1);

    // void setOnMapClickListener(@Nullable OnMapClickListener var1);

    // void setOnMapLongClickListener(@Nullable OnMapLongClickListener var1);

    void setOnMarkerClickListener(@Nullable OnMarkerClickListener var1);

    void setOnMarkerDragListener(@Nullable OnMarkerDragListener var1);

    void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener var1);

    void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener var1);

    void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener var1);

    void setInfoWindowAdapter(InfoWindowAdapter var1);

    void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener var1);

    void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener var1);

    void setOnMapLoadedCallback(OnMapLoadedCallback var1);

    // void setOnGroundOverlayClickListener(OnGroundOverlayClickListener var1);

    // void setOnCircleClickListener(OnCircleClickListener var1);

    // void setOnPolygonClickListener(OnPolygonClickListener var1);

    // void setOnPolylineClickListener(OnPolylineClickListener var1);

    void snapshot(SnapshotReadyCallback var1);

    void snapshot(SnapshotReadyCallback var1, Bitmap var2);

    void setPadding(int var1, int var2, int var3, int var4);

    void setContentDescription(String var1);

    // void setOnPoiClickListener(OnPoiClickListener var1);

    // boolean setMapStyle(@Nullable MapStyleOptions var1);

    void setMinZoomPreference(float var1);

    void setMaxZoomPreference(float var1);

    void resetMinMaxZoomPreference();

    // void setLatLngBoundsForCameraTarget(LatLngBounds var1);

    /* interface OnPoiClickListener {
        void onPoiClick(PointOfInterest var1);
    }*/

    /* interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay var1);
    }*/

    interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    interface OnMyLocationClickListener {
        void onMyLocationClick(@NonNull Location var1);
    }

    interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    interface InfoWindowAdapter {
        View getInfoWindow(Marker var1);

        View getInfoContents(Marker var1);
    }

    interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap var1);
    }

    interface CancelableCallback {
        void onFinish();

        void onCancel();
    }

    interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker var1);
    }

    interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker var1);
    }

    interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker var1);
    }

    interface OnMarkerDragListener {
        void onMarkerDragStart(Marker var1);

        void onMarkerDrag(Marker var1);

        void onMarkerDragEnd(Marker var1);
    }

    interface OnMarkerClickListener {
        boolean onMarkerClick(Marker var1);
    }

    /* interface OnPolylineClickListener {
        void onPolylineClick(Polyline var1);
    } */

    /* interface OnPolygonClickListener {
        void onPolygonClick(Polygon var1);
    } */

    /* interface OnCircleClickListener {
        void onCircleClick(Circle var1);
    } */

    interface OnCameraIdleListener {
        void onCameraIdle();
    }

    interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    interface OnCameraMoveListener {
        void onCameraMove();
    }

    interface OnCameraMoveStartedListener {
        int REASON_GESTURE = 1;
        int REASON_API_ANIMATION = 2;
        int REASON_DEVELOPER_ANIMATION = 3;

        void onCameraMoveStarted(int var1);
    }

    /* interface OnMapLongClickListener {
        void onMapLongClick(LatLng var1);
    } */

    /* interface OnMapClickListener {
        void onMapClick(LatLng var1);
    } */

    /* interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(IndoorBuilding var1);
    }*/
}
