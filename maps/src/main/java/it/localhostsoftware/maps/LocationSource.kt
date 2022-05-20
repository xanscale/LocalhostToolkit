package it.localhostsoftware.maps;

import android.location.Location;

public interface LocationSource {
    void activate(OnLocationChangedListener var1);

    void deactivate();

    interface OnLocationChangedListener {
        void onLocationChanged(Location var1);
    }
}
