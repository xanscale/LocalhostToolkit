package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.PolylineOptions

class GooglePolylineOptions(polylineOptions: PolylineOptions) : it.localhostsoftware.maps.model.PolylineOptions<PolylineOptions, GooglePatternItem, GoogleLatLng, GoogleCap>(polylineOptions) {
    override fun add(vararg var1: GoogleLatLng): it.localhostsoftware.maps.model.PolylineOptions<PolylineOptions, GooglePatternItem, GoogleLatLng, GoogleCap> {
        po.add(*var1.map { it.ll }.toTypedArray())
        return this
    }

    override val points: List<GoogleLatLng>
        get() = po.points.map { GoogleLatLng(it) }
    override var width: Float
        get() = po.width
        set(value) {
            po.width(value)
        }
    override var color: Int
        get() = po.color
        set(value) {
            po.color(value)
        }
    override var startCap: GoogleCap
        get() = GoogleCap(po.startCap)
        set(value) {
            po.startCap(value.cap)
        }
    override var endCap: GoogleCap
        get() = GoogleCap(po.endCap)
        set(value) {
            po.endCap(value.cap)
        }
    override var jointType: Int
        get() = po.jointType
        set(value) {
            po.jointType(value)
        }
    override var pattern: List<GooglePatternItem>?
        get() = po.pattern?.map { GooglePatternItem(it) }
        set(value) {
            po.pattern(value?.map { it.pi })
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