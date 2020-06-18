package localhost.toolkit.widget.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
	private RecyclerView.LayoutManager mLayoutManager;
	private int visibleThreshold = 5;
	private int currentPage = 0;
	private int previousTotalItemCount = 0;
	private boolean loading = true;

	/**
	 * RecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(LayoutManager) {})
	 *
	 * @param layoutManager used by RecyclerView
	 */
	public EndlessRecyclerViewScrollListener(RecyclerView.LayoutManager layoutManager) {
		this.mLayoutManager = layoutManager;
		if (layoutManager instanceof GridLayoutManager)
			visibleThreshold = visibleThreshold * ((GridLayoutManager) layoutManager).getSpanCount();
		else if (layoutManager instanceof StaggeredGridLayoutManager)
			visibleThreshold = visibleThreshold * ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
	}

	private int getLastVisibleItem(int[] lastVisibleItemPositions) {
		int maxSize = 0;
		for (int i = 0; i < lastVisibleItemPositions.length; i++) {
			if (i == 0) {
				maxSize = lastVisibleItemPositions[i];
			} else if (lastVisibleItemPositions[i] > maxSize) {
				maxSize = lastVisibleItemPositions[i];
			}
		}
		return maxSize;
	}

	@Override public void onScrolled(@NonNull RecyclerView view, int dx, int dy) {
		int lastVisibleItemPosition = 0;
		int totalItemCount = mLayoutManager.getItemCount();
		if (mLayoutManager instanceof StaggeredGridLayoutManager)
			lastVisibleItemPosition = getLastVisibleItem(((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null));
		else if (mLayoutManager instanceof LinearLayoutManager)
			lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
		if (totalItemCount < previousTotalItemCount) {
			this.currentPage = 0;
			this.previousTotalItemCount = totalItemCount;
			if (totalItemCount == 0) {
				this.loading = true;
			}
		}
		if (loading && (totalItemCount > previousTotalItemCount)) {
			loading = false;
			previousTotalItemCount = totalItemCount;
		}
		if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
			currentPage++;
			onLoadMore(currentPage + 1, totalItemCount);
			loading = true;
		}
	}

	/**
	 * Defines the process for actually loading more data based on page. Triggered only when new data needs to be appended to the list
	 *
	 * @param page            to be loaded
	 * @param totalItemsCount in current list
	 */
	public abstract void onLoadMore(int page, int totalItemsCount);
}