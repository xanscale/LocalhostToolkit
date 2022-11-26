package it.localhostsoftware.maps.model

import android.content.Context
import it.localhostsoftware.maps.MobileServices
import it.localhostsoftware.maps.getMobileServices
import it.localhostsoftware.maps.google.model.GoogleTileOverlayOptions
import it.localhostsoftware.maps.huawei.model.HuaweiTileOverlayOptions

abstract class TileOverlay<TO>(val to: TO) {
    abstract var transparency: Float
    abstract var zIndex: Float
    abstract val id: String
    abstract var fadeIn: Boolean
    abstract var visible: Boolean
    abstract fun clearTileCache()
    abstract fun remove()
}

abstract class TileOverlayOptions<TOO, TP : TileProvider<*, *>>(val too: TOO) {
    companion object {
        fun getInstance(c: Context) =
            when (c.getMobileServices()) {
                MobileServices.GOOGLE -> GoogleTileOverlayOptions(com.google.android.gms.maps.model.TileOverlayOptions())
                MobileServices.HUAWEI -> HuaweiTileOverlayOptions(com.huawei.hms.maps.model.TileOverlayOptions())
                else -> throw IllegalStateException()
            }
    }

    abstract var transparency: Float
    abstract var zIndex: Float
    abstract var fadeIn: Boolean
    abstract var tileProvider: TP?
    abstract var visible: Boolean
}

abstract class TileProvider<TP, T : Tile<*>>(val tp: TP) {
    abstract fun getTile(var1: Int, var2: Int, var3: Int): T?
}

abstract class Tile<T>(val t: T) {
    abstract val width: Int
    abstract val height: Int
    abstract val data: ByteArray?
}