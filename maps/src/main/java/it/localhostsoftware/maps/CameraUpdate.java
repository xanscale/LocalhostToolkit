package it.localhostsoftware.maps;

public class CameraUpdate<CU> {
    private final CU cu;

    public CameraUpdate(CU cu) {
        this.cu = cu;
    }

    public CU getCameraUpdate() {
        return cu;
    }
}