package localhost.toolkit.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class HeterogeneousRecyclerItem<E, H : RecyclerView.ViewHolder>(var extra: E) {
    fun internalOnBindViewHolder(holder: RecyclerView.ViewHolder) =
        @Suppress("UNCHECKED_CAST")
        onBindViewHolder(holder as H)

    fun internalOnViewRecycled(holder: RecyclerView.ViewHolder) =
        @Suppress("UNCHECKED_CAST")
        onViewRecycled(holder as H)

    abstract fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): H
    abstract fun onBindViewHolder(holder: H)
    open fun onViewRecycled(holder: H) {}
    val spanSize: Int
        get() = 1

    override fun toString() =
        extra.toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HeterogeneousRecyclerItem<*, *>) return false
        return extra == other.extra
    }

    override fun hashCode() =
        extra.hashCode()
}