package it.localhostsoftware.maps

import android.content.Context
import android.graphics.Point
import it.localhostsoftware.maps.google.GoogleCameraUpdateFactory
import it.localhostsoftware.maps.huawei.HuaweiCameraUpdateFactory
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

interface CameraUpdateFactory {
    companion object {
        fun getInstance(c: Context) =
                when (c.getMobileServices()) {
                    MobileServices.GOOGLE -> GoogleCameraUpdateFactory()
                    MobileServices.HUAWEI -> HuaweiCameraUpdateFactory()
                    else -> throw IllegalStateException()
                }
    }

    fun zoomIn(): CameraUpdate<*>
    fun zoomOut(): CameraUpdate<*>
    fun scrollBy(var0: Float, var1: Float): CameraUpdate<*>
    fun zoomTo(var0: Float): CameraUpdate<*>
    fun zoomBy(var0: Float): CameraUpdate<*>
    fun zoomBy(var0: Float, var1: Point): CameraUpdate<*>
    fun newCameraPosition(var0: CameraPosition<*>): CameraUpdate<*>
    fun newLatLng(var0: LatLng<*>): CameraUpdate<*>
    fun newLatLngZoom(var0: LatLng<*>, var1: Float): CameraUpdate<*>
    fun newLatLngBounds(var0: LatLngBounds<*>, var1: Int): CameraUpdate<*>
    fun newLatLngBounds(var0: LatLngBounds<*>, var1: Int, var2: Int, var3: Int): CameraUpdate<*>
}