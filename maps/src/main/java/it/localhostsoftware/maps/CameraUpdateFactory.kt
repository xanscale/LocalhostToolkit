package it.localhostsoftware.maps

import android.content.Context
import android.graphics.Point
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.GoogleCameraUpdateFactory
import it.localhostsoftware.maps.huawei.HuaweiCameraUpdateFactory
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

interface CameraUpdateFactory {
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

    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GoogleCameraUpdateFactory()
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiCameraUpdateFactory()
                else throw IllegalStateException()
    }
}