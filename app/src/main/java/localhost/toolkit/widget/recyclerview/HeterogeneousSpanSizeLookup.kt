package localhost.toolkit.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup

class HeterogeneousSpanSizeLookup(private val items: List<HeterogeneousRecyclerItem<*, *>>) : SpanSizeLookup() {
    override fun getSpanSize(position: Int) =
        if (position < items.size) items[position].spanSize else 1
}