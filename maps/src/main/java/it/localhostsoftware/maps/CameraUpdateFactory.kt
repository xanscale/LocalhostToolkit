package it.localhostsoftware.maps

import android.content.Context
import android.graphics.Point
import it.localhostsoftware.maps.google.GoogleCameraUpdateFactory
import it.localhostsoftware.maps.huawei.HuaweiCameraUpdateFactory
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

interface CameraUpdateFactory<CU : CameraUpdate<*>, CP : CameraPosition<*, *>, LL : LatLng<*>, LLB : LatLngBounds<*, *>> {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleCameraUpdateFactory()
                MobileServices.HUAWEI -> HuaweiCameraUpdateFactory()
                else -> throw IllegalStateException()
            }
    }

    fun zoomIn(): CU
    fun zoomOut(): CU
    fun scrollBy(var0: Float, var1: Float): CU
    fun zoomTo(var0: Float): CU
    fun zoomBy(var0: Float): CU
    fun zoomBy(var0: Float, var1: Point): CU
    fun newCameraPosition(var0: CP): CU
    fun newLatLng(var0: LL): CU
    fun newLatLngZoom(var0: LL, var1: Float): CU
    fun newLatLngBounds(var0: LLB, var1: Int): CU
    fun newLatLngBounds(var0: LLB, var1: Int, var2: Int, var3: Int): CU
}