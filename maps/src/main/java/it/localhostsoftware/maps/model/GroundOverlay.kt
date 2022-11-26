package it.localhostsoftware.maps.model

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