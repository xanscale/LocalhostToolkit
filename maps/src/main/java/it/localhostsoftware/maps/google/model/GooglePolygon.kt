package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions

class GooglePolygon(polygon: Polygon) : it.localhostsoftware.maps.model.Polygon<Polygon, GooglePatternItem, GoogleLatLng>(polygon) {
    override fun remove() {
        p.remove()
    }

    override val id: String
        get() = p.id
    override var points: List<GoogleLatLng>
        get() = p.points.map { GoogleLatLng(it) }
        set(var1) {
            p.points = var1.map { it.ll }
        }
    override var holes: List<List<GoogleLatLng>>
        get() = p.holes.map { it.map { ll -> GoogleLatLng(ll) } }
        set(var1) {
            p.holes = var1.map { it.map { ll -> ll.ll } }
        }
    override var strokeWidth: Float
        get() = p.strokeWidth
        set(var1) {
            p.strokeWidth = var1
        }
    override var strokeColor: Int
        get() = p.strokeColor
        set(var1) {
            p.strokeWidth = var1.toFloat()
        }
    override var strokeJointType: Int
        get() = p.strokeJointType
        set(var1) {
            p.strokeJointType = var1
        }
    override var strokePattern: List<GooglePatternItem>?
        get() = p.strokePattern?.map { GooglePatternItem(it) }
        set(var1) {
            p.strokePattern = var1?.map { it.pi }
        }
    override var fillColor: Int
        get() = p.fillColor
        set(var1) {
            p.fillColor = var1
        }
    override var zIndex: Float
        get() = p.zIndex
        set(var1) {
            p.zIndex = var1
        }
    override var isVisible: Boolean
        get() = p.isVisible
        set(var1) {
            p.isVisible = var1
        }
    override var isGeodesic: Boolean
        get() = p.isGeodesic
        set(var1) {
            p.isGeodesic = var1
        }
    override var isClickable: Boolean
        get() = p.isClickable
        set(var1) {
            p.isClickable = var1
        }
    override var tag: Any?
        get() = p.tag
        set(var1) {
            p.tag = var1
        }
}

class GooglePolygonOptions(polygonOptions: PolygonOptions) : it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions, GooglePatternItem, GoogleLatLng>(polygonOptions) {
    override fun add(vararg points: GoogleLatLng): it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions, GooglePatternItem, GoogleLatLng> {
        po.add(*points.map { it.ll }.toTypedArray())
        return this
    }

    override fun addHole(points: Iterable<GoogleLatLng>): it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions, GooglePatternItem, GoogleLatLng> {
        po.addHole(points.map { it.ll })
        return this
    }

    override val points: List<GoogleLatLng>
        get() = po.points.map { GoogleLatLng(it) }
    override val holes: List<List<GoogleLatLng>>
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
    override var strokePattern: List<GooglePatternItem>?
        get() = po.strokePattern?.map { GooglePatternItem(it) }
        set(value) {
            po.strokePattern(value?.map { it.pi })
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