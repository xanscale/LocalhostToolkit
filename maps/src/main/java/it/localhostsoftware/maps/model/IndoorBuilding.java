package it.localhostsoftware.maps.model;

import java.util.List;

public abstract class IndoorBuilding<IB> {
    private final IB IB;

    public IndoorBuilding(IB IB) {
        this.IB = IB;
    }

    public IB getIndoorBuilding() {
        return IB;
    }

    public abstract int getDefaultLevelIndex();

    public abstract int getActiveLevelIndex();

    public abstract List<IndoorLevel<?>> getLevels();

    public abstract boolean isUnderground();

    public abstract boolean equals(Object var1);

    public abstract int hashCode();
}