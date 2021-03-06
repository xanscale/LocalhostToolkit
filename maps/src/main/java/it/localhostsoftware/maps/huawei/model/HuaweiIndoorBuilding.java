package it.localhostsoftware.maps.huawei.model;

import java.util.ArrayList;
import java.util.List;

import it.localhostsoftware.maps.model.IndoorBuilding;
import it.localhostsoftware.maps.model.IndoorLevel;

public class HuaweiIndoorBuilding extends IndoorBuilding<com.huawei.hms.maps.model.IndoorBuilding> {
    public HuaweiIndoorBuilding(com.huawei.hms.maps.model.IndoorBuilding IB) {
        super(IB);
    }

    @Override
    public int getDefaultLevelIndex() {
        return getIndoorBuilding().getDefaultLevelIndex();
    }

    @Override
    public int getActiveLevelIndex() {
        return getIndoorBuilding().getActiveLevelIndex();
    }

    @Override
    public List<IndoorLevel<?>> getLevels() {
        ArrayList<IndoorLevel<?>> out = new ArrayList<>(getIndoorBuilding().getLevels().size());
        for (com.huawei.hms.maps.model.IndoorLevel value : getIndoorBuilding().getLevels())
            out.add(new HuaweiIndoorLevel(value));
        return out;
    }

    @Override
    public boolean isUnderground() {
        return getIndoorBuilding().isUnderground();
    }

    @Override
    public boolean equals(Object var1) {
        return getIndoorBuilding().equals(var1);
    }

    @Override
    public int hashCode() {
        return getIndoorBuilding().hashCode();
    }
}
