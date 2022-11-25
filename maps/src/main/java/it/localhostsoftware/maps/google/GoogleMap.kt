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

    override fun animateCamera(var1: GoogleCameraUpdate, onFinish: (() -> Unit)?, onCancel: (() -> Unit)?) =
        map.animateCamera(var1.cu, if (onFinish != null && onCancel != null) object : GoogleMap.CancelableCallback {
            override fun onFinish() = onFinish()
            override fun onCancel() = onCancel()
        } else null)

    override fun animateCamera(var1: GoogleCameraUpdate, var2: Int, onFinish: (() -> Unit)?, onCancel: (() -> Unit)?) =
        map.animateCamera(var1.cu, var2, if (onFinish != null && onCancel != null) object : GoogleMap.CancelableCallback {
            override fun onFinish() = onFinish()
            override fun onCancel() = onCancel()
        } else null)

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

    override fun setOnIndoorStateChangeListener(onIndoorBuildingFocused: (() -> Unit)?, onIndoorLevelActivated: ((GoogleIndoorBuilding) -> Unit)?) =
        map.setOnIndoorStateChangeListener(if (onIndoorBuildingFocused != null && onIndoorLevelActivated != null) object : GoogleMap.OnIndoorStateChangeListener {
            override fun onIndoorBuildingFocused() = onIndoorBuildingFocused()
            override fun onIndoorLevelActivated(indoorBuilding: IndoorBuilding) = onIndoorLevelActivated(GoogleIndoorBuilding(indoorBuilding))
        } else null)

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
    override var isIndoorEnabled: Boolean
        get() = map.isIndoorEnabled
        set(b) {
            map.isIndoorEnabled = b
        }
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

    override fun setOnCameraMoveStartedListener(onCameraMoveStarted: ((Int) -> Unit)?) =
        map.setOnCameraMoveStartedListener(onCameraMoveStarted?.let { { onCameraMoveStarted(it) } })

    override fun setOnCameraMoveListener(onCameraMove: (() -> Unit)?) =
        map.setOnCameraMoveListener(onCameraMove?.let { { onCameraMove() } })

    override fun setOnCameraMoveCanceledListener(onCameraMoveCanceled: (() -> Unit)?) =
        map.setOnCameraMoveCanceledListener(onCameraMoveCanceled?.let { { onCameraMoveCanceled() } })

    override fun setOnCameraIdleListener(onCameraIdle: (() -> Unit)?) =
        map.setOnCameraIdleListener(onCameraIdle?.let { { onCameraIdle() } })

    override fun setOnMapClickListener(onMapClick: ((GoogleLatLng) -> Unit)?) =
        map.setOnMapClickListener(onMapClick?.let { { onMapClick(GoogleLatLng(it)) } })

    override fun setOnMapLongClickListener(onMapLongClick: ((GoogleLatLng) -> Unit)?) =
        map.setOnMapLongClickListener(onMapLongClick?.let { { onMapLongClick(GoogleLatLng(it)) } })

    override fun setOnMarkerClickListener(onMarkerClick: ((GoogleMarker) -> Boolean)?) =
        map.setOnMarkerClickListener(onMarkerClick?.let { { onMarkerClick(GoogleMarker(it)) } })

    override fun setOnMarkerDragListener(onMarkerDragStart: ((GoogleMarker) -> Unit)?, onMarkerDrag: ((GoogleMarker) -> Unit)?, onMarkerDragEnd: ((GoogleMarker) -> Unit)?) =
        map.setOnMarkerDragListener(if (onMarkerDragStart != null && onMarkerDrag != null && onMarkerDragEnd != null) object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) = onMarkerDragStart(GoogleMarker(marker))
            override fun onMarkerDrag(marker: Marker) = onMarkerDrag(GoogleMarker(marker))
            override fun onMarkerDragEnd(marker: Marker) = onMarkerDragEnd(GoogleMarker(marker))
        } else null)

    override fun setOnInfoWindowClickListener(onInfoWindowClick: ((GoogleMarker) -> Unit)?) =
        map.setOnInfoWindowClickListener(onInfoWindowClick?.let { { onInfoWindowClick(GoogleMarker(it)) } })

    override fun setOnInfoWindowLongClickListener(onInfoWindowLongClick: ((GoogleMarker) -> Unit)?) =
        map.setOnInfoWindowLongClickListener(onInfoWindowLongClick?.let { { onInfoWindowLongClick(GoogleMarker(it)) } })

    override fun setOnInfoWindowCloseListener(onInfoWindowClose: ((GoogleMarker) -> Unit)?) =
        map.setOnInfoWindowCloseListener(onInfoWindowClose?.let { { onInfoWindowClose(GoogleMarker(it)) } })

    override fun setInfoWindowAdapter(getInfoWindow: ((GoogleMarker) -> View)?, getInfoContents: ((GoogleMarker) -> View)?) =
        map.setInfoWindowAdapter(if (getInfoWindow != null && getInfoContents != null) object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View = getInfoWindow(GoogleMarker(marker))
            override fun getInfoContents(marker: Marker): View = getInfoContents(GoogleMarker(marker))
        } else null)

    override fun setOnMyLocationButtonClickListener(onMyLocationButtonClick: (() -> Boolean)?) =
        map.setOnMyLocationButtonClickListener(onMyLocationButtonClick?.let { { onMyLocationButtonClick() } })

    override fun setOnMyLocationClickListener(onMyLocationClick: ((Location) -> Unit)?) =
        map.setOnMyLocationClickListener(onMyLocationClick?.let { { onMyLocationClick(it) } })

    override fun setOnMapLoadedCallback(onMapLoaded: (() -> Unit)?) =
        map.setOnMapLoadedCallback(onMapLoaded?.let { { onMapLoaded() } })

    override fun setOnCircleClickListener(onCircleClick: ((GoogleCircle) -> Unit)?) =
        map.setOnCircleClickListener(onCircleClick?.let { { onCircleClick(GoogleCircle(it)) } })

    override fun setOnPolygonClickListener(onPolygonClick: ((GooglePolygon) -> Unit)?) =
        map.setOnPolygonClickListener(onPolygonClick?.let { { onPolygonClick(GooglePolygon(it)) } })

    override fun setOnPolylineClickListener(onPolylineClick: ((GooglePolyline) -> Unit)?) =
        map.setOnPolylineClickListener(onPolylineClick?.let { { onPolylineClick(GooglePolyline(it)) } })

    override fun snapshot(onSnapshotReady: ((Bitmap?) -> Unit)) =
        map.snapshot { onSnapshotReady(it) }

    override fun snapshot(onSnapshotReady: ((Bitmap?) -> Unit), var2: Bitmap?) =
        map.snapshot({ onSnapshotReady(it) }, var2)

    override fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int) =
        map.setPadding(var1, var2, var3, var4)

    override fun setContentDescription(var1: String?) =
        map.setContentDescription(var1)

    override fun setOnPoiClickListener(onPoiClick: ((GooglePointOfInterest) -> Unit)?) =
        map.setOnPoiClickListener(onPoiClick?.let { { onPoiClick(GooglePointOfInterest(it)) } })

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