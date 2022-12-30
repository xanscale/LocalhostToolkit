package localhost.toolkit.widget.recyclerview

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

class HeterogeneousListAdapter : ListAdapter<AbstractItemAdapter<*, *>, RecyclerView.ViewHolder>(DiffUtilItemCallback()), Filterable {
    private var filter: Filter? = null
    private val classToType: HashMap<Class<*>, Int> = HashMap()
    private val typeToPos: SparseIntArray = SparseIntArray()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(getItem(typeToPos[viewType]).onCreateViewBinding(LayoutInflater.from(parent.context), parent))

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) =
        getItem(position).internalOnBindViewHolder(viewHolder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
        holder.bindingAdapterPosition.let {
            if (it != RecyclerView.NO_POSITION) getItem(it).internalOnViewRecycled(holder)
        }

    override fun getItemViewType(position: Int) =
        classToType[getItem(position).javaClass] ?: super.getItemViewType(position)

    override fun getItemId(position: Int) =
        getItem(position).hashCode().toLong()

    override fun submitList(list: List<AbstractItemAdapter<*, *>>?) =
        super.submitList(list) {
            filter = null
            updateTypes()
        }

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

    override fun getFilter(): Filter {
        if (filter == null) filter = HeterogeneousFilter()
        return filter!!
    }

    private inner class HeterogeneousFilter : Filter() {
        private val originalItems: List<AbstractItemAdapter<*, *>> = ArrayList(currentList)
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            if (constraint == null || constraint.isEmpty()) {
                results.values = ArrayList(originalItems)
                results.count = originalItems.size
            } else {
                val pattern = Pattern.compile(StringBuilder("^\\X*").apply {
                    constraint.toString().lowercase(Locale.getDefault()).split("\\W").forEach {
                        append("(?=.*").append(it).append(")")
                    }
                    append("\\X*$")
                }.toString())
                val newValues = ArrayList<AbstractItemAdapter<*, *>>()
                originalItems.forEach {
                    if (pattern.matcher(it.toString().lowercase(Locale.getDefault())).matches())
                        newValues.add(it)
                }
                results.values = newValues
                results.count = newValues.size
            }
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            @Suppress("UNCHECKED_CAST")
            applyFilter(results.values as List<AbstractItemAdapter<*, *>>?)
        }
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