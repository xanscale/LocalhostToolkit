package it.localhostsoftware.maps;

public class LatLng {
    public final double latitude;
    public final double longitude;

    public static LatLng fromGoogleLatLng(com.google.android.gms.maps.model.LatLng latLng) {
        return new LatLng(latLng.latitude, latLng.longitude);
    }

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public com.google.android.gms.maps.model.LatLng toGoogleLatLng() {
        return new com.google.android.gms.maps.model.LatLng(latitude, longitude);
    }
}
