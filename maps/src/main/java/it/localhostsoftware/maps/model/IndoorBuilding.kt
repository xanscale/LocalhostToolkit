package it.localhostsoftware.maps.model

abstract class IndoorBuilding<IB, IL : IndoorLevel<*>>(val ib: IB) {
    abstract val defaultLevelIndex: Int
    abstract val activeLevelIndex: Int
    abstract val levels: List<IL>
    abstract val isUnderground: Boolean
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}