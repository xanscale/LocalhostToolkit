package it.localhostsoftware.maps

abstract class UiSettings<US>(val uiSettings: US) {
    abstract fun setAllGesturesEnabled(var1: Boolean)
    abstract var isZoomControlsEnabled: Boolean
    abstract var isCompassEnabled: Boolean
    abstract var isMyLocationButtonEnabled: Boolean
    abstract var isIndoorLevelPickerEnabled: Boolean
    abstract var isScrollGesturesEnabled: Boolean
    abstract var isScrollGesturesEnabledDuringRotateOrZoom: Boolean
    abstract var isZoomGesturesEnabled: Boolean
    abstract var isTiltGesturesEnabled: Boolean
    abstract var isRotateGesturesEnabled: Boolean
    abstract var isMapToolbarEnabled: Boolean
}