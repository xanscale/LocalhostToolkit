package it.localhostsoftware.core.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * @param threshold of scrolling remaining rows
 * @param block callback with page to load and current total items count
 */
class RecyclerViewEndlessScrollListener(private val threshold: Int = 5, private val block: (page: Int, itemCount: Int) -> Unit) : RecyclerView.OnScrollListener() {
    private var leftItems: Int? = null
    private var currentPage = 0
    private var previousItemCount = 0
    private var loading = true

    private fun getLeftItems(layoutManager: RecyclerView.LayoutManager) = leftItems ?: (threshold * when (layoutManager) {
        is GridLayoutManager -> layoutManager.spanCount
        is StaggeredGridLayoutManager -> layoutManager.spanCount
        else -> 1
    }).also { leftItems = it }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        view.layoutManager?.let { layoutManager ->
            if (layoutManager.itemCount < previousItemCount) {
                currentPage = 0
                previousItemCount = layoutManager.itemCount
                if (layoutManager.itemCount == 0) loading = true
            }
            if (loading && layoutManager.itemCount > previousItemCount) {
                loading = false
                previousItemCount = layoutManager.itemCount
            }
            when (layoutManager) {
                is StaggeredGridLayoutManager -> layoutManager.findLastVisibleItemPositions(null).maxOrNull() ?: 0
                is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
                else -> 0
            }.let { lastVisibleItemPosition ->
                if (!loading && lastVisibleItemPosition + getLeftItems(layoutManager) > layoutManager.itemCount) {
                    currentPage++
                    block(currentPage + 1, layoutManager.itemCount)
                    loading = true
                }
            }
        }
    }
}