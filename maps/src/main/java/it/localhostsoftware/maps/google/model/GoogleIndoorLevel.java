package it.localhostsoftware.maps.google.model;

import androidx.annotation.NonNull;

import it.localhostsoftware.maps.model.IndoorLevel;

public class GoogleIndoorLevel extends IndoorLevel<com.google.android.gms.maps.model.IndoorLevel> {
    public GoogleIndoorLevel(com.google.android.gms.maps.model.IndoorLevel IL) {
        super(IL);
    }

    @NonNull
    @Override
    public String getName() {
        return getIndoorLevel().getName();
    }

    @NonNull
    @Override
    public String getShortName() {
        return getIndoorLevel().getShortName();
    }

    @Override
    public void activate() {
        getIndoorLevel().activate();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object var1) {
        return getIndoorLevel().equals(var1);
    }

    @Override
    public int hashCode() {
        return getIndoorLevel().hashCode();
    }
}
