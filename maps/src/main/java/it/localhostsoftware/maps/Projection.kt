package it.localhostsoftware.maps

import android.graphics.Point
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.VisibleRegion

abstract class Projection<P>(val p: P) {
    abstract fun fromScreenLocation(var1: Point): LatLng<*>
    abstract fun toScreenLocation(var1: LatLng<*>): Point
    abstract val visibleRegion: VisibleRegion<*>
}