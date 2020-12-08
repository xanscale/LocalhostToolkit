package it.localhostsoftware.maps.model;

public abstract class BitmapDescriptor<BR> {
    private final BR br;

    public BitmapDescriptor(BR br) {
        this.br = br;
    }

    public BR getBitmapDescriptor() {
        return br;
    }
}
