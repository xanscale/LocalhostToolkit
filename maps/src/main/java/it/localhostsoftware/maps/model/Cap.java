package it.localhostsoftware.maps.model;

public class Cap<C> {
    private final C c;

    public Cap(C c) {
        this.c = c;
    }

    public C getCap() {
        return c;
    }
}
