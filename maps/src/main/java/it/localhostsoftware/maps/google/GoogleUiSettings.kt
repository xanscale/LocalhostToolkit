package it.localhostsoftware.maps.google;

import it.localhostsoftware.maps.UiSettings;

public class GoogleUiSettings extends UiSettings<com.google.android.gms.maps.UiSettings> {
    public GoogleUiSettings(com.google.android.gms.maps.UiSettings uiSettings) {
        super(uiSettings);
    }

    @Override
    public void setZoomControlsEnabled(boolean b) {
        getUiSettings().setZoomControlsEnabled(b);
    }

    @Override
    public void setCompassEnabled(boolean b) {
        getUiSettings().setCompassEnabled(b);
    }

    @Override
    public void setMyLocationButtonEnabled(boolean b) {
        getUiSettings().setMyLocationButtonEnabled(b);
    }

    @Override
    public void setIndoorLevelPickerEnabled(boolean b) {
        getUiSettings().setIndoorLevelPickerEnabled(b);
    }

    @Override
    public void setScrollGesturesEnabled(boolean b) {
        getUiSettings().setScrollGesturesEnabled(b);
    }

    @Override
    public void setZoomGesturesEnabled(boolean b) {
        getUiSettings().setZoomGesturesEnabled(b);
    }

    @Override
    public void setTiltGesturesEnabled(boolean b) {
        getUiSettings().setTiltGesturesEnabled(b);
    }

    @Override
    public void setRotateGesturesEnabled(boolean b) {
        getUiSettings().setRotateGesturesEnabled(b);
    }

    @Override
    public void setScrollGesturesEnabledDuringRotateOrZoom(boolean b) {
        getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(b);
    }

    @Override
    public void setAllGesturesEnabled(boolean b) {
        getUiSettings().setAllGesturesEnabled(b);
    }

    @Override
    public void setMapToolbarEnabled(boolean b) {
        getUiSettings().setMapToolbarEnabled(b);
    }

    @Override
    public boolean isZoomControlsEnabled() {
        return getUiSettings().isZoomControlsEnabled();
    }

    @Override
    public boolean isCompassEnabled() {
        return getUiSettings().isCompassEnabled();
    }

    @Override
    public boolean isMyLocationButtonEnabled() {
        return getUiSettings().isMyLocationButtonEnabled();
    }

    @Override
    public boolean isIndoorLevelPickerEnabled() {
        return getUiSettings().isIndoorLevelPickerEnabled();
    }

    @Override
    public boolean isScrollGesturesEnabled() {
        return getUiSettings().isScrollGesturesEnabled();
    }

    @Override
    public boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        return getUiSettings().isScrollGesturesEnabledDuringRotateOrZoom();
    }

    @Override
    public boolean isZoomGesturesEnabled() {
        return getUiSettings().isZoomGesturesEnabled();
    }

    @Override
    public boolean isTiltGesturesEnabled() {
        return getUiSettings().isTiltGesturesEnabled();
    }

    @Override
    public boolean isRotateGesturesEnabled() {
        return getUiSettings().isRotateGesturesEnabled();
    }

    @Override
    public boolean isMapToolbarEnabled() {
        return getUiSettings().isMapToolbarEnabled();
    }
}
