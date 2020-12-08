package it.localhostsoftware.maps;

public interface UiSettings {
    void setZoomControlsEnabled(boolean var1);

    void setCompassEnabled(boolean var1);

    void setMyLocationButtonEnabled(boolean var1);

    void setIndoorLevelPickerEnabled(boolean var1);

    void setScrollGesturesEnabled(boolean var1);

    void setZoomGesturesEnabled(boolean var1);

    void setTiltGesturesEnabled(boolean var1);

    void setRotateGesturesEnabled(boolean var1);

    void setScrollGesturesEnabledDuringRotateOrZoom(boolean var1);

    void setAllGesturesEnabled(boolean var1);

    void setMapToolbarEnabled(boolean var1);

    boolean isZoomControlsEnabled();

    boolean isCompassEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isScrollGesturesEnabled();

    boolean isScrollGesturesEnabledDuringRotateOrZoom();

    boolean isZoomGesturesEnabled();

    boolean isTiltGesturesEnabled();

    boolean isRotateGesturesEnabled();

    boolean isMapToolbarEnabled();
}
