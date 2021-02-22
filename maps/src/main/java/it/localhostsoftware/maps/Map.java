package it.localhostsoftware.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

import it.localhostsoftware.maps.model.CameraPosition;
import it.localhostsoftware.maps.model.Circle;
import it.localhostsoftware.maps.model.CircleOptions;
import it.localhostsoftware.maps.model.IndoorBuilding;
import it.localhostsoftware.maps.model.LatLng;
import it.localhostsoftware.maps.model.Marker;
import it.localhostsoftware.maps.model.MarkerOptions;
import it.localhostsoftware.maps.model.Polyline;
import it.localhostsoftware.maps.model.PolylineOptions;

public abstract class Map<M> {
    int MAP_TYPE_NONE = 0;
    int MAP_TYPE_NORMAL = 1;
    int MAP_TYPE_SATELLITE = 2;
    int MAP_TYPE_TERRAIN = 3;
    int MAP_TYPE_HYBRID = 4;

    private final M m;

    public Map(M m) {
        this.m = m;
    }

    public M getMap() {
        return m;
    }

    abstract public CameraPosition<?> getCameraPosition();

    abstract public float getMaxZoomLevel();

    abstract public float getMinZoomLevel();

    abstract public void moveCamera(CameraUpdate<?> var1);

    abstract public void animateCamera(CameraUpdate<?> var1);

    abstract public void animateCamera(CameraUpdate<?> var1, CancelableCallback var2);

    abstract public void animateCamera(CameraUpdate<?> var1, int var2, CancelableCallback var3);

    abstract public void stopAnimation();

    abstract public Polyline<?> addPolyline(PolylineOptions<?> var1);

    //TODO Polygon addPolygon(PolygonOptions var1);

    abstract public Circle<?> addCircle(CircleOptions<?> var1);

    abstract public Marker<?> addMarker(MarkerOptions<?> var1);

    //TODO  GroundOverlay addGroundOverlay(GroundOverlayOptions var1);

    //TODO  TileOverlay addTileOverlay(TileOverlayOptions var1);

    abstract public void clear();

    abstract public IndoorBuilding<?> getFocusedBuilding();

    abstract public void setOnIndoorStateChangeListener(OnIndoorStateChangeListener var1);

    abstract public int getMapType();

    abstract public void setMapType(int var1);

    abstract public boolean isTrafficEnabled();

    abstract public void setTrafficEnabled(boolean var1);

    abstract public boolean isIndoorEnabled();

    abstract public boolean setIndoorEnabled(boolean var1);

    abstract public boolean isBuildingsEnabled();

    abstract public void setBuildingsEnabled(boolean var1);

    abstract public boolean isMyLocationEnabled();

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    abstract public void setMyLocationEnabled(boolean var1);

    //TODO void setLocationSource(LocationSource var1);

    abstract public UiSettings<?> getUiSettings();

    //TODO Projection getProjection();

    abstract public void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener var1);

    abstract public void setOnCameraMoveListener(@Nullable OnCameraMoveListener var1);

    abstract public void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener var1);

    abstract public void setOnCameraIdleListener(@Nullable OnCameraIdleListener var1);

    abstract public void setOnMapClickListener(@Nullable OnMapClickListener var1);

    abstract public void setOnMapLongClickListener(@Nullable OnMapLongClickListener var1);

    abstract public void setOnMarkerClickListener(@Nullable OnMarkerClickListener var1);

    abstract public void setOnMarkerDragListener(@Nullable OnMarkerDragListener var1);

    abstract public void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener var1);

    abstract public void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener var1);

    abstract public void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener var1);

    abstract public void setInfoWindowAdapter(@Nullable InfoWindowAdapter var1);

    abstract public void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener var1);

    abstract public void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener var1);

    abstract public void setOnMapLoadedCallback(@Nullable OnMapLoadedCallback var1);

    //TODO void setOnGroundOverlayClickListener(OnGroundOverlayClickListener var1);

    abstract public void setOnCircleClickListener(OnCircleClickListener var1);

    //TODO void setOnPolygonClickListener(OnPolygonClickListener var1);

    abstract public void setOnPolylineClickListener(OnPolylineClickListener var1);

    abstract public void snapshot(SnapshotReadyCallback var1);

    abstract public void snapshot(SnapshotReadyCallback var1, Bitmap var2);

    abstract public void setPadding(int var1, int var2, int var3, int var4);

    abstract public void setContentDescription(String var1);

    //TODO void setOnPoiClickListener(OnPoiClickListener var1);

    //TODO boolean setMapStyle(@Nullable MapStyleOptions var1);

    abstract public void setMinZoomPreference(float var1);

    abstract public void setMaxZoomPreference(float var1);

    abstract public void resetMinMaxZoomPreference();

    //TODO void setLatLngBoundsForCameraTarget(LatLngBounds var1);

    /* TODO interface OnPoiClickListener {
        void onPoiClick(PointOfInterest var1);
    }*/

    /* TODO interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay var1);
    }*/

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMyLocationClickListener {
        void onMyLocationClick(@NonNull Location var1);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    public interface InfoWindowAdapter {
        View getInfoWindow(Marker<?> var1);

        View getInfoContents(Marker<?> var1);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap var1);
    }

    public interface CancelableCallback {
        void onFinish();

        void onCancel();
    }

    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker<?> var1);
    }

    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker<?> var1);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker<?> var1);
    }

    public interface OnMarkerDragListener {
        void onMarkerDragStart(Marker<?> var1);

        void onMarkerDrag(Marker<?> var1);

        void onMarkerDragEnd(Marker<?> var1);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker<?> var1);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline<?> var1);
    }

    /* TODO interface OnPolygonClickListener {
        void onPolygonClick(Polygon var1);
    } */

    public interface OnCircleClickListener {
        void onCircleClick(Circle<?> var1);
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    public interface OnCameraMoveStartedListener {
        int REASON_GESTURE = 1;
        int REASON_API_ANIMATION = 2;
        int REASON_DEVELOPER_ANIMATION = 3;

        void onCameraMoveStarted(int var1);
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng<?> var1);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng<?> var1);
    }

    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(IndoorBuilding<?> var1);
    }
}
