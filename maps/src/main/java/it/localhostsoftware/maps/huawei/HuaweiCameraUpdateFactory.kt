package it.localhostsoftware.maps.huawei

import android.graphics.Point
import com.huawei.hms.maps.CameraUpdateFactory
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

class HuaweiCameraUpdateFactory : it.localhostsoftware.maps.CameraUpdateFactory {
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

    override fun newCameraPosition(var0: CameraPosition<*>) =
        HuaweiCameraUpdate(CameraUpdateFactory.newCameraPosition(var0.cp as com.huawei.hms.maps.model.CameraPosition))

    override fun newLatLng(var0: LatLng<*>) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLng(var0.ll as com.huawei.hms.maps.model.LatLng))

    override fun newLatLngZoom(var0: LatLng<*>, var1: Float) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLngZoom(var0.ll as com.huawei.hms.maps.model.LatLng, var1))

    override fun newLatLngBounds(var0: LatLngBounds<*>, var1: Int) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLngBounds(var0.lb as com.huawei.hms.maps.model.LatLngBounds, var1))

    override fun newLatLngBounds(var0: LatLngBounds<*>, var1: Int, var2: Int, var3: Int) =
        HuaweiCameraUpdate(CameraUpdateFactory.newLatLngBounds(var0.lb as com.huawei.hms.maps.model.LatLngBounds, var1, var2, var3))
}