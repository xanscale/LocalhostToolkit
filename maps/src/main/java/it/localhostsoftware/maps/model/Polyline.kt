package it.localhostsoftware.maps.model

abstract class Polyline<P>(val p: P) {
    abstract fun remove()
    abstract val id: String
    abstract var points: List<LatLng<*>>
    abstract var width: Float
    abstract var color: Int
    abstract var startCap: Cap<*>
    abstract var endCap: Cap<*>
    abstract var jointType: Int
    abstract var pattern: List<PatternItem<*>>?
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isGeodesic: Boolean
    abstract var isClickable: Boolean
    abstract var tag: Any?
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}