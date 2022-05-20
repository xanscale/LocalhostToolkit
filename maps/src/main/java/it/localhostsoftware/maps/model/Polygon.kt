package it.localhostsoftware.maps.model

abstract class Polygon<P>(val p: P) {
    abstract fun remove()
    abstract val id: String
    abstract var points: List<LatLng<*>>
    abstract var holes: List<List<LatLng<*>>>
    abstract var strokeWidth: Float
    abstract var strokeColor: Int
    abstract var strokeJointType: Int
    abstract var strokePattern: List<PatternItem<*>>?
    abstract var fillColor: Int
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isGeodesic: Boolean
    abstract var isClickable: Boolean
    abstract var tag: Any?
}