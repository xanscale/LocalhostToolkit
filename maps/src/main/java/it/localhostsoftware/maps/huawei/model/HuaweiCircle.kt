package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Circle

class HuaweiCircle(circle: Circle) : it.localhostsoftware.maps.model.Circle<Circle, HuaweiPatternItem, HuaweiLatLng>(circle) {
    override fun remove() {
        c.remove()
    }

    override val id: String
        get() = c.id
    override var center: HuaweiLatLng
        get() = HuaweiLatLng(c.center)
        set(var1) {
            c.center = var1.ll
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
    override var strokePattern: List<HuaweiPatternItem>?
        get() =
            c.strokePattern?.map { HuaweiPatternItem(it) }
        set(var1) {
            c.strokePattern = var1?.map { it.pi }
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
            c.setTag(var1)
        }
}