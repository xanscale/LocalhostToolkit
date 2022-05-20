package it.localhostsoftware.maps.huawei.model

import com.huawei.hms.maps.model.IndoorBuilding
import it.localhostsoftware.maps.model.IndoorLevel

class HuaweiIndoorBuilding(ib: IndoorBuilding) : it.localhostsoftware.maps.model.IndoorBuilding<IndoorBuilding>(ib) {
    override val defaultLevelIndex: Int
        get() = ib.defaultLevelIndex
    override val activeLevelIndex: Int
        get() = ib.activeLevelIndex
    override val levels: List<IndoorLevel<*>>
        get() = ib.levels.map { HuaweiIndoorLevel(it) }
    override val isUnderground: Boolean
        get() = ib.isUnderground

    override fun equals(other: Any?): Boolean = ib == other
    override fun hashCode(): Int = ib.hashCode()
}