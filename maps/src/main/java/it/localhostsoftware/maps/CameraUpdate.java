package it.localhostsoftware.maps;

public abstract class CameraUpdate<CU> {
    private final CU cu;

    public CameraUpdate(CU cu) {
        this.cu = cu;
    }

    public CU getCameraUpdate() {
        return cu;
    }
}