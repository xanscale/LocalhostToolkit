package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Circle
import com.huawei.hms.maps.model.CircleOptions

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

class HuaweiCircleOptions(circleOptions: CircleOptions) : it.localhostsoftware.maps.model.CircleOptions<CircleOptions, HuaweiLatLng, HuaweiPatternItem>(circleOptions) {
    override var center: HuaweiLatLng?
        get() = HuaweiLatLng(co.center)
        set(value) {
            value?.let { co.center(value.ll) } ?: throw IllegalStateException()
        }
    override var radius: Double
        get() = co.radius
        set(value) {
            co.radius(value)
        }
    override var strokeWidth: Float
        get() = co.strokeWidth
        set(value) {
            co.strokeWidth(value)
        }
    override var strokeColor: Int
        get() = co.strokeColor
        set(value) {
            co.strokeColor(value)
        }
    override var strokePattern: List<HuaweiPatternItem>?
        get() = co.strokePattern?.map { HuaweiPatternItem(it) }
        set(value) {
            co.strokePattern(value?.map { it.pi })
        }
    override var fillColor: Int
        get() = co.fillColor
        set(value) {
            co.fillColor(value)
        }
    override var zIndex: Float
        get() = co.zIndex
        set(value) {
            co.zIndex(value)
        }
    override var isVisible: Boolean
        get() = co.isVisible
        set(value) {
            co.visible(value)
        }
    override var isClickable: Boolean
        get() = co.isClickable
        set(value) {
            co.clickable(value)
        }
}