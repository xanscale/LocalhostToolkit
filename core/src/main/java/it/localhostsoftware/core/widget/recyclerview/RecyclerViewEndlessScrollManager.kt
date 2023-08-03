package it.localhostsoftware.core.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * @param recyclerView to be applied on
 * @param block callback with page to load and current total items count
 */
class RecyclerViewEndlessScrollManager(private val recyclerView: RecyclerView, private val block: (page: Int, totalItemsCount: Int) -> Unit) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true

    init {
        visibleThreshold *= when (val it = recyclerView.layoutManager) {
            is GridLayoutManager -> it.spanCount
            is StaggeredGridLayoutManager -> it.spanCount
            else -> 1
        }
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition = when (val it = recyclerView.layoutManager) {
            is StaggeredGridLayoutManager -> it.findLastVisibleItemPositions(null).maxOrNull() ?: 0
            is LinearLayoutManager -> it.findLastVisibleItemPosition()
            else -> 0
        }
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        if (totalItemCount < previousTotalItemCount) {
            currentPage = 0
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) loading = true
        }
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            block(currentPage + 1, totalItemCount)
            loading = true
        }
    }
}