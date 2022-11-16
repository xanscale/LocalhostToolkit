package it.localhostsoftware.maps.google

import android.graphics.Bitmap
import android.view.View
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.*
import it.localhostsoftware.maps.GeoMap
import it.localhostsoftware.maps.Projection
import it.localhostsoftware.maps.UiSettings
import it.localhostsoftware.maps.google.model.*
import it.localhostsoftware.maps.model.MarkerOptions

class GoogleMap(googleMap: GoogleMap) : GeoMap<GoogleMap, GoogleCameraUpdate, GoogleCameraPosition, GooglePolylineOptions, GooglePolyline, GooglePolygonOptions, GooglePolygon, GoogleCircleOptions, GoogleCircle>(googleMap) {
    override val cameraPosition: GoogleCameraPosition
        get() = GoogleCameraPosition(map.cameraPosition)
    override val maxZoomLevel: Float
        get() = map.maxZoomLevel
    override val minZoomLevel: Float
        get() = map.minZoomLevel

    override fun moveCamera(var1: GoogleCameraUpdate) =
        map.moveCamera(var1.cu)

    override fun animateCamera(var1: GoogleCameraUpdate) =
        map.animateCamera(var1.cu)

    override fun animateCamera(var1: GoogleCameraUpdate, var2: CancelableCallback?) =
        map.animateCamera(var1.cu, var2?.let {
            object : GoogleMap.CancelableCallback {
                override fun onFinish() = it.onFinish()
                override fun onCancel() = it.onCancel()
            }
        })

    override fun animateCamera(var1: GoogleCameraUpdate, var2: Int, var3: CancelableCallback?) =
        map.animateCamera(var1.cu, var2, var3?.let {
            object : GoogleMap.CancelableCallback {
                override fun onFinish() = it.onFinish()
                override fun onCancel() = it.onCancel()
            }
        })

    override fun stopAnimation() =
        map.stopAnimation()

    override fun addPolyline(var1: GooglePolylineOptions) =
        GooglePolyline(map.addPolyline(var1.po))

    override fun addPolygon(var1: GooglePolygonOptions) =
        GooglePolygon(map.addPolygon(var1.po))

    override fun addCircle(var1: GoogleCircleOptions) =
        GoogleCircle(map.addCircle(var1.co))

    override fun addMarker(var1: MarkerOptions<*>) =
        map.addMarker((var1 as GoogleMarkerOptions).mo)?.let { GoogleMarker(it) }

    override fun clear() =
        map.clear()

    override val focusedBuilding: it.localhostsoftware.maps.model.IndoorBuilding<*>?
        get() = map.focusedBuilding?.let { GoogleIndoorBuilding(it) }

    override fun setOnIndoorStateChangeListener(var1: OnIndoorStateChangeListener?) =
        map.setOnIndoorStateChangeListener(var1?.let {
            object : GoogleMap.OnIndoorStateChangeListener {
                override fun onIndoorBuildingFocused() = it.onIndoorBuildingFocused()
                override fun onIndoorLevelActivated(indoorBuilding: IndoorBuilding) = it.onIndoorLevelActivated(GoogleIndoorBuilding(indoorBuilding))
            }
        })

    override var mapType: Int
        get() = map.mapType
        set(i) {
            map.mapType = i
        }
    override var isTrafficEnabled: Boolean
        get() = map.isTrafficEnabled
        set(b) {
            map.isTrafficEnabled = b
        }
    override val isIndoorEnabled: Boolean
        get() = map.isIndoorEnabled

    override fun setIndoorEnabled(var1: Boolean) =
        map.setIndoorEnabled(var1)

    override var isBuildingsEnabled: Boolean
        get() = map.isBuildingsEnabled
        set(b) {
            map.isBuildingsEnabled = b
        }

    @set:RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override var isMyLocationEnabled: Boolean
        get() = map.isMyLocationEnabled
        set(b) {
            map.isMyLocationEnabled = b
        }

    override fun setLocationSource(var1: it.localhostsoftware.maps.LocationSource?) =
        map.setLocationSource(var1?.let { ls ->
            object : LocationSource {
                override fun deactivate() = ls.deactivate()
                override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) =
                    var1.activate(it.localhostsoftware.maps.LocationSource.OnLocationChangedListener { onLocationChangedListener.onLocationChanged(it) })
            }
        })

    override val uiSettings: UiSettings<*>
        get() = GoogleUiSettings(map.uiSettings)
    override val projection: Projection<*>
        get() = GoogleProjection(map.projection)

    override fun setOnCameraMoveStartedListener(var1: OnCameraMoveStartedListener?) =
        map.setOnCameraMoveStartedListener(var1?.let { GoogleMap.OnCameraMoveStartedListener { var1.onCameraMoveStarted(it) } })

    override fun setOnCameraMoveListener(var1: OnCameraMoveListener?) =
        map.setOnCameraMoveListener(var1?.let { GoogleMap.OnCameraMoveListener { var1.onCameraMove() } })

    override fun setOnCameraMoveCanceledListener(var1: OnCameraMoveCanceledListener?) =
        map.setOnCameraMoveCanceledListener(var1?.let { GoogleMap.OnCameraMoveCanceledListener { var1.onCameraMoveCanceled() } })

    override fun setOnCameraIdleListener(var1: OnCameraIdleListener?) =
        map.setOnCameraIdleListener(var1?.let { GoogleMap.OnCameraIdleListener { var1.onCameraIdle() } })

    override fun setOnMapClickListener(var1: OnMapClickListener?) =
        map.setOnMapClickListener(var1?.let { GoogleMap.OnMapClickListener { var1.onMapClick(GoogleLatLng(it)) } })

    override fun setOnMapLongClickListener(var1: OnMapLongClickListener?) =
        map.setOnMapLongClickListener(var1?.let { GoogleMap.OnMapLongClickListener { var1.onMapLongClick(GoogleLatLng(it)) } })

    override fun setOnMarkerClickListener(var1: OnMarkerClickListener?) =
        map.setOnMarkerClickListener(var1?.let { GoogleMap.OnMarkerClickListener { var1.onMarkerClick(GoogleMarker(it)) } })

    override fun setOnMarkerDragListener(var1: OnMarkerDragListener?) =
        map.setOnMarkerDragListener(var1?.let {
            object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragStart(marker: Marker) = var1.onMarkerDragStart(GoogleMarker(marker))
                override fun onMarkerDrag(marker: Marker) = var1.onMarkerDrag(GoogleMarker(marker))
                override fun onMarkerDragEnd(marker: Marker) = var1.onMarkerDragEnd(GoogleMarker(marker))
            }
        })

    override fun setOnInfoWindowClickListener(var1: OnInfoWindowClickListener?) =
        map.setOnInfoWindowClickListener(var1?.let { GoogleMap.OnInfoWindowClickListener { var1.onInfoWindowClick(GoogleMarker(it)) } })

    override fun setOnInfoWindowLongClickListener(var1: OnInfoWindowLongClickListener?) =
        map.setOnInfoWindowLongClickListener(var1?.let { GoogleMap.OnInfoWindowLongClickListener { var1.onInfoWindowLongClick(GoogleMarker(it)) } })

    override fun setOnInfoWindowCloseListener(var1: OnInfoWindowCloseListener?) =
        map.setOnInfoWindowCloseListener(var1?.let { GoogleMap.OnInfoWindowCloseListener { var1.onInfoWindowClose(GoogleMarker(it)) } })

    override fun setInfoWindowAdapter(var1: InfoWindowAdapter?) =
        map.setInfoWindowAdapter(var1?.let {
            object : GoogleMap.InfoWindowAdapter {
                override fun getInfoWindow(marker: Marker): View = var1.getInfoWindow(GoogleMarker(marker))
                override fun getInfoContents(marker: Marker): View = var1.getInfoContents(GoogleMarker(marker))
            }
        })

    override fun setOnMyLocationButtonClickListener(var1: OnMyLocationButtonClickListener?) =
        map.setOnMyLocationButtonClickListener(var1?.let { GoogleMap.OnMyLocationButtonClickListener { var1.onMyLocationButtonClick() } })

    override fun setOnMyLocationClickListener(var1: OnMyLocationClickListener?) =
        map.setOnMyLocationClickListener(var1?.let { GoogleMap.OnMyLocationClickListener { var1.onMyLocationClick(it) } })

    override fun setOnMapLoadedCallback(var1: OnMapLoadedCallback?) =
        map.setOnMapLoadedCallback(var1?.let { GoogleMap.OnMapLoadedCallback { var1.onMapLoaded() } })

    override fun setOnCircleClickListener(var1: OnCircleClickListener?) =
        map.setOnCircleClickListener(var1?.let { GoogleMap.OnCircleClickListener { var1.onCircleClick(GoogleCircle(it)) } })

    override fun setOnPolygonClickListener(var1: OnPolygonClickListener?) =
        map.setOnPolygonClickListener(var1?.let { GoogleMap.OnPolygonClickListener { var1.onPolygonClick(GooglePolygon(it)) } })

    override fun setOnPolylineClickListener(var1: OnPolylineClickListener?) =
        map.setOnPolylineClickListener(var1?.let { GoogleMap.OnPolylineClickListener { var1.onPolylineClick(GooglePolyline(it)) } })

    override fun snapshot(var1: SnapshotReadyCallback) =
        map.snapshot { var1.onSnapshotReady(it) }

    override fun snapshot(var1: SnapshotReadyCallback, var2: Bitmap?) =
        map.snapshot({ var1.onSnapshotReady(it) }, var2)

    override fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int) =
        map.setPadding(var1, var2, var3, var4)

    override fun setContentDescription(var1: String?) =
        map.setContentDescription(var1)

    override fun setOnPoiClickListener(var1: OnPoiClickListener?) =
        map.setOnPoiClickListener(var1?.let { GoogleMap.OnPoiClickListener { var1.onPoiClick(GooglePointOfInterest(it)) } })

    override fun setMapStyle(var1: it.localhostsoftware.maps.model.MapStyleOptions<*>?) =
        map.setMapStyle(var1?.let { var1.mso as MapStyleOptions })

    override fun setMinZoomPreference(var1: Float) =
        map.setMinZoomPreference(var1)

    override fun setMaxZoomPreference(var1: Float) =
        map.setMaxZoomPreference(var1)

    override fun resetMinMaxZoomPreference() =
        map.resetMinMaxZoomPreference()

    override fun setLatLngBoundsForCameraTarget(var1: it.localhostsoftware.maps.model.LatLngBounds<*>?) =
        map.setLatLngBoundsForCameraTarget(var1?.let { var1.lb as LatLngBounds })
}