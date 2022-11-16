package it.localhostsoftware.maps.model

abstract class Polyline<P, PI : PatternItem<*>, LL:LatLng<*>, C: Cap<*>>(val p: P) {
    abstract fun remove()
    abstract val id: String
    abstract var points: List<LL>
    abstract var width: Float
    abstract var color: Int
    abstract var startCap: C
    abstract var endCap: C
    abstract var jointType: Int
    abstract var pattern: List<PI>?
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isGeodesic: Boolean
    abstract var isClickable: Boolean
    abstract var tag: Any?
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}