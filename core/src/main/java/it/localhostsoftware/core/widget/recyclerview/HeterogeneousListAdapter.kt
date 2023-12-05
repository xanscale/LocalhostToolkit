package it.localhostsoftware.core.widget.recyclerview

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.util.*
import java.util.regex.Pattern

class HeterogeneousListAdapter : ListAdapter<AbstractItemAdapter<*, *>, ViewHolder<*>>(DiffUtilItemCallback()), Filterable {
    private var filter: Filter? = null
    private val classToType: HashMap<Class<*>, Int> = HashMap()
    private val typeToPos: SparseIntArray = SparseIntArray()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(getItem(typeToPos[viewType]).onCreateViewBinding(LayoutInflater.from(parent.context), parent))

    override fun onBindViewHolder(viewHolder: ViewHolder<*>, position: Int) =
        getItem(position).onBindViewHolderInternal(viewHolder)

    override fun onViewRecycled(holder: ViewHolder<*>) {
        holder.bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let {
            getItem(it).onViewRecycledInternal(holder)
        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder<*>) {
        holder.bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let {
            getItem(it).onViewAttachedToWindowInternal(holder)
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder<*>) {
        holder.bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let {
            getItem(it).onViewDetachedFromWindowInternal(holder)
        }
    }

    override fun getItemViewType(position: Int) =
        classToType[getItem(position).javaClass] ?: super.getItemViewType(position)

    override fun getItemId(position: Int) =
        getItem(position).hashCode().toLong()

    override fun submitList(list: List<AbstractItemAdapter<*, *>>?) =
        submitList(list, null)

    override fun submitList(list: List<AbstractItemAdapter<*, *>>?, commitCallback: Runnable?) =
        super.submitList(list) {
            filter = null
            updateTypes()
            commitCallback?.run()
        }

    private fun applyFilter(list: List<AbstractItemAdapter<*, *>>?) =
        super.submitList(list) { updateTypes() }

    private fun updateTypes() {
        typeToPos.clear()
        for (i in 0 until itemCount) {
            if (!classToType.containsKey(getItem(i).javaClass))
                classToType[getItem(i).javaClass] = classToType.size
            classToType[getItem(i).javaClass].let { type ->
                if (type != null && typeToPos.indexOfKey(type) < 0)
                    typeToPos.put(type, i)
            }
        }
    }

    override fun getFilter(): Filter = filter ?: HeterogeneousFilter().also { filter = it }

    private inner class HeterogeneousFilter : Filter() {
        private val originalItems: List<AbstractItemAdapter<*, *>> = ArrayList(currentList)
        override fun performFiltering(constraint: CharSequence?) = FilterResults().apply {
            if (constraint.isNullOrEmpty()) {
                values = originalItems.toList()
                count = originalItems.size
            } else Pattern.compile(constraint.toFilterRegex()).let { pattern ->
                originalItems.filter { pattern.matcher(it.toString().lowercase(Locale.getDefault())).matches() }
            }.let { newValues ->
                values = newValues
                count = newValues.size
            }

        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            @Suppress("UNCHECKED_CAST")
            applyFilter(results.values as List<AbstractItemAdapter<*, *>>?)
        }

        private fun CharSequence.toFilterRegex() =
            toString().lowercase(Locale.getDefault()).split("\\W".toRegex()).joinToString(separator = "", prefix = "^\\X*", postfix = "\\X*$") { "(?=.*$it)" }
    }

    private class DiffUtilItemCallback : DiffUtil.ItemCallback<AbstractItemAdapter<*, *>>() {
        override fun areItemsTheSame(oldItem: AbstractItemAdapter<*, *>, newItem: AbstractItemAdapter<*, *>): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: AbstractItemAdapter<*, *>, newItem: AbstractItemAdapter<*, *>): Boolean {
            return oldItem == newItem
        }
    }
}

class ViewHolder<B : ViewBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)