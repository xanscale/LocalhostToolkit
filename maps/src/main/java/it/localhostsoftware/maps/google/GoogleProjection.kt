package it.localhostsoftware.maps.google

import android.graphics.Point
import com.google.android.gms.maps.Projection
import it.localhostsoftware.maps.google.model.GoogleLatLng
import it.localhostsoftware.maps.google.model.GoogleVisibleRegion
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.VisibleRegion

class GoogleProjection(projection: Projection) : it.localhostsoftware.maps.Projection<Projection>(projection) {
    override fun fromScreenLocation(var1: Point) =
            GoogleLatLng(p.fromScreenLocation(var1))

    override fun toScreenLocation(var1: LatLng<*>) =
            p.toScreenLocation(var1.ll as com.google.android.gms.maps.model.LatLng)

    override val visibleRegion: VisibleRegion<*>
        get() = GoogleVisibleRegion(p.visibleRegion)
}