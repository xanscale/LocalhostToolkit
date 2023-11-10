package it.localhostsoftware.core.lang

import android.text.TextUtils
import kotlin.math.max

class Version(value: String) : Comparable<Version> {
    private val values = ArrayList<Int>()

    init {
        value.split("\\.").forEach {
            try {
                values.add(it.toInt())
            } catch (e: Exception) {
                values.add(0)
            }
        }
    }

    fun getVal(pos: Int) =
        if (pos < values.size) values[pos] else 0

    override fun compareTo(other: Version): Int {
        repeat(max(values.size, other.values.size)) {
            getVal(it).compareTo(other.getVal(it)).let { compare ->
                if (compare != 0) return compare
            }
        }
        return 0
    }

    override fun toString(): String = TextUtils.join(".", values)

    override fun equals(other: Any?) = if (other is Version) compareTo(other) == 0 else false

    override fun hashCode() = values.hashCode()
}