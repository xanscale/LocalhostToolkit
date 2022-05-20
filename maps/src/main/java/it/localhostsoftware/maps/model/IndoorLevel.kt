package it.localhostsoftware.maps.model;

import androidx.annotation.NonNull;

public abstract class IndoorLevel<IL> {
    private final IL IL;

    public IndoorLevel(IL IL) {
        this.IL = IL;
    }

    public IL getIndoorLevel() {
        return IL;
    }

    @NonNull
    public abstract String getName();

    @NonNull
    public abstract String getShortName();

    public abstract void activate();

    public abstract boolean equals(Object var1);

    public abstract int hashCode();
}
