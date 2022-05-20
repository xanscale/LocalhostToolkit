package it.localhostsoftware.maps.model

import android.content.Context
import android.graphics.Bitmap
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import it.localhostsoftware.maps.google.model.GoogleBitmapDescriptorFactory
import it.localhostsoftware.maps.huawei.model.HuaweiBitmapDescriptorFactory

interface BitmapDescriptorFactory {
    companion object {
        fun getInstance(context: Context) =
                if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS) GoogleBitmapDescriptorFactory()
                else if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS) HuaweiBitmapDescriptorFactory()
                else throw IllegalStateException()

        const val HUE_RED = 0.0f
        const val HUE_ORANGE = 30.0f
        const val HUE_YELLOW = 60.0f
        const val HUE_GREEN = 120.0f
        const val HUE_CYAN = 180.0f
        const val HUE_AZURE = 210.0f
        const val HUE_BLUE = 240.0f
        const val HUE_VIOLET = 270.0f
        const val HUE_MAGENTA = 300.0f
        const val HUE_ROSE = 330.0f
    }

    fun fromResource(var0: Int): BitmapDescriptor<*>
    fun fromAsset(var0: String): BitmapDescriptor<*>
    fun fromFile(var0: String): BitmapDescriptor<*>
    fun fromPath(var0: String): BitmapDescriptor<*>
    fun defaultMarker(): BitmapDescriptor<*>
    fun defaultMarker(var0: Float): BitmapDescriptor<*>
    fun fromBitmap(var0: Bitmap): BitmapDescriptor<*>
}