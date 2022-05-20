package it.localhostsoftware.maps.model

abstract class IndoorBuilding<IB>(val ib: IB) {
    abstract val defaultLevelIndex: Int
    abstract val activeLevelIndex: Int
    abstract val levels: List<IndoorLevel<*>>
    abstract val isUnderground: Boolean
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}