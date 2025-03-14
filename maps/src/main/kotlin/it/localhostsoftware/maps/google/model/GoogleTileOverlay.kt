package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.Tile
import com.google.android.gms.maps.model.TileOverlay
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.android.gms.maps.model.TileProvider

class GoogleTileOverlay(tileOverlay: TileOverlay) : it.localhostsoftware.maps.model.TileOverlay<TileOverlay>(tileOverlay) {
    override var transparency: Float
        get() = to.transparency
        set(value) {
            to.transparency = value
        }
    override var zIndex: Float
        get() = to.zIndex
        set(value) {
            to.zIndex = value
        }
    override val id: String
        get() = to.id
    override var fadeIn: Boolean
        get() = to.fadeIn
        set(value) {
            to.fadeIn = value
        }
    override var visible: Boolean
        get() = to.isVisible
        set(value) {
            to.isVisible = value
        }

    override fun clearTileCache() =
        to.clearTileCache()

    override fun remove() =
        to.remove()
}

class GoogleTileOverlayOptions(tileOverlayOptions: TileOverlayOptions) : it.localhostsoftware.maps.model.TileOverlayOptions<TileOverlayOptions, GoogleTileProvider>(tileOverlayOptions) {
    override var transparency: Float
        get() = too.transparency
        set(value) {
            too.transparency(value)
        }
    override var zIndex: Float
        get() = too.zIndex
        set(value) {
            too.zIndex(value)
        }
    override var fadeIn: Boolean
        get() = too.fadeIn
        set(value) {
            too.fadeIn(value)
        }
    override var tileProvider: GoogleTileProvider?
        get() = too.tileProvider?.let { GoogleTileProvider(it) }
        set(value) {
            value?.let { too.tileProvider(it.tp) } ?: throw IllegalStateException()
        }
    override var visible: Boolean
        get() = too.isVisible
        set(value) {
            too.visible(value)
        }
}

class GoogleTileProvider(tileProvider: TileProvider) : it.localhostsoftware.maps.model.TileProvider<TileProvider, GoogleTile>(tileProvider) {
    override fun getTile(var1: Int, var2: Int, var3: Int) =
        tp.getTile(var1, var2, var3)?.let { GoogleTile(it) }
}

class GoogleTile(tile: Tile) : it.localhostsoftware.maps.model.Tile<Tile>(tile) {
    override val width: Int
        get() = t.width
    override val height: Int
        get() = t.height
    override val data: ByteArray?
        get() = t.data
}