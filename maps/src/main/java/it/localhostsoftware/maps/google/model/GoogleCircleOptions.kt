package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.CircleOptions
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.PatternItem

class GoogleCircleOptions(circleOptions: CircleOptions) : it.localhostsoftware.maps.model.CircleOptions<CircleOptions>(circleOptions) {
    override fun center(var1: LatLng<*>): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.center(var1.ll as com.google.android.gms.maps.model.LatLng)
        return this
    }

    override fun radius(var1: Double): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.radius(var1)
        return this
    }

    override fun strokeWidth(var1: Float): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.strokeWidth(var1)
        return this
    }

    override fun strokeColor(var1: Int): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.strokeColor(var1)
        return this
    }

    override fun strokePattern(var1: List<PatternItem<*>>?): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.strokePattern(var1?.map { it.pi as com.google.android.gms.maps.model.PatternItem })
        return this
    }

    override fun fillColor(var1: Int): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.fillColor(var1)
        return this
    }

    override fun zIndex(var1: Float): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.zIndex(var1)
        return this
    }

    override fun visible(var1: Boolean): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.visible(var1)
        return this
    }

    override fun clickable(var1: Boolean): it.localhostsoftware.maps.model.CircleOptions<*> {
        co.clickable(var1)
        return this
    }

    override val center: LatLng<*>?
        get() = co.center?.let { GoogleLatLng(it) }
    override val radius: Double
        get() = co.radius
    override val strokeWidth: Float
        get() = co.strokeWidth
    override val strokeColor: Int
        get() = co.strokeColor
    override val strokePattern: List<PatternItem<*>>?
        get() = co.strokePattern?.map { PatternItem<com.google.android.gms.maps.model.PatternItem>(it) }
    override val fillColor: Int
        get() = co.fillColor
    override val zIndex: Float
        get() = co.zIndex
    override val isVisible: Boolean
        get() = co.isVisible
    override val isClickable: Boolean
        get() = co.isClickable
}