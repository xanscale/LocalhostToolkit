package it.localhostsoftware.maps.google.model

import it.localhostsoftware.maps.model.IndoorBuilding

class GoogleIndoorBuilding(ib: com.google.android.gms.maps.model.IndoorBuilding) : IndoorBuilding<com.google.android.gms.maps.model.IndoorBuilding, GoogleIndoorLevel>(ib) {
    override val defaultLevelIndex: Int
        get() = ib.defaultLevelIndex
    override val activeLevelIndex: Int
        get() = ib.activeLevelIndex
    override val levels: List<GoogleIndoorLevel>
        get() = ib.levels.map { GoogleIndoorLevel(it) }
    override val isUnderground: Boolean
        get() = ib.isUnderground

    override fun equals(other: Any?): Boolean = ib == other
    override fun hashCode(): Int = ib.hashCode()
}