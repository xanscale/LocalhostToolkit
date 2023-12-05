package it.localhostsoftware.core.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
abstract class AbstractItemAdapter<E, B : ViewBinding>(val extra: E) {
    val spanSize: Int
        get() = 1

    internal fun onBindViewHolderInternal(holder: ViewHolder<*>) = onBinding((holder as ViewHolder<B>).binding)
    internal fun onViewRecycledInternal(holder: ViewHolder<*>) = onViewRecycled((holder as ViewHolder<B>).binding)
    internal fun onViewAttachedToWindowInternal(holder: ViewHolder<*>) = onViewAttachedToWindow((holder as ViewHolder<B>).binding)
    internal fun onViewDetachedFromWindowInternal(holder: ViewHolder<*>) = onViewDetachedFromWindow((holder as ViewHolder<B>).binding)

    abstract fun onCreateViewBinding(inflater: LayoutInflater, parent: ViewGroup): B
    abstract fun onBinding(binding: B)

    open fun onViewRecycled(binding: B) {}
    open fun onViewAttachedToWindow(binding: B) {}
    open fun onViewDetachedFromWindow(binding: B) {}

    override fun toString() = extra.toString()
    override fun equals(other: Any?) = extra == (other as? AbstractItemAdapter<*, *>)?.extra
    override fun hashCode() = extra.hashCode()
}