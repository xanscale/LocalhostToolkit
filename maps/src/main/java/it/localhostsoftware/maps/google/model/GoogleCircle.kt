package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.Circle
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.PatternItem

class GoogleCircle(circle: Circle) : it.localhostsoftware.maps.model.Circle<Circle>(circle) {
    override fun remove() = c.remove()
    override val id: String
        get() = c.id
    override var center: LatLng<*>
        get() = GoogleLatLng(c.center)
        set(var1) {
            c.center = var1.ll as com.google.android.gms.maps.model.LatLng
        }
    override var radius: Double
        get() = c.radius
        set(var1) {
            c.radius = var1
        }
    override var strokeWidth: Float
        get() = c.strokeWidth
        set(var1) {
            c.strokeWidth = var1
        }
    override var strokeColor: Int
        get() = c.strokeColor
        set(var1) {
            c.strokeColor = var1
        }
    override var strokePattern: List<PatternItem<*>>?
        get() =
            c.strokePattern?.map { PatternItem<com.google.android.gms.maps.model.PatternItem>(it) }
        set(var1) {
            c.strokePattern = var1?.map { it.pi as com.google.android.gms.maps.model.PatternItem }
        }
    override var fillColor: Int
        get() = c.fillColor
        set(var1) {
            c.fillColor = var1
        }
    override var zIndex: Float
        get() = c.zIndex
        set(var1) {
            c.zIndex = var1
        }
    override var isVisible: Boolean
        get() = c.isVisible
        set(var1) {
            c.isVisible = var1
        }
    override var isClickable: Boolean
        get() = c.isClickable
        set(var1) {
            c.isClickable = var1
        }
    override var tag: Any?
        get() = c.tag
        set(var1) {
            c.tag = var1
        }
}