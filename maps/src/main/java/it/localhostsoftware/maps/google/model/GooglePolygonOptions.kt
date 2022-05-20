package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.PolygonOptions
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.PatternItem

class GooglePolygonOptions(polygonOptions: PolygonOptions) : it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions>(polygonOptions) {
    override fun add(point: LatLng<*>): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.add(point.ll as com.google.android.gms.maps.model.LatLng)
        return this
    }

    override fun add(vararg points: LatLng<*>): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.add(*points.map { it.ll as com.google.android.gms.maps.model.LatLng }.toTypedArray())
        return this
    }

    override fun addAll(points: Iterable<LatLng<*>>): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.addAll(points.map { it.ll as com.google.android.gms.maps.model.LatLng })
        return this
    }

    override fun addHole(points: Iterable<LatLng<*>>): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.addHole(points.map { it.ll as com.google.android.gms.maps.model.LatLng })
        return this
    }

    override fun strokeWidth(width: Float): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.strokeWidth(width)
        return this
    }

    override fun strokeColor(color: Int): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.strokeColor(color)
        return this
    }

    override fun strokeJointType(jointType: Int): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.strokeJointType(jointType)
        return this
    }

    override fun strokePattern(pattern: List<PatternItem<*>>?): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.strokePattern(pattern?.map { it.pi as com.google.android.gms.maps.model.PatternItem })
        return this
    }

    override fun fillColor(color: Int): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.fillColor(color)
        return this
    }

    override fun zIndex(zIndex: Float): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.zIndex(zIndex)
        return this
    }

    override fun visible(visible: Boolean): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.visible(visible)
        return this
    }

    override fun geodesic(geodesic: Boolean): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.geodesic(geodesic)
        return this
    }

    override fun clickable(clickable: Boolean): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.clickable(clickable)
        return this
    }

    override val points: List<LatLng<*>>
        get() = po.points.map { GoogleLatLng(it) }
    override val holes: List<List<LatLng<*>>>
        get() = po.holes.map { it.map { ll -> GoogleLatLng(ll) } }
    override val strokeWidth: Float
        get() = po.strokeWidth
    override val strokeColor: Int
        get() = po.strokeColor
    override val strokeJointType: Int
        get() = po.strokeJointType
    override val strokePattern: List<PatternItem<*>>?
        get() =
            po.strokePattern?.map { PatternItem<com.google.android.gms.maps.model.PatternItem>(it) }
    override val fillColor: Int
        get() = po.fillColor
    override val zIndex: Float
        get() = po.zIndex
    override val isVisible: Boolean
        get() = po.isVisible
    override val isGeodesic: Boolean
        get() = po.isGeodesic
    override val isClickable: Boolean
        get() = po.isClickable
}