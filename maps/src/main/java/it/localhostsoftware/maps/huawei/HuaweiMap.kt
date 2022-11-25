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

class HuaweiMap(huaweiMap: HuaweiMap) : GeoMap<HuaweiMap, HuaweiCameraUpdate, HuaweiCameraPosition, HuaweiPolylineOptions, HuaweiPolyline, HuaweiPolygonOptions, HuaweiPolygon, HuaweiCircleOptions, HuaweiCircle, HuaweiMarkerOptions, HuaweiMarker, HuaweiIndoorBuilding, HuaweiUiSettings, HuaweiProjection, HuaweiMapStyleOptions, HuaweiLatLng, HuaweiLatLngBounds, HuaweiPointOfInterest>(
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
    override val projection: HuaweiProjection
        get() = HuaweiProjection(map.projection)

    override fun setOnCameraMoveStartedListener(block: ((Int) -> Unit)?) =
        map.setOnCameraMoveStartedListener(block?.let { { block(it) } })

    override fun setOnCameraMoveListener(block: (() -> Unit)?) =
        map.setOnCameraMoveListener(block?.let { { block() } })

    override fun setOnCameraMoveCanceledListener(block: (() -> Unit)?) =
        map.setOnCameraMoveCanceledListener(block?.let { { block() } })

    override fun setOnCameraIdleListener(block: (() -> Unit)?) =
        map.setOnCameraIdleListener(block?.let { { block() } })

    override fun setOnMapClickListener(block: ((HuaweiLatLng) -> Unit)?) =
        map.setOnMapClickListener(block?.let { { block(HuaweiLatLng(it)) } })

    override fun setOnMapLongClickListener(block: ((HuaweiLatLng) -> Unit)?) =
        map.setOnMapLongClickListener(block?.let { { block(HuaweiLatLng(it)) } })

    override fun setOnMarkerClickListener(block: ((HuaweiMarker) -> Boolean)?) =
        map.setOnMarkerClickListener(block?.let { { block(HuaweiMarker(it)) } })

    override fun setOnMarkerDragListener(var1: OnMarkerDragListener?) =
        map.setOnMarkerDragListener(var1?.let {
            object : HuaweiMap.OnMarkerDragListener {
                override fun onMarkerDragStart(marker: Marker) = var1.onMarkerDragStart(HuaweiMarker(marker))
                override fun onMarkerDrag(marker: Marker) = var1.onMarkerDrag(HuaweiMarker(marker))
                override fun onMarkerDragEnd(marker: Marker) = var1.onMarkerDragEnd(HuaweiMarker(marker))
            }
        })

    override fun setOnInfoWindowClickListener(block: ((HuaweiMarker) -> Unit)?) =
        map.setOnInfoWindowClickListener(block?.let { { block(HuaweiMarker(it)) } })

    override fun setOnInfoWindowLongClickListener(block: ((HuaweiMarker) -> Unit)?) =
        map.setOnInfoWindowLongClickListener(block?.let { { block(HuaweiMarker(it)) } })

    override fun setOnInfoWindowCloseListener(block: ((HuaweiMarker) -> Unit)?) =
        map.setOnInfoWindowCloseListener(block?.let { { block(HuaweiMarker(it)) } })

    override fun setInfoWindowAdapter(var1: InfoWindowAdapter?) =
        map.setInfoWindowAdapter(var1?.let {
            object : HuaweiMap.InfoWindowAdapter {
                override fun getInfoWindow(marker: Marker): View = it.getInfoWindow(HuaweiMarker(marker))
                override fun getInfoContents(marker: Marker): View = it.getInfoContents(HuaweiMarker(marker))
            }
        })

    override fun setOnMyLocationButtonClickListener(block: (() -> Boolean)?) =
        map.setOnMyLocationButtonClickListener(block?.let { { block() } })

    override fun setOnMyLocationClickListener(block: ((Location) -> Unit)?) =
        map.setOnMyLocationClickListener(block?.let { { block(it) } })

    override fun setOnMapLoadedCallback(block: (() -> Unit)?) =
        map.setOnMapLoadedCallback(block?.let { { block() } })

    override fun setOnCircleClickListener(block: ((HuaweiCircle) -> Unit)?) =
        map.setOnCircleClickListener(block?.let { { block(HuaweiCircle(it)) } })

    override fun setOnPolygonClickListener(block: ((HuaweiPolygon) -> Unit)?) =
        map.setOnPolygonClickListener(block?.let { { block(HuaweiPolygon(it)) } })

    override fun setOnPolylineClickListener(block: ((HuaweiPolyline) -> Unit)?) =
        map.setOnPolylineClickListener(block?.let { { block(HuaweiPolyline(it)) } })

    override fun snapshot(block: ((Bitmap?) -> Unit)) =
        map.snapshot { block(it) }

    override fun snapshot(block: ((Bitmap?) -> Unit), var2: Bitmap?) =
        map.snapshot({ block(it) }, var2)

    override fun setPadding(var1: Int, var2: Int, var3: Int, var4: Int) {
        map.setPadding(var1, var2, var3, var4)
        map.uiSettings.setLogoPadding(var1, var2, var3, var4)
    }

    override fun setContentDescription(var1: String?) =
        map.setContentDescription(var1)

    override fun setOnPoiClickListener(block: ((HuaweiPointOfInterest) -> Unit)?) =
        map.setOnPoiClickListener(block?.let { { block(HuaweiPointOfInterest(it)) } })

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