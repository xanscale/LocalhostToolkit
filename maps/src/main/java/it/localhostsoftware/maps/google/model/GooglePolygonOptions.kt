package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.PolygonOptions

class GooglePolygonOptions(polygonOptions: PolygonOptions) : it.localhostsoftware.maps.model.PolygonOptions<PolygonOptions, GooglePatternItem, GoogleLatLng>(polygonOptions) {
    override fun add(vararg points: GoogleLatLng): it.localhostsoftware.maps.model.PolygonOptions<*, *, *> {
        po.add(*points.map { it.ll }.toTypedArray())
        return this
    }

    override fun addHole(points: Iterable<GoogleLatLng>): it.localhostsoftware.maps.model.PolygonOptions<*, *, *> {
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