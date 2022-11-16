package it.localhostsoftware.maps.model

import android.content.Context
import android.graphics.Bitmap
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleBitmapDescriptorFactory
import it.localhostsoftware.maps.huawei.model.HuaweiBitmapDescriptorFactory

interface BitmapDescriptorFactory<BD : BitmapDescriptor<*>> {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleBitmapDescriptorFactory()
                MobileServices.HUAWEI -> HuaweiBitmapDescriptorFactory()
                else -> throw IllegalStateException()
            }

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

    fun fromResource(var0: Int): BD
    fun fromAsset(var0: String): BD
    fun fromFile(var0: String): BD
    fun fromPath(var0: String): BD
    fun defaultMarker(): BD
    fun defaultMarker(var0: Float): BD
    fun fromBitmap(var0: Bitmap): BD
}