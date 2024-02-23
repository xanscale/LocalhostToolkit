package it.localhostsoftware.core.widget.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.viewbinding.ViewBinding

@Suppress("UNCHECKED_CAST")
abstract class AbstractItemAdapter<E, B : ViewBinding>(parentLifecycle: Lifecycle, val extra: E) : LifecycleOwner {
    private lateinit var lifecycleRegistry: LifecycleRegistry
    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    val spanSize: Int
        get() = 1

    init {
        parentLifecycle.addObserver(LifecycleEventObserver { source, _ ->
            if (source.lifecycle.currentState < lifecycleRegistry.currentState)
                lifecycleRegistry.currentState = source.lifecycle.currentState
        })
    }

    internal fun onCreateViewBindingInternal(inflater: LayoutInflater, parent: ViewGroup) = onCreateViewBinding(inflater, parent).also {
        lifecycleRegistry = LifecycleRegistry(this)
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
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
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