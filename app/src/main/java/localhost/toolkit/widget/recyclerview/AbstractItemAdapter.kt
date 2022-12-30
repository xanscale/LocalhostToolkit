package localhost.toolkit.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class AbstractItemAdapter<E, B : ViewBinding>(var extra: E) {
    val spanSize: Int
        get() = 1

    fun internalOnBindViewHolder(holder: ViewHolder<*>) =
        @Suppress("UNCHECKED_CAST")
        onBinding((holder as ViewHolder<B>).binding)

    fun internalOnViewRecycled(holder: ViewHolder<*>) =
        @Suppress("UNCHECKED_CAST")
        onViewRecycled((holder as ViewHolder<B>).binding)

    abstract fun onCreateViewBinding(inflater: LayoutInflater, parent: ViewGroup): B
    abstract fun onBinding(binding: B)
    open fun onViewRecycled(binding: B) {}
    override fun toString() =
        extra.toString()

    override fun equals(other: Any?) =
        extra == (other as AbstractItemAdapter<*, *>).extra

    override fun hashCode() =
        extra.hashCode()
}