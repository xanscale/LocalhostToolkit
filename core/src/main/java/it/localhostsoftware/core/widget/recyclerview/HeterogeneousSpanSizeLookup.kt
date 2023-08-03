package it.localhostsoftware.core.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup

class HeterogeneousSpanSizeLookup(private val items: List<AbstractItemAdapter<*, *>>) : SpanSizeLookup() {
    override fun getSpanSize(position: Int) =
        if (position < items.size) items[position].spanSize else 1
}