package it.localhostsoftware.maps.huawei

import android.graphics.Bitmap
import android.location.Location
import android.view.View
import androidx.annotation.RequiresPermission
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.LocationSource
import com.huawei.hms.maps.model.IndoorBuilding
import com.huawei.hms.maps.model.Marker
import it.localhostsoftware.maps.GeoMap
import it.localhostsoftware.maps.huawei.model.*

class HuaweiMap(huaweiMap: HuaweiMap) : GeoMap<HuaweiMap, HuaweiCameraUpdate, HuaweiCameraPosition, HuaweiPolylineOptions, HuaweiPolyline, HuaweiPolygonOptions, HuaweiPolygon, HuaweiCircleOptions, HuaweiCircle, HuaweiMarkerOptions, HuaweiMarker, HuaweiIndoorBuilding, HuaweiUiSettings, HuaweiProjection, HuaweiMapStyleOptions, HuaweiLatLng, HuaweiLatLngBounds, HuaweiPointOfInterest, HuaweiGroundOverlayOptions, HuaweiGroundOverlay>(
    huaweiMap
) {
    override val cameraPosition: HuaweiCameraPosition
        get() = HuaweiCameraPosition(map.cameraPosition)
    override val maxZoomLevel: Float
        get() = map.maxZoomLevel
    override val minZoomLevel: Float
        get() = map.minZoomLevel

    override fun moveCamera(var1: HuaweiCameraUpdate) =
        map.moveCamera(var1.cu)

    override fun animateCamera(var1: HuaweiCameraUpdate) =
        map.animateCamera(var1.cu)

    override fun animateCamera(var1: HuaweiCameraUpdate, onFinish: (() -> Unit)?, onCancel: (() -> Unit)?) =
        map.animateCamera(var1.cu, if (onFinish != null && onCancel != null) object : HuaweiMap.CancelableCallback {
            override fun onFinish() = onFinish()
            override fun onCancel() = onCancel()
        } else null)

    override fun animateCamera(var1: HuaweiCameraUpdate, var2: Int, onFinish: (() -> Unit)?, onCancel: (() -> Unit)?) =
        map.animateCamera(var1.cu, var2, if (onFinish != null && onCancel != null) object : HuaweiMap.CancelableCallback {
            override fun onFinish() = onFinish()
            override fun onCancel() = onCancel()
        } else null)

    override fun stopAnimation() =
        map.stopAnimation()

    override fun addPolyline(var1: HuaweiPolylineOptions) =
        HuaweiPolyline(map.addPolyline(var1.po))

    override fun addPolygon(var1: HuaweiPolygonOptions) =
        HuaweiPolygon(map.addPolygon(var1.po))

    override fun addCircle(var1: HuaweiCircleOptions) =
        HuaweiCircle(map.addCircle(var1.co))

    override fun addMarker(var1: HuaweiMarkerOptions) =
        HuaweiMarker(map.addMarker(var1.mo))

    override fun addGroundOverlay(var1: HuaweiGroundOverlayOptions) =
        map.addGroundOverlay(var1.goo)?.let { HuaweiGroundOverlay(it) }

    override fun clear() =
        map.clear()

    override val focusedBuilding: HuaweiIndoorBuilding?
        get() = map.focusedBuilding?.let { HuaweiIndoorBuilding(it) }

    override fun setOnIndoorStateChangeListener(onIndoorBuildingFocused: (() -> Unit)?, onIndoorLevelActivated: ((HuaweiIndoorBuilding) -> Unit)?) =
        map.setOnIndoorStateChangeListener(if (onIndoorBuildingFocused != null && onIndoorLevelActivated != null) object : HuaweiMap.OnIndoorStateChangeListener {
            override fun onIndoorBuildingFocused() = onIndoorBuildingFocused()
            override fun onIndoorLevelActivated(indoorBuilding: IndoorBuilding) = onIndoorLevelActivated(HuaweiIndoorBuilding(indoorBuilding))
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

    override fun setLocationSource(activate: (((Location) -> Unit) -> Unit)?, deactivate: (() -> Unit)?) =
        map.setLocationSource(if (activate != null && deactivate != null) object : LocationSource {
            override fun deactivate() = deactivate()
            override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) =
                activate { onLocationChangedListener.onLocationChanged(it) }
        } else null)

    override val uiSettings: HuaweiUiSettings
        get() = HuaweiUiSettings(map.uiSettings)
    override val projection: HuaweiProjection
        get() = HuaweiProjection(map.projection)

    override fun setOnCameraMoveStartedListener(onCameraMoveStarted: ((Int) -> Unit)?) =
        map.setOnCameraMoveStartedListener(onCameraMoveStarted?.let { { onCameraMoveStarted(it) } })

    override fun setOnCameraMoveListener(onCameraMove: (() -> Unit)?) =
        map.setOnCameraMoveListener(onCameraMove?.let { { onCameraMove() } })

    override fun setOnCameraMoveCanceledListener(onCameraMoveCanceled: (() -> Unit)?) =
        map.setOnCameraMoveCanceledListener(onCameraMoveCanceled?.let { { onCameraMoveCanceled() } })

    override fun setOnCameraIdleListener(onCameraIdle: (() -> Unit)?) =
        map.setOnCameraIdleListener(onCameraIdle?.let { { onCameraIdle() } })

    override fun setOnMapClickListener(onMapClick: ((HuaweiLatLng) -> Unit)?) =
        map.setOnMapClickListener(onMapClick?.let { { onMapClick(HuaweiLatLng(it)) } })

    override fun setOnMapLongClickListener(onMapLongClick: ((HuaweiLatLng) -> Unit)?) =
        map.setOnMapLongClickListener(onMapLongClick?.let { { onMapLongClick(HuaweiLatLng(it)) } })

    override fun setOnMarkerClickListener(onMarkerClick: ((HuaweiMarker) -> Boolean)?) =
        map.setOnMarkerClickListener(onMarkerClick?.let { { onMarkerClick(HuaweiMarker(it)) } })

    override fun setOnMarkerDragListener(onMarkerDragStart: ((HuaweiMarker) -> Unit)?, onMarkerDrag: ((HuaweiMarker) -> Unit)?, onMarkerDragEnd: ((HuaweiMarker) -> Unit)?) =
        map.setOnMarkerDragListener(if (onMarkerDragStart != null && onMarkerDrag != null && onMarkerDragEnd != null) object : HuaweiMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) = onMarkerDragStart(HuaweiMarker(marker))
            override fun onMarkerDrag(marker: Marker) = onMarkerDrag(HuaweiMarker(marker))
            override fun onMarkerDragEnd(marker: Marker) = onMarkerDragEnd(HuaweiMarker(marker))
        } else null)

    override fun setOnInfoWindowClickListener(onInfoWindowClick: ((HuaweiMarker) -> Unit)?) =
        map.setOnInfoWindowClickListener(onInfoWindowClick?.let { { onInfoWindowClick(HuaweiMarker(it)) } })

    override fun setOnInfoWindowLongClickListener(onInfoWindowLongClick: ((HuaweiMarker) -> Unit)?) =
        map.setOnInfoWindowLongClickListener(onInfoWindowLongClick?.let { { onInfoWindowLongClick(HuaweiMarker(it)) } })

    override fun setOnInfoWindowCloseListener(onInfoWindowClose: ((HuaweiMarker) -> Unit)?) =
        map.setOnInfoWindowCloseListener(onInfoWindowClose?.let { { onInfoWindowClose(HuaweiMarker(it)) } })

    override fun setInfoWindowAdapter(getInfoWindow: ((HuaweiMarker) -> View)?, getInfoContents: ((HuaweiMarker) -> View)?) =
        map.setInfoWindowAdapter(if (getInfoWindow != null && getInfoContents != null) object : HuaweiMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View = getInfoWindow(HuaweiMarker(marker))
            override fun getInfoContents(marker: Marker): View = getInfoContents(HuaweiMarker(marker))
        } else null)

    override fun setOnMyLocationButtonClickListener(onMyLocationButtonClick: (() -> Boolean)?) =
        map.setOnMyLocationButtonClickListener(onMyLocationButtonClick?.let { { onMyLocationButtonClick() } })

    override fun setOnMyLocationClickListener(onMyLocationClick: ((Location) -> Unit)?) =
        map.setOnMyLocationClickListener(onMyLocationClick?.let { { onMyLocationClick(it) } })

    override fun setOnMapLoadedCallback(onMapLoaded: (() -> Unit)?) =
        map.setOnMapLoadedCallback(onMapLoaded?.let { { onMapLoaded() } })

    override fun setOnGroundOverlayClickListener(onGroundOverlayClick: (HuaweiGroundOverlay) -> Unit) =
        map.setOnGroundOverlayClickListener { onGroundOverlayClick(HuaweiGroundOverlay(it)) }

    override fun setOnCircleClickListener(onCircleClick: ((HuaweiCircle) -> Unit)?) =
        map.setOnCircleClickListener(onCircleClick?.let { { onCircleClick(HuaweiCircle(it)) } })

    override fun setOnPolygonClickListener(onPolygonClick: ((HuaweiPolygon) -> Unit)?) =
        map.setOnPolygonClickListener(onPolygonClick?.let { { onPolygonClick(HuaweiPolygon(it)) } })

    override fun setOnPolylineClickListener(onPolylineClick: ((HuaweiPolyline) -> Unit)?) =
        map.setOnPolylineClickListener(onPolylineClick?.let { { onPolylineClick(HuaweiPolyline(it)) } })

    override fun snapshot(onSnapshotReady: ((Bitmap?) -> Unit)) =
        map.snapshot { onSnapshotReady(it) }

    override fun snapshot(onSnapshotReady: ((Bitmap?) -> Unit), var2: Bitmap?) =
        map.snapshot({ onSnapshotReady(it) }, var2)

    override fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int) {
        map.setPadding(var1, var2, var3, var4)
        map.uiSettings.setLogoPadding(var1, var2, var3, var4)
    }

    override fun setContentDescription(var1: String?) =
        map.setContentDescription(var1)

    override fun setOnPoiClickListener(onPoiClick: ((HuaweiPointOfInterest) -> Unit)?) =
        map.setOnPoiClickListener(onPoiClick?.let { { onPoiClick(HuaweiPointOfInterest(it)) } })

    override fun setMapStyle(var1: HuaweiMapStyleOptions?) =
        map.setMapStyle(var1?.let { var1.mso })

    override fun setMinZoomPreference(var1: Float) =
        map.setMinZoomPreference(var1)

    override fun setMaxZoomPreference(var1: Float) =
        map.setMaxZoomPreference(var1)

    override fun resetMinMaxZoomPreference() =
        map.resetMinMaxZoomPreference()

    override fun setLatLngBoundsForCameraTarget(var1: HuaweiLatLngBounds?) =
        map.setLatLngBoundsForCameraTarget(var1?.let { var1.lb })
}