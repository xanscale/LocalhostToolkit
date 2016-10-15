package localhost.toolkit.widget;

import android.widget.AbsListView;

public abstract class EndlessListViewScrollListener implements AbsListView.OnScrollListener {
	private int visibleThreshold = 5;
	private int currentPage = 0;
	private int previousTotalItemCount = 0;
	private boolean loading = true;
	private int startingPageIndex = 0;

	/**
	 * ListView.setOnScrollListener(new EndlessListViewScrollListener(){...});
	 */
	public EndlessListViewScrollListener() {
	}

	/**
	 * ListView.setOnScrollListener(new EndlessListViewScrollListener(){...});
	 *
	 * @param visibleThreshold The minimum number of items to have below your current scroll position before loading more.
	 */
	public EndlessListViewScrollListener(int visibleThreshold) {
		this.visibleThreshold = visibleThreshold;
	}

	/**
	 * ListView.setOnScrollListener(new EndlessListViewScrollListener(){...});
	 *
	 * @param visibleThreshold The minimum number of items to have below your current scroll position before loading more.
	 * @param startPage        Sets the starting page index
	 */
	public EndlessListViewScrollListener(int visibleThreshold, int startPage) {
		this.visibleThreshold = visibleThreshold;
		this.startingPageIndex = startPage;
		this.currentPage = startPage;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (totalItemCount < previousTotalItemCount) {
			this.currentPage = this.startingPageIndex;
			this.previousTotalItemCount = totalItemCount;
			if (totalItemCount == 0) {
				this.loading = true;
			}
		}
		if (loading && (totalItemCount > previousTotalItemCount)) {
			loading = false;
			previousTotalItemCount = totalItemCount;
			currentPage++;
		}
		if (!loading && (firstVisibleItem + visibleItemCount + visibleThreshold) >= totalItemCount) {
			loading = onLoadMore(currentPage + 1, totalItemCount);
		}
	}

	/**
	 * Defines the process for actually loading more data based on page. Triggered only when new data needs to be appended to the list
	 *
	 * @param page to be loaded
	 * @param totalItemsCount in current list
	 * @return true if more data is being loaded, false if there is no more data to load.
	 */
	public abstract boolean onLoadMore(int page, int totalItemsCount);

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
}