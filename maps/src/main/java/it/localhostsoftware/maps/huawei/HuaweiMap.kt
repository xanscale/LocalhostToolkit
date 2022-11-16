package it.localhostsoftware.maps.huawei

import android.graphics.Bitmap
import android.view.View
import androidx.annotation.RequiresPermission
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.LocationSource
import com.huawei.hms.maps.model.IndoorBuilding
import com.huawei.hms.maps.model.LatLngBounds
import com.huawei.hms.maps.model.MapStyleOptions
import com.huawei.hms.maps.model.Marker
import it.localhostsoftware.maps.GeoMap
import it.localhostsoftware.maps.Projection
import it.localhostsoftware.maps.huawei.model.*

class HuaweiMap(huaweiMap: HuaweiMap) : GeoMap<HuaweiMap, HuaweiCameraUpdate, HuaweiCameraPosition, HuaweiPolylineOptions, HuaweiPolyline, HuaweiPolygonOptions, HuaweiPolygon, HuaweiCircleOptions, HuaweiCircle, HuaweiMarkerOptions, HuaweiMarker,
        HuaweiIndoorBuilding, HuaweiUiSettings
        >(huaweiMap) {
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

    override fun animateCamera(var1: HuaweiCameraUpdate, var2: CancelableCallback?) =
        map.animateCamera(var1.cu, var2?.let {
            object : HuaweiMap.CancelableCallback {
                override fun onFinish() = it.onFinish()
                override fun onCancel() = it.onCancel()
            }
        })

    override fun animateCamera(var1: HuaweiCameraUpdate, var2: Int, var3: CancelableCallback?) =
        map.animateCamera(var1.cu, var2, var3?.let {
            object : HuaweiMap.CancelableCallback {
                override fun onFinish() = it.onFinish()
                override fun onCancel() = it.onCancel()
            }
        })

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

    override fun clear() =
        map.clear()

    override val focusedBuilding: HuaweiIndoorBuilding?
        get() = map.focusedBuilding?.let { HuaweiIndoorBuilding(it) }

    override fun setOnIndoorStateChangeListener(var1: OnIndoorStateChangeListener?) =
        map.setOnIndoorStateChangeListener(var1?.let {
            object : HuaweiMap.OnIndoorStateChangeListener {
                override fun onIndoorBuildingFocused() = var1.onIndoorBuildingFocused()
                override fun onIndoorLevelActivated(indoorBuilding: IndoorBuilding) = var1.onIndoorLevelActivated(HuaweiIndoorBuilding(indoorBuilding))
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

    override val uiSettings: HuaweiUiSettings
        get() = HuaweiUiSettings(map.uiSettings)
    override val projection: Projection<*>
        get() = HuaweiProjection(map.projection)

    override fun setOnCameraMoveStartedListener(var1: OnCameraMoveStartedListener?) =
        map.setOnCameraMoveStartedListener(var1?.let { HuaweiMap.OnCameraMoveStartedListener { var1.onCameraMoveStarted(it) } })

    override fun setOnCameraMoveListener(var1: OnCameraMoveListener?) =
        map.setOnCameraMoveListener(var1?.let { HuaweiMap.OnCameraMoveListener { var1.onCameraMove() } })

    override fun setOnCameraMoveCanceledListener(var1: OnCameraMoveCanceledListener?) =
        map.setOnCameraMoveCanceledListener(var1?.let { HuaweiMap.OnCameraMoveCanceledListener { var1.onCameraMoveCanceled() } })

    override fun setOnCameraIdleListener(var1: OnCameraIdleListener?) =
        map.setOnCameraIdleListener(var1?.let { HuaweiMap.OnCameraIdleListener { var1.onCameraIdle() } })

    override fun setOnMapClickListener(var1: OnMapClickListener?) =
        map.setOnMapClickListener(var1?.let { HuaweiMap.OnMapClickListener { var1.onMapClick(HuaweiLatLng(it)) } })

    override fun setOnMapLongClickListener(var1: OnMapLongClickListener?) =
        map.setOnMapLongClickListener(var1?.let { HuaweiMap.OnMapLongClickListener { var1.onMapLongClick(HuaweiLatLng(it)) } })

    override fun setOnMarkerClickListener(var1: OnMarkerClickListener?) =
        map.setOnMarkerClickListener(var1?.let { HuaweiMap.OnMarkerClickListener { var1.onMarkerClick(HuaweiMarker(it)) } })

    override fun setOnMarkerDragListener(var1: OnMarkerDragListener?) =
        map.setOnMarkerDragListener(var1?.let {
            object : HuaweiMap.OnMarkerDragListener {
                override fun onMarkerDragStart(marker: Marker) = var1.onMarkerDragStart(HuaweiMarker(marker))
                override fun onMarkerDrag(marker: Marker) = var1.onMarkerDrag(HuaweiMarker(marker))
                override fun onMarkerDragEnd(marker: Marker) = var1.onMarkerDragEnd(HuaweiMarker(marker))
            }
        })

    override fun setOnInfoWindowClickListener(var1: OnInfoWindowClickListener?) =
        map.setOnInfoWindowClickListener(var1?.let { HuaweiMap.OnInfoWindowClickListener { var1.onInfoWindowClick(HuaweiMarker(it)) } })

    override fun setOnInfoWindowLongClickListener(var1: OnInfoWindowLongClickListener?) =
        map.setOnInfoWindowLongClickListener(var1?.let { HuaweiMap.OnInfoWindowLongClickListener { var1.onInfoWindowLongClick(HuaweiMarker(it)) } })

    override fun setOnInfoWindowCloseListener(var1: OnInfoWindowCloseListener?) =
        map.setOnInfoWindowCloseListener(var1?.let { HuaweiMap.OnInfoWindowCloseListener { var1.onInfoWindowClose(HuaweiMarker(it)) } })

    override fun setInfoWindowAdapter(var1: InfoWindowAdapter?) =
        map.setInfoWindowAdapter(var1?.let {
            object : HuaweiMap.InfoWindowAdapter {
                override fun getInfoWindow(marker: Marker): View = it.getInfoWindow(HuaweiMarker(marker))
                override fun getInfoContents(marker: Marker): View = it.getInfoContents(HuaweiMarker(marker))
            }
        })

    override fun setOnMyLocationButtonClickListener(var1: OnMyLocationButtonClickListener?) =
        map.setOnMyLocationButtonClickListener(var1?.let { HuaweiMap.OnMyLocationButtonClickListener { var1.onMyLocationButtonClick() } })

    override fun setOnMyLocationClickListener(var1: OnMyLocationClickListener?) =
        map.setOnMyLocationClickListener(var1?.let { HuaweiMap.OnMyLocationClickListener { var1.onMyLocationClick(it) } })

    override fun setOnMapLoadedCallback(var1: OnMapLoadedCallback?) =
        map.setOnMapLoadedCallback(var1?.let { HuaweiMap.OnMapLoadedCallback { var1.onMapLoaded() } })

    override fun setOnCircleClickListener(var1: OnCircleClickListener?) =
        map.setOnCircleClickListener(var1?.let { HuaweiMap.OnCircleClickListener { var1.onCircleClick(HuaweiCircle(it)) } })

    override fun setOnPolygonClickListener(var1: OnPolygonClickListener?) =
        map.setOnPolygonClickListener(var1?.let { HuaweiMap.OnPolygonClickListener { var1.onPolygonClick(HuaweiPolygon(it)) } })

    override fun setOnPolylineClickListener(var1: OnPolylineClickListener?) =
        map.setOnPolylineClickListener(var1?.let { HuaweiMap.OnPolylineClickListener { var1.onPolylineClick(HuaweiPolyline(it)) } })

    override fun snapshot(var1: SnapshotReadyCallback) =
        map.snapshot { var1.onSnapshotReady(it) }

    override fun snapshot(var1: SnapshotReadyCallback, var2: Bitmap?) =
        map.snapshot({ var1.onSnapshotReady(it) }, var2)

    override fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int) {
        map.setPadding(var1, var2, var3, var4)
        map.uiSettings.setLogoPadding(var1, var2, var3, var4)
    }

    override fun setContentDescription(var1: String?) =
        map.setContentDescription(var1)

    override fun setOnPoiClickListener(var1: OnPoiClickListener?) =
        map.setOnPoiClickListener(var1?.let { HuaweiMap.OnPoiClickListener { var1.onPoiClick(HuaweiPointOfInterest(it)) } })

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