package localhost.toolkit.widget.recyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * RecyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(LayoutManager) {})
 */
abstract class EndlessRecyclerViewScrollListener(private val mLayoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true

    init {
        visibleThreshold *= when (mLayoutManager) {
            is GridLayoutManager -> mLayoutManager.spanCount
            is StaggeredGridLayoutManager -> mLayoutManager.spanCount
            else -> 1
        }
    }

    /**
     * Defines the process for actually loading more data based on page. Triggered only when new data needs to be appended to the list
     *
     * @param page            to be loaded
     * @param totalItemsCount in current list
     */
    abstract fun onLoadMore(page: Int, totalItemsCount: Int)
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        val lastVisibleItemPosition = when (mLayoutManager) {
            is StaggeredGridLayoutManager -> mLayoutManager.findLastVisibleItemPositions(null).maxOrNull() ?: 0
            is LinearLayoutManager -> mLayoutManager.findLastVisibleItemPosition()
            else -> 0
        }
        val totalItemCount = mLayoutManager.itemCount
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
            onLoadMore(currentPage + 1, totalItemCount)
            loading = true
        }
    }
}