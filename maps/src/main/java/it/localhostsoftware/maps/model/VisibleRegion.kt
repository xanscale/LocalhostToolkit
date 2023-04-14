package it.localhostsoftware.maps.model

abstract class VisibleRegion<VR, LL : LatLng<*>, LLB : LatLngBounds<*, *>>(val vr: VR) {
    abstract val nearLeft: LL
    abstract val nearRight: LL
    abstract val farLeft: LL
    abstract val farRight: LL
    abstract val latLngBounds: LLB
    abstract override fun hashCode(): Int
    abstract override fun equals(other: Any?): Boolean
    abstract override fun toString(): String
}