package it.localhostsoftware.maps.huawei

import android.graphics.Point
import com.huawei.hms.maps.CameraUpdate
import com.huawei.hms.maps.CameraUpdateFactory
import it.localhostsoftware.maps.huawei.model.HuaweiCameraPosition
import it.localhostsoftware.maps.huawei.model.HuaweiLatLng
import it.localhostsoftware.maps.huawei.model.HuaweiLatLngBounds

class HuaweiCameraUpdate(cu: CameraUpdate) : it.localhostsoftware.maps.CameraUpdate<CameraUpdate>(cu)
class HuaweiCameraUpdateFactory : it.localhostsoftware.maps.CameraUpdateFactory<HuaweiCameraUpdate, HuaweiCameraPosition, HuaweiLatLng, HuaweiLatLngBounds> {
    override fun zoomIn() =
        HuaweiCameraUpdate(CameraUpdateFactory.zoomIn())

    override fun zoomOut() =
        HuaweiCameraUpdate(CameraUpdateFactory.zoomOut())

    override fun scrollBy(var0: Float, var1: Float) =
        HuaweiCameraUpdate(CameraUpdateFactory.scrollBy(var0, var1))

    override fun zoomTo(var0: Float) =
        HuaweiCameraUpdate(CameraUpdateFactory.zoomTo(var0))

    override fun zoomBy(var0: Float) =
        HuaweiCameraUpdate(CameraUpdateFactory.zoomBy(var0))

    override fun zoomBy(var0: Float, var1: Point) =
        HuaweiCameraUpdate(CameraUpdateFactory.zoomBy(var0, var1))

    override fun newCameraPosition(var0: HuaweiCameraPosition) =
        HuaweiCameraUpdate(CameraUpdateFactory.newCameraPosition(var0.cp))

    override fun newLatLng(var0: HuaweiLatLng) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLng(var0.ll))

    override fun newLatLngZoom(var0: HuaweiLatLng, var1: Float) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLngZoom(var0.ll, var1))

    override fun newLatLngBounds(var0: HuaweiLatLngBounds, var1: Int) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLngBounds(var0.lb, var1))

    override fun newLatLngBounds(var0: HuaweiLatLngBounds, var1: Int, var2: Int, var3: Int) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLngBounds(var0.lb, var1, var2, var3))
}