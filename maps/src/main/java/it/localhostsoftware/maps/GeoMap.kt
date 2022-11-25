package it.localhostsoftware.maps

import android.graphics.Bitmap
import android.location.Location
import android.view.View
import androidx.annotation.RequiresPermission
import it.localhostsoftware.maps.model.*

abstract class GeoMap<M, CU : CameraUpdate<*>, CP : CameraPosition<*, *>, PLO : PolylineOptions<*, *, *, *>, PL : Polyline<*, *, *, *>, PGO : PolygonOptions<*, *, *>, PG : Polygon<*, *, *>, CO : CircleOptions<*, *, *>, C : Circle<*, *, *>, MRO : MarkerOptions<*, *, *>, MR : Marker<*, *, *>, IB : IndoorBuilding<*, *>, US : UiSettings<*>, PJ : Projection<*, *>, MSO : MapStyleOptions<*>, LL : LatLng<*>, LLB : LatLngBounds<*, *>, POI: PointOfInterest<*, *>>(
    val map: M
) {
    companion object {
        const val MAP_TYPE_NONE = 0
        const val MAP_TYPE_NORMAL = 1
        const val MAP_TYPE_SATELLITE = 2
        const val MAP_TYPE_TERRAIN = 3
        const val MAP_TYPE_HYBRID = 4
        const val REASON_GESTURE = 1
        const val REASON_API_ANIMATION = 2
        const val REASON_DEVELOPER_ANIMATION = 3
    }

    abstract val cameraPosition: CP
    abstract val maxZoomLevel: Float
    abstract val minZoomLevel: Float
    abstract fun moveCamera(var1: CU)
    abstract fun animateCamera(var1: CU)
    abstract fun animateCamera(var1: CU, var2: CancelableCallback?)
    abstract fun animateCamera(var1: CU, var2: Int, var3: CancelableCallback?)
    abstract fun stopAnimation()
    abstract fun addPolyline(var1: PLO): PL
    abstract fun addPolygon(var1: PGO): PG
    abstract fun addCircle(var1: CO): C
    abstract fun addMarker(var1: MRO): MR?

    //TODO  GroundOverlay addGroundOverlay(GroundOverlayOptions var1);
    //TODO  TileOverlay addTileOverlay(TileOverlayOptions var1);
    abstract fun clear()
    abstract val focusedBuilding: IB?
    abstract fun setOnIndoorStateChangeListener(var1: OnIndoorStateChangeListener?)
    abstract var mapType: Int
    abstract var isTrafficEnabled: Boolean
    abstract val isIndoorEnabled: Boolean
    abstract fun setIndoorEnabled(var1: Boolean): Boolean
    abstract var isBuildingsEnabled: Boolean

    @set:RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    abstract var isMyLocationEnabled: Boolean
    abstract fun setLocationSource(var1: LocationSource?)
    abstract val uiSettings: US
    abstract val projection: PJ
    abstract fun setOnCameraMoveStartedListener(block: ((Int) -> Unit)?)
    abstract fun setOnCameraMoveListener(block: (() -> Unit)?)
    abstract fun setOnCameraMoveCanceledListener(block: (() -> Unit)?)
    abstract fun setOnCameraIdleListener(block: (() -> Unit)?)
    abstract fun setOnMapClickListener(block: ((LL) -> Unit)?)
    abstract fun setOnMapLongClickListener(block: ((LL) -> Unit)?)
    abstract fun setOnMarkerClickListener(block: ((MR) -> Boolean)?)
    abstract fun setOnMarkerDragListener(var1: OnMarkerDragListener?)
    abstract fun setOnInfoWindowClickListener(block: ((MR) -> Unit)?)
    abstract fun setOnInfoWindowLongClickListener(block: ((MR) -> Unit)?)
    abstract fun setOnInfoWindowCloseListener(block: ((MR) -> Unit)?)
    abstract fun setInfoWindowAdapter(var1: InfoWindowAdapter?)
    abstract fun setOnMyLocationButtonClickListener(block: (() -> Boolean)?)
    abstract fun setOnMyLocationClickListener(block: ((Location) -> Unit)?)
    abstract fun setOnMapLoadedCallback(block: (() -> Unit)?)

    //TODO void setOnGroundOverlayClickListener(OnGroundOverlayClickListener var1);
    abstract fun setOnCircleClickListener(block: ((C) -> Unit)?)
    abstract fun setOnPolygonClickListener(block: ((PG) -> Unit)?)
    abstract fun setOnPolylineClickListener(block: ((PL) -> Unit)?)
    abstract fun snapshot(block: ((Bitmap?) -> Unit))
    abstract fun snapshot(block: ((Bitmap?) -> Unit), var2: Bitmap?)
    abstract fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int)
    abstract fun setContentDescription(var1: String?)
    abstract fun setOnPoiClickListener(block: ((POI) -> Unit)?)
    abstract fun setMapStyle(var1: MSO?): Boolean
    abstract fun setMinZoomPreference(var1: Float)
    abstract fun setMaxZoomPreference(var1: Float)
    abstract fun resetMinMaxZoomPreference()
    abstract fun setLatLngBoundsForCameraTarget(var1: LLB?)

    /* TODO interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay var1);
    }*/
    interface InfoWindowAdapter {
        fun getInfoWindow(var1: Marker<*, *, *>): View
        fun getInfoContents(var1: Marker<*, *, *>): View
    }

    interface CancelableCallback {
        fun onFinish()
        fun onCancel()
    }

    interface OnMarkerDragListener {
        fun onMarkerDragStart(var1: Marker<*, *, *>)
        fun onMarkerDrag(var1: Marker<*, *, *>)
        fun onMarkerDragEnd(var1: Marker<*, *, *>)
    }

    interface OnIndoorStateChangeListener {
        fun onIndoorBuildingFocused()
        fun onIndoorLevelActivated(var1: IndoorBuilding<*, *>)
    }
}