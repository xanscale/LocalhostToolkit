package it.localhostsoftware.maps.google.model

import com.google.android.gms.maps.model.IndoorLevel

class GoogleIndoorLevel(IL: IndoorLevel) : it.localhostsoftware.maps.model.IndoorLevel<IndoorLevel>(IL) {
    override val name: String
        get() = il.name
    override val shortName: String
        get() = il.shortName

    override fun activate() {
        il.activate()
    }

    override fun equals(other: Any?): Boolean = il == other
    override fun hashCode(): Int = il.hashCode()
}