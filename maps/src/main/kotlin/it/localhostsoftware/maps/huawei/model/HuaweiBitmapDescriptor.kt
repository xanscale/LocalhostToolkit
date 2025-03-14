package it.localhostsoftware.maps.huawei.model

import android.graphics.Bitmap
import com.huawei.hms.maps.model.BitmapDescriptor
import it.localhostsoftware.maps.model.BitmapDescriptorFactory

class HuaweiBitmapDescriptor(cu: BitmapDescriptor) : it.localhostsoftware.maps.model.BitmapDescriptor<BitmapDescriptor>(cu)
class HuaweiBitmapDescriptorFactory : BitmapDescriptorFactory<HuaweiBitmapDescriptor> {
    override fun fromResource(var0: Int) =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(var0))

    override fun fromAsset(var0: String) =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromAsset(var0))

    override fun fromFile(var0: String) =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromFile(var0))

    override fun fromPath(var0: String) =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromPath(var0))

    override fun defaultMarker() =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker())

    override fun defaultMarker(var0: Float) =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(var0))

    override fun fromBitmap(var0: Bitmap) =
        HuaweiBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(var0))
}