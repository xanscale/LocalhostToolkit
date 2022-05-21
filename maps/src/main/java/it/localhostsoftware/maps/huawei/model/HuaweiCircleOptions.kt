package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.CircleOptions
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.PatternItem

class HuaweiCircleOptions(circleOptions: CircleOptions) : it.localhostsoftware.maps.model.CircleOptions<CircleOptions>(circleOptions) {
    override var center: LatLng<*>?
        get() = HuaweiLatLng(co.center)
        set(value) {
            value?.let { co.center(value.ll as com.huawei.hms.maps.model.LatLng) } ?: throw IllegalStateException()
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
    override var strokePattern: List<PatternItem<*>>?
        get() = co.strokePattern?.map { PatternItem(it) }
        set(value) {
            co.strokePattern(value?.map { it.pi as com.huawei.hms.maps.model.PatternItem })
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