package it.localhostsoftware.maps

import android.graphics.Bitmap
import android.location.Location
import android.view.View
import androidx.annotation.RequiresPermission
import it.localhostsoftware.maps.model.*

abstract class GeoMap<M>(val map: M) {
    companion object {
        const val MAP_TYPE_NONE = 0
        const val MAP_TYPE_NORMAL = 1
        const val MAP_TYPE_SATELLITE = 2
        const val MAP_TYPE_TERRAIN = 3
        const val MAP_TYPE_HYBRID = 4
    }

    abstract val cameraPosition: CameraPosition<*>
    abstract val maxZoomLevel: Float
    abstract val minZoomLevel: Float
    abstract fun moveCamera(var1: CameraUpdate<*>)
    abstract fun animateCamera(var1: CameraUpdate<*>)
    abstract fun animateCamera(var1: CameraUpdate<*>, var2: CancelableCallback?)
    abstract fun animateCamera(var1: CameraUpdate<*>, var2: Int, var3: CancelableCallback?)
    abstract fun stopAnimation()
    abstract fun addPolyline(var1: PolylineOptions<*>): Polyline<*>
    abstract fun addPolygon(var1: PolygonOptions<*>): Polygon<*>
    abstract fun addCircle(var1: CircleOptions<*>): Circle<*>
    abstract fun addMarker(var1: MarkerOptions<*>): Marker<*>?

    //TODO  GroundOverlay addGroundOverlay(GroundOverlayOptions var1);
    //TODO  TileOverlay addTileOverlay(TileOverlayOptions var1);
    abstract fun clear()
    abstract val focusedBuilding: IndoorBuilding<*>?
    abstract fun setOnIndoorStateChangeListener(var1: OnIndoorStateChangeListener?)
    abstract var mapType: Int
    abstract var isTrafficEnabled: Boolean
    abstract val isIndoorEnabled: Boolean
    abstract fun setIndoorEnabled(var1: Boolean): Boolean
    abstract var isBuildingsEnabled: Boolean

    @set:RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    abstract var isMyLocationEnabled: Boolean
    abstract fun setLocationSource(var1: LocationSource?)
    abstract val uiSettings: UiSettings<*>
    abstract val projection: Projection<*>
    abstract fun setOnCameraMoveStartedListener(var1: OnCameraMoveStartedListener?)
    abstract fun setOnCameraMoveListener(var1: OnCameraMoveListener?)
    abstract fun setOnCameraMoveCanceledListener(var1: OnCameraMoveCanceledListener?)
    abstract fun setOnCameraIdleListener(var1: OnCameraIdleListener?)
    abstract fun setOnMapClickListener(var1: OnMapClickListener?)
    abstract fun setOnMapLongClickListener(var1: OnMapLongClickListener?)
    abstract fun setOnMarkerClickListener(var1: OnMarkerClickListener?)
    abstract fun setOnMarkerDragListener(var1: OnMarkerDragListener?)
    abstract fun setOnInfoWindowClickListener(var1: OnInfoWindowClickListener?)
    abstract fun setOnInfoWindowLongClickListener(var1: OnInfoWindowLongClickListener?)
    abstract fun setOnInfoWindowCloseListener(var1: OnInfoWindowCloseListener?)
    abstract fun setInfoWindowAdapter(var1: InfoWindowAdapter?)
    abstract fun setOnMyLocationButtonClickListener(var1: OnMyLocationButtonClickListener?)
    abstract fun setOnMyLocationClickListener(var1: OnMyLocationClickListener?)
    abstract fun setOnMapLoadedCallback(var1: OnMapLoadedCallback?)

    //TODO void setOnGroundOverlayClickListener(OnGroundOverlayClickListener var1);
    abstract fun setOnCircleClickListener(var1: OnCircleClickListener?)
    abstract fun setOnPolygonClickListener(var1: OnPolygonClickListener?)
    abstract fun setOnPolylineClickListener(var1: OnPolylineClickListener?)
    abstract fun snapshot(var1: SnapshotReadyCallback)
    abstract fun snapshot(var1: SnapshotReadyCallback, var2: Bitmap?)
    abstract fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int)
    abstract fun setContentDescription(var1: String?)
    abstract fun setOnPoiClickListener(var1: OnPoiClickListener?)
    abstract fun setMapStyle(var1: MapStyleOptions<*>?): Boolean
    abstract fun setMinZoomPreference(var1: Float)
    abstract fun setMaxZoomPreference(var1: Float)
    abstract fun resetMinMaxZoomPreference()
    abstract fun setLatLngBoundsForCameraTarget(var1: LatLngBounds<*>?)
    interface OnPoiClickListener {
        fun onPoiClick(var1: PointOfInterest<*>)
    }

    /* TODO interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay var1);
    }*/
    interface OnMapLoadedCallback {
        fun onMapLoaded()
    }

    interface OnMyLocationClickListener {
        fun onMyLocationClick(var1: Location)
    }

    interface OnMyLocationButtonClickListener {
        fun onMyLocationButtonClick(): Boolean
    }

    interface InfoWindowAdapter {
        fun getInfoWindow(var1: Marker<*>): View
        fun getInfoContents(var1: Marker<*>): View
    }

    interface SnapshotReadyCallback {
        fun onSnapshotReady(var1: Bitmap?)
    }

    interface CancelableCallback {
        fun onFinish()
        fun onCancel()
    }

    interface OnInfoWindowCloseListener {
        fun onInfoWindowClose(var1: Marker<*>)
    }

    interface OnInfoWindowLongClickListener {
        fun onInfoWindowLongClick(var1: Marker<*>)
    }

    interface OnInfoWindowClickListener {
        fun onInfoWindowClick(var1: Marker<*>)
    }

    interface OnMarkerDragListener {
        fun onMarkerDragStart(var1: Marker<*>)
        fun onMarkerDrag(var1: Marker<*>)
        fun onMarkerDragEnd(var1: Marker<*>)
    }

    interface OnMarkerClickListener {
        fun onMarkerClick(var1: Marker<*>): Boolean
    }

    interface OnPolylineClickListener {
        fun onPolylineClick(var1: Polyline<*>)
    }

    interface OnPolygonClickListener {
        fun onPolygonClick(var1: Polygon<*>)
    }

    interface OnCircleClickListener {
        fun onCircleClick(var1: Circle<*>)
    }

    interface OnCameraIdleListener {
        fun onCameraIdle()
    }

    interface OnCameraMoveCanceledListener {
        fun onCameraMoveCanceled()
    }

    interface OnCameraMoveListener {
        fun onCameraMove()
    }

    interface OnCameraMoveStartedListener {
        companion object {
            const val REASON_GESTURE = 1
            const val REASON_API_ANIMATION = 2
            const val REASON_DEVELOPER_ANIMATION = 3
        }

        fun onCameraMoveStarted(var1: Int)
    }

    interface OnMapLongClickListener {
        fun onMapLongClick(var1: LatLng<*>)
    }

    interface OnMapClickListener {
        fun onMapClick(var1: LatLng<*>)
    }

    interface OnIndoorStateChangeListener {
        fun onIndoorBuildingFocused()
        fun onIndoorLevelActivated(var1: IndoorBuilding<*>)
    }
}