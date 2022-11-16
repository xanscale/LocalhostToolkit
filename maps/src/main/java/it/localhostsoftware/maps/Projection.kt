package it.localhostsoftware.maps

import android.graphics.Point
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.VisibleRegion

abstract class Projection<P, LL : LatLng<*>>(val p: P) {
    abstract fun fromScreenLocation(var1: Point): LL
    abstract fun toScreenLocation(var1: LL): Point?
    abstract val visibleRegion: VisibleRegion<*, *, *>
}