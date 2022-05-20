package it.localhostsoftware.maps;

public abstract class UiSettings<US> {
    private final US us;

    public UiSettings(US us) {
        this.us = us;
    }

    public US getUiSettings() {
        return us;
    }

    abstract public void setZoomControlsEnabled(boolean var1);

    abstract public void setCompassEnabled(boolean var1);

    abstract public void setMyLocationButtonEnabled(boolean var1);

    abstract public void setIndoorLevelPickerEnabled(boolean var1);

    abstract public void setScrollGesturesEnabled(boolean var1);

    abstract public void setZoomGesturesEnabled(boolean var1);

    abstract public void setTiltGesturesEnabled(boolean var1);

    abstract public void setRotateGesturesEnabled(boolean var1);

    abstract public void setScrollGesturesEnabledDuringRotateOrZoom(boolean var1);

    abstract public void setAllGesturesEnabled(boolean var1);

    abstract public void setMapToolbarEnabled(boolean var1);

    abstract public boolean isZoomControlsEnabled();

    abstract public boolean isCompassEnabled();

    abstract public boolean isMyLocationButtonEnabled();

    abstract public boolean isIndoorLevelPickerEnabled();

    abstract public boolean isScrollGesturesEnabled();

    abstract public boolean isScrollGesturesEnabledDuringRotateOrZoom();

    abstract public boolean isZoomGesturesEnabled();

    abstract public boolean isTiltGesturesEnabled();

    abstract public boolean isRotateGesturesEnabled();

    abstract public boolean isMapToolbarEnabled();
}
