package it.localhostsoftware.maps.model

abstract class IndoorLevel<IL>(val il: IL) {
    abstract val name: String
    abstract val shortName: String
    abstract fun activate()
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}