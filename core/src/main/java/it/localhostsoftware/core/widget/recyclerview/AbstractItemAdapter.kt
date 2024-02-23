package it.localhostsoftware.core.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
abstract class AbstractItemAdapter<E, B : ViewBinding>(val extra: E) : LifecycleOwner {
    private val lifecycleRegistry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    val spanSize: Int
        get() = 1

    fun onParentLifecycleStateChanged(newParentLifecycleState: Lifecycle.State) {
        if (newParentLifecycleState < lifecycleRegistry.currentState)
            lifecycleRegistry.currentState = newParentLifecycleState
    }

    internal fun onCreateViewBindingInternal(inflater: LayoutInflater, parent: ViewGroup) = onCreateViewBinding(inflater, parent).also {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    internal fun onBindViewHolderInternal(holder: ViewHolder<*>) = onBinding((holder as ViewHolder<B>).binding).also {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    internal fun onViewAttachedToWindowInternal(holder: ViewHolder<*>) = onViewAttachedToWindow((holder as ViewHolder<B>).binding).also {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    internal fun onViewDetachedFromWindowInternal(holder: ViewHolder<*>) = onViewDetachedFromWindow((holder as ViewHolder<B>).binding).also {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    internal fun onViewRecycledInternal(holder: ViewHolder<*>) = onViewRecycled((holder as ViewHolder<B>).binding).also {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    abstract fun onCreateViewBinding(inflater: LayoutInflater, parent: ViewGroup): B
    abstract fun onBinding(binding: B)
    open fun onViewRecycled(binding: B) {}
    open fun onViewAttachedToWindow(binding: B) {}
    open fun onViewDetachedFromWindow(binding: B) {}

    override fun toString() = extra.toString()
    override fun equals(other: Any?) = extra == (other as? AbstractItemAdapter<*, *>)?.extra
    override fun hashCode() = extra.hashCode()
}