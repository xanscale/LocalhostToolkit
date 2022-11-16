package it.localhostsoftware.maps.model

abstract class Marker<M, LL : LatLng<*>, BD: BitmapDescriptor<*>>(val m: M) {
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