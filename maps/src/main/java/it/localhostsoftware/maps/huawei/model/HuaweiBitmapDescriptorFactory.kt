package it.localhostsoftware.maps.huawei.model

import android.graphics.Bitmap
import it.localhostsoftware.maps.model.BitmapDescriptor
import it.localhostsoftware.maps.model.BitmapDescriptorFactory

class HuaweiBitmapDescriptorFactory : BitmapDescriptorFactory {
    override fun fromResource(var0: Int) =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(var0))

    override fun fromAsset(var0: String): BitmapDescriptor<*> =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromAsset(var0))

    override fun fromFile(var0: String): BitmapDescriptor<*> =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromFile(var0))

    override fun fromPath(var0: String): BitmapDescriptor<*> =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromPath(var0))

    override fun defaultMarker(): BitmapDescriptor<*> =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker())

    override fun defaultMarker(var0: Float): BitmapDescriptor<*> =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(var0))

    override fun fromBitmap(var0: Bitmap): BitmapDescriptor<*> =
            BitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(var0))
}