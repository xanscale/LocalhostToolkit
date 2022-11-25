package it.localhostsoftware.maps.google

import android.graphics.Bitmap
import android.location.Location
import android.view.View
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.IndoorBuilding
import com.google.android.gms.maps.model.Marker
import it.localhostsoftware.maps.GeoMap
import it.localhostsoftware.maps.google.model.*

class GoogleMap(googleMap: GoogleMap) : GeoMap<GoogleMap, GoogleCameraUpdate, GoogleCameraPosition, GooglePolylineOptions, GooglePolyline, GooglePolygonOptions, GooglePolygon, GoogleCircleOptions, GoogleCircle, GoogleMarkerOptions, GoogleMarker, GoogleIndoorBuilding, GoogleUiSettings, GoogleProjection, GoogleMapStyleOptions, GoogleLatLng, GoogleLatLngBounds, GooglePointOfInterest>(
    googleMap
) {
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

    override fun addMarker(var1: GoogleMarkerOptions) =
        map.addMarker(var1.mo)?.let { GoogleMarker(it) }

    override fun clear() =
        map.clear()

    override val focusedBuilding: GoogleIndoorBuilding?
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

    override val uiSettings: GoogleUiSettings
        get() = GoogleUiSettings(map.uiSettings)
    override val projection: GoogleProjection
        get() = GoogleProjection(map.projection)

    override fun setOnCameraMoveStartedListener(block: ((Int) -> Unit)?) =
        map.setOnCameraMoveStartedListener(block?.let { { block(it) } })

    override fun setOnCameraMoveListener(block: (() -> Unit)?) =
        map.setOnCameraMoveListener(block?.let { { block() } })

    override fun setOnCameraMoveCanceledListener(block: (() -> Unit)?) =
        map.setOnCameraMoveCanceledListener(block?.let { { block() } })

    override fun setOnCameraIdleListener(block: (() -> Unit)?) =
        map.setOnCameraIdleListener(block?.let { { block() } })

    override fun setOnMapClickListener(block: ((GoogleLatLng) -> Unit)?) =
        map.setOnMapClickListener(block?.let { { block(GoogleLatLng(it)) } })

    override fun setOnMapLongClickListener(block: ((GoogleLatLng) -> Unit)?) =
        map.setOnMapLongClickListener(block?.let { { block(GoogleLatLng(it)) } })

    override fun setOnMarkerClickListener(block: ((GoogleMarker) -> Boolean)?) =
        map.setOnMarkerClickListener(block?.let { { block(GoogleMarker(it)) } })

    override fun setOnMarkerDragListener(var1: OnMarkerDragListener?) =
        map.setOnMarkerDragListener(var1?.let {
            object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragStart(marker: Marker) = var1.onMarkerDragStart(GoogleMarker(marker))
                override fun onMarkerDrag(marker: Marker) = var1.onMarkerDrag(GoogleMarker(marker))
                override fun onMarkerDragEnd(marker: Marker) = var1.onMarkerDragEnd(GoogleMarker(marker))
            }
        })

    override fun setOnInfoWindowClickListener(block: ((GoogleMarker) -> Unit)?) =
        map.setOnInfoWindowClickListener(block?.let { { block(GoogleMarker(it)) } })

    override fun setOnInfoWindowLongClickListener(block: ((GoogleMarker) -> Unit)?) =
        map.setOnInfoWindowLongClickListener(block?.let { { block(GoogleMarker(it)) } })

    override fun setOnInfoWindowCloseListener(block: ((GoogleMarker) -> Unit)?) =
        map.setOnInfoWindowCloseListener(block?.let { { block(GoogleMarker(it)) } })

    override fun setInfoWindowAdapter(var1: InfoWindowAdapter?) =
        map.setInfoWindowAdapter(var1?.let {
            object : GoogleMap.InfoWindowAdapter {
                override fun getInfoWindow(marker: Marker): View = var1.getInfoWindow(GoogleMarker(marker))
                override fun getInfoContents(marker: Marker): View = var1.getInfoContents(GoogleMarker(marker))
            }
        })

    override fun setOnMyLocationButtonClickListener(block: (() -> Boolean)?) =
        map.setOnMyLocationButtonClickListener(block?.let { { block() } })

    override fun setOnMyLocationClickListener(block: ((Location) -> Unit)?) =
        map.setOnMyLocationClickListener(block?.let { { block(it) } })

    override fun setOnMapLoadedCallback(block: (() -> Unit)?) =
        map.setOnMapLoadedCallback(block?.let { { block() } })

    override fun setOnCircleClickListener(block: ((GoogleCircle) -> Unit)?) =
        map.setOnCircleClickListener(block?.let { { block(GoogleCircle(it)) } })

    override fun setOnPolygonClickListener(block: ((GooglePolygon) -> Unit)?) =
        map.setOnPolygonClickListener(block?.let { { block(GooglePolygon(it)) } })

    override fun setOnPolylineClickListener(block: ((GooglePolyline) -> Unit)?) =
        map.setOnPolylineClickListener(block?.let { { block(GooglePolyline(it)) } })

    override fun snapshot(block: ((Bitmap?) -> Unit)) =
        map.snapshot { block(it) }

    override fun snapshot(block: ((Bitmap?) -> Unit), var2: Bitmap?) =
        map.snapshot({ block(it) }, var2)

    override fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int) =
        map.setPadding(var1, var2, var3, var4)

    override fun setContentDescription(var1: String?) =
        map.setContentDescription(var1)

    override fun setOnPoiClickListener(block: ((GooglePointOfInterest) -> Unit)?) =
        map.setOnPoiClickListener(block?.let { { block(GooglePointOfInterest(it)) } })

    override fun setMapStyle(var1: GoogleMapStyleOptions?) =
        map.setMapStyle(var1?.let { var1.mso })

    override fun setMinZoomPreference(var1: Float) =
        map.setMinZoomPreference(var1)

    override fun setMaxZoomPreference(var1: Float) =
        map.setMaxZoomPreference(var1)

    override fun resetMinMaxZoomPreference() =
        map.resetMinMaxZoomPreference()

    override fun setLatLngBoundsForCameraTarget(var1: GoogleLatLngBounds?) =
        map.setLatLngBoundsForCameraTarget(var1?.let { var1.lb })
}