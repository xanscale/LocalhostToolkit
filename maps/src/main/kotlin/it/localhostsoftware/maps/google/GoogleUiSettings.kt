package it.localhostsoftware.maps.google

import com.google.android.gms.maps.UiSettings

class GoogleUiSettings(uiSettings: UiSettings) : it.localhostsoftware.maps.UiSettings<UiSettings>(uiSettings) {
    override fun setAllGesturesEnabled(var1: Boolean) {
        uiSettings.setAllGesturesEnabled(var1)
    }

    override var isZoomControlsEnabled: Boolean
        get() = uiSettings.isZoomControlsEnabled
        set(b) {
            uiSettings.isZoomControlsEnabled = b
        }
    override var isCompassEnabled: Boolean
        get() = uiSettings.isCompassEnabled
        set(b) {
            uiSettings.isCompassEnabled = b
        }
    override var isMyLocationButtonEnabled: Boolean
        get() = uiSettings.isMyLocationButtonEnabled
        set(b) {
            uiSettings.isMyLocationButtonEnabled = b
        }
    override var isIndoorLevelPickerEnabled: Boolean
        get() = uiSettings.isIndoorLevelPickerEnabled
        set(b) {
            uiSettings.isIndoorLevelPickerEnabled = b
        }
    override var isScrollGesturesEnabled: Boolean
        get() = uiSettings.isScrollGesturesEnabled
        set(b) {
            uiSettings.isScrollGesturesEnabled = b
        }
    override var isScrollGesturesEnabledDuringRotateOrZoom: Boolean
        get() = uiSettings.isScrollGesturesEnabledDuringRotateOrZoom
        set(b) {
            uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = b
        }
    override var isZoomGesturesEnabled: Boolean
        get() = uiSettings.isZoomGesturesEnabled
        set(b) {
            uiSettings.isZoomGesturesEnabled = b
        }
    override var isTiltGesturesEnabled: Boolean
        get() = uiSettings.isTiltGesturesEnabled
        set(b) {
            uiSettings.isTiltGesturesEnabled = b
        }
    override var isRotateGesturesEnabled: Boolean
        get() = uiSettings.isRotateGesturesEnabled
        set(b) {
            uiSettings.isRotateGesturesEnabled = b
        }
    override var isMapToolbarEnabled: Boolean
        get() = uiSettings.isMapToolbarEnabled
        set(b) {
            uiSettings.isMapToolbarEnabled = b
        }
}