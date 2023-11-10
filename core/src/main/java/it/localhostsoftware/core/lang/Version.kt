package it.localhostsoftware.core.lang

import android.text.TextUtils
import kotlin.math.max

class Version(private val values: List<Int>) : Comparable<Version> {
    constructor(value: String) : this(value.split("\\.").map { it.toIntOrNull() ?: 0 })

    fun get(pos: Int) = values.getOrElse(pos) { 0 }

    fun take(segments: Int) = Version(values.take(segments))

    override fun compareTo(other: Version): Int {
        repeat(max(values.size, other.values.size)) {
            get(it).compareTo(other.get(it)).let { compare ->
                if (compare != 0) return compare
            }
        }
        return 0
    }

    override fun toString(): String = TextUtils.join(".", values)

    override fun equals(other: Any?) = if (other is Version) compareTo(other) == 0 else false

    override fun hashCode() = values.hashCode()
}