package it.localhostsoftware.maps.model

abstract class VisibleRegion<VR>(val vr: VR) {
    abstract val nearLeft: LatLng<*>
    abstract val nearRight: LatLng<*>
    abstract val farLeft: LatLng<*>
    abstract val farRight: LatLng<*>
    abstract val latLngBounds: LatLngBounds<*, *>
    abstract override fun hashCode(): Int
    abstract override fun equals(other: Any?): Boolean
    abstract override fun toString(): String
}