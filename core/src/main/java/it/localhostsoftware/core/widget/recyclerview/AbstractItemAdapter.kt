package it.localhostsoftware.core.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
abstract class AbstractItemAdapter<E, B : ViewBinding>(val extra: E) {
    val spanSize: Int
        get() = 1

    internal fun internalOnBindViewHolder(holder: ViewHolder<*>) = onBinding((holder as ViewHolder<B>).binding)
    internal fun internalOnViewRecycled(holder: ViewHolder<*>) = onViewRecycled((holder as ViewHolder<B>).binding)
    abstract fun onCreateViewBinding(inflater: LayoutInflater, parent: ViewGroup): B
    abstract fun onBinding(binding: B)
    open fun onViewRecycled(binding: B) {}
    override fun toString() = extra.toString()
    override fun equals(other: Any?) = extra == (other as AbstractItemAdapter<*, *>).extra
    override fun hashCode() = extra.hashCode()
}