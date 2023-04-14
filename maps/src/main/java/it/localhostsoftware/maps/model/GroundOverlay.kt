package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleGroundOverlayOptions
import it.localhostsoftware.maps.huawei.model.HuaweiGroundOverlayOptions

abstract class GroundOverlay<GO, BD : BitmapDescriptor<*>, LL : LatLng<*>, LLB : LatLngBounds<*, *>>(val go: GO) {
    abstract var bearing: Float
    abstract val height: Float
    abstract val width: Float
    abstract val id: String
    abstract var transparency: Float
    abstract var zIndex: Float
    abstract var position: LL
    abstract var bounds: LLB
    abstract var tag: Any?
    abstract var clickable: Boolean
    abstract var visible: Boolean
    abstract fun remove()
    abstract fun setDimensions(param1: Float)
    abstract fun setDimensions(param1: Float, param2: Float)
    abstract fun setImage(param1: BD)
}

abstract class GroundOverlayOptions<GOO, BD : BitmapDescriptor<*>, LL : LatLng<*>, LLB : LatLngBounds<*, *>>(val goo: GOO) {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleGroundOverlayOptions(com.google.android.gms.maps.model.GroundOverlayOptions())
                MobileServices.HUAWEI -> HuaweiGroundOverlayOptions(com.huawei.hms.maps.model.GroundOverlayOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract var anchor: Pair<Float, Float>
    abstract var bearing: Float
    abstract var zIndex: Float
    abstract var clickable: Boolean
    abstract var image: BD
    abstract var transparency: Float
    abstract var visible: Boolean
    abstract val height: Float
    abstract val width: Float
    abstract val location: LL?
    abstract val bounds: LLB?
    abstract fun position(location: LL, width: Float): GroundOverlayOptions<GOO, BD, LL, LLB>
    abstract fun position(location: LL, width: Float, height: Float): GroundOverlayOptions<GOO, BD, LL, LLB>
    abstract fun positionFromBounds(bounds: LLB): GroundOverlayOptions<GOO, BD, LL, LLB>
}