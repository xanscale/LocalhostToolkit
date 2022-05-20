package it.localhostsoftware.maps.model

abstract class Marker<M>(val m: M) {
    abstract fun remove()
    abstract val id: String
    abstract var position: LatLng<*>
    abstract var zIndex: Float
    abstract fun setIcon(var1: BitmapDescriptor<*>?)
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