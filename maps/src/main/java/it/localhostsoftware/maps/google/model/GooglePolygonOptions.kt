package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.PolygonOptions
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.PatternItem

class GooglePolygonOptions(polygonOptions: PolygonOptions) : it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions>(polygonOptions) {
    override fun add(vararg points: LatLng<*>): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.add(*points.map { it.ll as com.google.android.gms.maps.model.LatLng }.toTypedArray())
        return this
    }

    override fun addHole(points: Iterable<LatLng<*>>): it.localhostsoftware.maps.model.PolygonOptions<*> {
        po.addHole(points.map { it.ll as com.google.android.gms.maps.model.LatLng })
        return this
    }

    override val points: List<LatLng<*>>
        get() = po.points.map { GoogleLatLng(it) }
    override val holes: List<List<LatLng<*>>>
        get() = po.holes.map { it.map { ll -> GoogleLatLng(ll) } }
    override var strokeWidth: Float
        get() = po.strokeWidth
        set(value) {
            po.strokeWidth(value)
        }
    override var strokeColor: Int
        get() = po.strokeColor
        set(value) {
            po.strokeColor(value)
        }
    override var strokeJointType: Int
        get() = po.strokeJointType
        set(value) {
            po.strokeJointType(value)
        }
    override var strokePattern: List<PatternItem<*>>?
        get() = po.strokePattern?.map { PatternItem<com.google.android.gms.maps.model.PatternItem>(it) }
        set(value) {
            po.strokePattern(value?.map { it.pi as com.google.android.gms.maps.model.PatternItem })
        }
    override var fillColor: Int
        get() = po.fillColor
        set(value) {
            po.fillColor(value)
        }
    override var zIndex: Float
        get() = po.zIndex
        set(value) {
            po.zIndex(value)
        }
    override var isVisible: Boolean
        get() = po.isVisible
        set(value) {
            po.visible(value)
        }
    override var isGeodesic: Boolean
        get() = po.isGeodesic
        set(value) {
            po.geodesic(value)
        }
    override var isClickable: Boolean
        get() = po.isClickable
        set(value) {
            po.clickable(value)
        }
}