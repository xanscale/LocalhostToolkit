package it.localhostsoftware.maps.model

abstract class Circle<C>(val c: C) {
    abstract val id: String
    abstract var center: LatLng<*>
    abstract var radius: Double
    abstract var strokeWidth: Float
    abstract var strokeColor: Int
    abstract var strokePattern: List<PatternItem<*>>?
    abstract var fillColor: Int
    abstract var zIndex: Float
    abstract var isVisible: Boolean
    abstract var isClickable: Boolean
    abstract var tag: Any?
    abstract fun remove()
}