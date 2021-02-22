package it.localhostsoftware.maps.huawei.model;

import androidx.annotation.NonNull;

import it.localhostsoftware.maps.model.IndoorLevel;

public class HuaweiIndoorLevel extends IndoorLevel<com.huawei.hms.maps.model.IndoorLevel> {
    public HuaweiIndoorLevel(com.huawei.hms.maps.model.IndoorLevel IL) {
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
