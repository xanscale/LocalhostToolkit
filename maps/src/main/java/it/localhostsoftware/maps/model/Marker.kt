package it.localhostsoftware.maps.model

import android.content.Context
import com.google.android.gms.maps.model.MarkerOptions
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleMarkerOptions
import it.localhostsoftware.maps.huawei.model.HuaweiMarkerOptions

abstract class Marker<M, LL : LatLng<*>, BD : BitmapDescriptor<*>>(val m: M) {
    abstract fun remove()
    abstract val id: String
    abstract var position: LL
    abstract var zIndex: Float
    abstract fun setIcon(var1: BD?)
    abstract fun setAnchor(var1: Float, var2: Float)
    abstract fun setInfoWindowAnchor(var1: Float, var2: Float)
    abstract var title: String?
    abstract var snippet: String?
    abstract var isDraggable: Boolean
    abstract fun showInfoWindow()
    abstract fun hideInfoWindow()
    abstract val isInfoWindowShown: Boolean
    abstract var isVisible: Boolean
    abstract var isFlat: Boolean
    abstract var rotation: Float
    abstract var alpha: Float
    abstract var tag: Any?
}

abstract class MarkerOptions<MO, BD : BitmapDescriptor<*>, LL : LatLng<*>>(val mo: MO) {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleMarkerOptions(MarkerOptions())
                MobileServices.HUAWEI -> HuaweiMarkerOptions(com.huawei.hms.maps.model.MarkerOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract var position: LL
    abstract var title: String?
    abstract var snippet: String?
    abstract var icon: BD?
    abstract var anchor: Pair<Float, Float>
    abstract var isDraggable: Boolean
    abstract var isVisible: Boolean
    abstract var isFlat: Boolean
    abstract var rotation: Float
    abstract var infoWindowAnchor: Pair<Float, Float>
    abstract var alpha: Float
    abstract var zIndex: Float
}