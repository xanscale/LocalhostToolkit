package localhost.toolkit.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * HeterogeneousItemTouchHelper.attachToRecyclerView(recyclerView);
 */
class HeterogeneousItemTouchHelper private constructor(
        private val adapter: HeterogeneousListAdapter,
        private val dragFlags: Int,
        private val swipeFlags: Int
) : ItemTouchHelper.Callback() {
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.submitList(adapter.currentList.toMutableList().apply {
            removeAt(viewHolder.bindingAdapterPosition)
        })
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) =
            makeMovementFlags(dragFlags, swipeFlags)

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        adapter.submitList(adapter.currentList.toMutableList().apply {
            val fromPosition = viewHolder.bindingAdapterPosition
            val toPosition = target.bindingAdapterPosition
            if (fromPosition < toPosition)
                for (i in fromPosition until toPosition)
                    Collections.swap(this, i, i + 1)
            else
                for (i in fromPosition downTo toPosition + 1)
                    Collections.swap(this, i, i - 1)
        })
        return true
    }

    companion object {
        private const val VERTICAL = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        private const val HORIZONTAL = ItemTouchHelper.START or ItemTouchHelper.END
        fun attachToRecyclerView(recyclerView: RecyclerView) {
            (recyclerView.adapter as? HeterogeneousListAdapter)?.let {
                ItemTouchHelper(when (recyclerView.layoutManager) {
                    is GridLayoutManager -> HeterogeneousItemTouchHelper(it, VERTICAL or HORIZONTAL, 0)
                    is LinearLayoutManager -> HeterogeneousItemTouchHelper(it, VERTICAL, HORIZONTAL)
                    else -> throw IllegalStateException(recyclerView.layoutManager!!.javaClass.simpleName + " is not supported")
                }).attachToRecyclerView(recyclerView)
            } ?: throw IllegalStateException(recyclerView.adapter!!.javaClass.simpleName + " is not supported")
        }
    }
}