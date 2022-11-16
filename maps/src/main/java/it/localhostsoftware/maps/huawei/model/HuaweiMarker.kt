package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.Marker
import it.localhostsoftware.maps.model.BitmapDescriptor
import it.localhostsoftware.maps.model.LatLng

class HuaweiMarker(marker: Marker) : it.localhostsoftware.maps.model.Marker<Marker, HuaweiLatLng, HuaweiBitmapDescriptor>(marker) {
    override fun remove() {
        m.remove()
    }

    override val id: String
        get() = m.id
    override var position: HuaweiLatLng
        get() = HuaweiLatLng(m.position)
        set(value) {
            m.position = value.ll
        }
    override var zIndex: Float
        get() = m.zIndex
        set(v) {
            m.zIndex = v
        }

    override fun setIcon(var1: HuaweiBitmapDescriptor?) {
        m.setIcon(var1?.bitmapDescriptor)
    }

    override fun setAnchor(var1: Float, var2: Float) {
        m.setMarkerAnchor(var1, var2)
    }

    override fun setInfoWindowAnchor(var1: Float, var2: Float) {
        m.setInfoWindowAnchor(var1, var2)
    }

    override var title: String?
        get() = m.title
        set(s) {
            m.title = s
        }
    override var snippet: String?
        get() = m.snippet
        set(s) {
            m.snippet = s
        }
    override var isDraggable: Boolean
        get() = m.isDraggable
        set(b) {
            m.isDraggable = b
        }

    override fun showInfoWindow() {
        m.showInfoWindow()
    }

    override fun hideInfoWindow() {
        m.hideInfoWindow()
    }

    override val isInfoWindowShown: Boolean
        get() = m.isInfoWindowShown
    override var isVisible: Boolean
        get() = m.isVisible
        set(b) {
            m.isVisible = b
        }
    override var isFlat: Boolean
        get() = m.isFlat
        set(b) {
            m.isFlat = b
        }
    override var rotation: Float
        get() = m.rotation
        set(v) {
            m.rotation = v
        }
    override var alpha: Float
        get() = m.alpha
        set(v) {
            m.alpha = v
        }
    override var tag: Any?
        get() = m.tag
        set(o) {
            m.tag = o
        }
}