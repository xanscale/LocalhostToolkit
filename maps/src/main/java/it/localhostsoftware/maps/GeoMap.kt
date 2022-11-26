package it.localhostsoftware.maps

import android.graphics.Bitmap
import android.location.Location
import android.view.View
import androidx.annotation.RequiresPermission
import it.localhostsoftware.maps.model.*

abstract class GeoMap<M, CU : CameraUpdate<*>, CP : CameraPosition<*, *>, PLO : PolylineOptions<*, *, *, *>, PL : Polyline<*, *, *, *>, PGO : PolygonOptions<*, *, *>, PG : Polygon<*, *, *>, CO : CircleOptions<*, *, *>, C : Circle<*, *, *>, MRO : MarkerOptions<*, *, *>, MR : Marker<*, *, *>, IB : IndoorBuilding<*, *>, US : UiSettings<*>, PJ : Projection<*, *>, MSO : MapStyleOptions<*>, LL : LatLng<*>, LLB : LatLngBounds<*, *>, POI : PointOfInterest<*, *>, GOO : GroundOverlayOptions<*, *, *, *>, GO : GroundOverlay<*, *, *, *>, TOO : TileOverlayOptions<*, *>, TO : TileOverlay<*>>(
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
    abstract fun animateCamera(var1: CU, onFinish: (() -> Unit)?, onCancel: (() -> Unit)?)
    abstract fun animateCamera(var1: CU, var2: Int, onFinish: (() -> Unit)?, onCancel: (() -> Unit)?)
    abstract fun stopAnimation()
    abstract fun addPolyline(var1: PLO): PL
    abstract fun addPolygon(var1: PGO): PG
    abstract fun addCircle(var1: CO): C
    abstract fun addMarker(var1: MRO): MR?
    abstract fun addGroundOverlay(var1: GOO): GO?
    abstract fun addTileOverlay(var1: TOO): TO?
    abstract fun clear()
    abstract val focusedBuilding: IB?
    abstract fun setOnIndoorStateChangeListener(onIndoorBuildingFocused: (() -> Unit)?, onIndoorLevelActivated: ((IB) -> Unit)?)
    abstract var mapType: Int
    abstract var isTrafficEnabled: Boolean
    abstract var isIndoorEnabled: Boolean
    abstract var isBuildingsEnabled: Boolean

    @set:RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    abstract var isMyLocationEnabled: Boolean
    abstract fun setLocationSource(activate: (((Location) -> Unit) -> Unit)?, deactivate: (() -> Unit)?)
    abstract val uiSettings: US
    abstract val projection: PJ
    abstract fun setOnCameraMoveStartedListener(onCameraMoveStarted: ((Int) -> Unit)?)
    abstract fun setOnCameraMoveListener(onCameraMove: (() -> Unit)?)
    abstract fun setOnCameraMoveCanceledListener(onCameraMoveCanceled: (() -> Unit)?)
    abstract fun setOnCameraIdleListener(onCameraIdle: (() -> Unit)?)
    abstract fun setOnMapClickListener(onMapClick: ((LL) -> Unit)?)
    abstract fun setOnMapLongClickListener(onMapLongClick: ((LL) -> Unit)?)
    abstract fun setOnMarkerClickListener(onMarkerClick: ((MR) -> Boolean)?)
    abstract fun setOnMarkerDragListener(onMarkerDragStart: ((MR) -> Unit)?, onMarkerDrag: ((MR) -> Unit)?, onMarkerDragEnd: ((MR) -> Unit)?)
    abstract fun setOnInfoWindowClickListener(onInfoWindowClick: ((MR) -> Unit)?)
    abstract fun setOnInfoWindowLongClickListener(onInfoWindowLongClick: ((MR) -> Unit)?)
    abstract fun setOnInfoWindowCloseListener(onInfoWindowClose: ((MR) -> Unit)?)
    abstract fun setInfoWindowAdapter(getInfoWindow: ((MR) -> View)?, getInfoContents: ((MR) -> View)?)
    abstract fun setOnMyLocationButtonClickListener(onMyLocationButtonClick: (() -> Boolean)?)
    abstract fun setOnMyLocationClickListener(onMyLocationClick: ((Location) -> Unit)?)
    abstract fun setOnMapLoadedCallback(onMapLoaded: (() -> Unit)?)
    abstract fun setOnGroundOverlayClickListener(onGroundOverlayClick: (GO) -> Unit)
    abstract fun setOnCircleClickListener(onCircleClick: ((C) -> Unit)?)
    abstract fun setOnPolygonClickListener(onPolygonClick: ((PG) -> Unit)?)
    abstract fun setOnPolylineClickListener(onPolylineClick: ((PL) -> Unit)?)
    abstract fun snapshot(onSnapshotReady: ((Bitmap?) -> Unit))
    abstract fun snapshot(onSnapshotReady: ((Bitmap?) -> Unit), var2: Bitmap?)
    abstract fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int)
    abstract fun setContentDescription(var1: String?)
    abstract fun setOnPoiClickListener(onPoiClick: ((POI) -> Unit)?)
    abstract fun setMapStyle(var1: MSO?): Boolean
    abstract fun setMinZoomPreference(var1: Float)
    abstract fun setMaxZoomPreference(var1: Float)
    abstract fun resetMinMaxZoomPreference()
    abstract fun setLatLngBoundsForCameraTarget(var1: LLB?)
}