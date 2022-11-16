package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.CircleOptions

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