package it.localhostsoftware.maps.uiSettings;

public class GoogleUiSettings implements UiSettings {
    private final com.google.android.gms.maps.UiSettings uiSettings;

    public GoogleUiSettings(com.google.android.gms.maps.UiSettings uiSettings) {
        this.uiSettings = uiSettings;
    }

    @Override
    public void setZoomControlsEnabled(boolean b) {
        uiSettings.setZoomControlsEnabled(b);
    }

    @Override
    public void setCompassEnabled(boolean b) {
        uiSettings.setCompassEnabled(b);
    }

    @Override
    public void setMyLocationButtonEnabled(boolean b) {
        uiSettings.setMyLocationButtonEnabled(b);
    }

    @Override
    public void setIndoorLevelPickerEnabled(boolean b) {
        uiSettings.setIndoorLevelPickerEnabled(b);
    }

    @Override
    public void setScrollGesturesEnabled(boolean b) {
        uiSettings.setScrollGesturesEnabled(b);
    }

    @Override
    public void setZoomGesturesEnabled(boolean b) {
        uiSettings.setZoomGesturesEnabled(b);
    }

    @Override
    public void setTiltGesturesEnabled(boolean b) {
        uiSettings.setTiltGesturesEnabled(b);
    }

    @Override
    public void setRotateGesturesEnabled(boolean b) {
        uiSettings.setRotateGesturesEnabled(b);
    }

    @Override
    public void setScrollGesturesEnabledDuringRotateOrZoom(boolean b) {
        uiSettings.setScrollGesturesEnabledDuringRotateOrZoom(b);
    }

    @Override
    public void setAllGesturesEnabled(boolean b) {
        uiSettings.setAllGesturesEnabled(b);
    }

    @Override
    public void setMapToolbarEnabled(boolean b) {
        uiSettings.setMapToolbarEnabled(b);
    }

    @Override
    public boolean isZoomControlsEnabled() {
        return uiSettings.isZoomControlsEnabled();
    }

    @Override
    public boolean isCompassEnabled() {
        return uiSettings.isCompassEnabled();
    }

    @Override
    public boolean isMyLocationButtonEnabled() {
        return uiSettings.isMyLocationButtonEnabled();
    }

    @Override
    public boolean isIndoorLevelPickerEnabled() {
        return uiSettings.isIndoorLevelPickerEnabled();
    }

    @Override
    public boolean isScrollGesturesEnabled() {
        return uiSettings.isScrollGesturesEnabled();
    }

    @Override
    public boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        return uiSettings.isScrollGesturesEnabledDuringRotateOrZoom();
    }

    @Override
    public boolean isZoomGesturesEnabled() {
        return uiSettings.isZoomGesturesEnabled();
    }

    @Override
    public boolean isTiltGesturesEnabled() {
        return uiSettings.isTiltGesturesEnabled();
    }

    @Override
    public boolean isRotateGesturesEnabled() {
        return uiSettings.isRotateGesturesEnabled();
    }

    @Override
    public boolean isMapToolbarEnabled() {
        return uiSettings.isMapToolbarEnabled();
    }
}
