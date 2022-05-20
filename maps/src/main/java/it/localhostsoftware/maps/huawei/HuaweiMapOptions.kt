package it.localhostsoftware.maps.huawei

import com.huawei.hms.maps.HuaweiMapOptions
import it.localhostsoftware.maps.MapOptions
import it.localhostsoftware.maps.huawei.model.HuaweiCameraPosition
import it.localhostsoftware.maps.huawei.model.HuaweiLatLngBounds
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLngBounds

class HuaweiMapOptions(huaweiMapOptions: HuaweiMapOptions) : MapOptions<HuaweiMapOptions>(huaweiMapOptions) {
    override fun zOrderOnTop(var1: Boolean): MapOptions<*> {
        mo.zOrderOnTop(var1)
        return this
    }

    override fun useViewLifecycleInFragment(var1: Boolean): MapOptions<*> {
        mo.useViewLifecycleInFragment(var1)
        return this
    }

    override fun mapType(var1: Int): MapOptions<*> {
        mo.mapType(var1)
        return this
    }

    override fun camera(var1: CameraPosition<*>?): MapOptions<*> {
        mo.camera(var1?.cp as? com.huawei.hms.maps.model.CameraPosition)
        return this
    }

    override fun zoomControlsEnabled(var1: Boolean): MapOptions<*> {
        mo.zoomControlsEnabled(var1)
        return this
    }

    override fun compassEnabled(var1: Boolean): MapOptions<*> {
        mo.compassEnabled(var1)
        return this
    }

    override fun scrollGesturesEnabled(var1: Boolean): MapOptions<*> {
        mo.scrollGesturesEnabled(var1)
        return this
    }

    override fun zoomGesturesEnabled(var1: Boolean): MapOptions<*> {
        mo.zoomGesturesEnabled(var1)
        return this
    }

    override fun tiltGesturesEnabled(var1: Boolean): MapOptions<*> {
        mo.tiltGesturesEnabled(var1)
        return this
    }

    override fun rotateGesturesEnabled(var1: Boolean): MapOptions<*> {
        mo.rotateGesturesEnabled(var1)
        return this
    }

    override fun liteMode(var1: Boolean): MapOptions<*> {
        mo.liteMode(var1)
        return this
    }

    override fun mapToolbarEnabled(var1: Boolean): MapOptions<*> {
        mo.mapToolbarEnabled(var1)
        return this
    }

    override fun ambientEnabled(var1: Boolean): MapOptions<*> {
        mo.ambientEnabled(var1)
        return this
    }

    override fun minZoomPreference(var1: Float): MapOptions<*> {
        mo.minZoomPreference(var1)
        return this
    }

    override fun maxZoomPreference(var1: Float): MapOptions<*> {
        mo.maxZoomPreference(var1)
        return this
    }

    override fun latLngBoundsForCameraTarget(var1: LatLngBounds<*>?): MapOptions<*> {
        mo.latLngBoundsForCameraTarget(var1?.lb as? com.huawei.hms.maps.model.LatLngBounds)
        return this
    }

    override val zOrderOnTop: Boolean
        get() = mo.zOrderOnTop
    override val useViewLifecycleInFragment: Boolean
        get() = mo.useViewLifecycleInFragment
    override val mapType: Int
        get() = mo.mapType
    override val camera: CameraPosition<*>
        get() = HuaweiCameraPosition(mo.camera)
    override val zoomControlsEnabled: Boolean
        get() = mo.zoomControlsEnabled
    override val compassEnabled: Boolean
        get() = mo.compassEnabled
    override val scrollGesturesEnabled: Boolean
        get() = mo.scrollGesturesEnabled
    override val zoomGesturesEnabled: Boolean
        get() = mo.zoomGesturesEnabled
    override val tiltGesturesEnabled: Boolean
        get() = mo.tiltGesturesEnabled
    override val rotateGesturesEnabled: Boolean
        get() = mo.rotateGesturesEnabled
    override val liteMode: Boolean
        get() = mo.liteMode
    override val mapToolbarEnabled: Boolean
        get() = mo.mapToolbarEnabled
    override val ambientEnabled: Boolean
        get() = mo.ambientEnabled
    override val minZoomPreference: Float
        get() = mo.minZoomPreference
    override val maxZoomPreference: Float
        get() = mo.maxZoomPreference
    override val latLngBoundsForCameraTarget: LatLngBounds<*>
        get() = HuaweiLatLngBounds(mo.latLngBoundsForCameraTarget)
}