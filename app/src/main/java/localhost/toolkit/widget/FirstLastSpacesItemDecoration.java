package localhost.toolkit.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

public class FirstLastSpacesItemDecoration extends RecyclerView.ItemDecoration {
	private int marginPx;
	private int orientation;

	FirstLastSpacesItemDecoration(Context context, int orientation, int marginInDp) {
		if (orientation != LinearLayout.HORIZONTAL && orientation != LinearLayout.VERTICAL) {
			throw new IllegalArgumentException("Invalid orientation. It should be either LinearLayout.HORIZONTAL or LinearLayout.VERTICAL");
		}
		this.orientation = orientation;
		this.marginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDp, context.getResources().getDisplayMetrics());
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		if (parent.getChildAdapterPosition(view) == 0 && orientation == LinearLayout.HORIZONTAL)
			outRect.left = marginPx;
		else if (parent.getChildAdapterPosition(view) == 0 && orientation == LinearLayout.VERTICAL)
			outRect.top = marginPx;
		else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1 && orientation == LinearLayout.HORIZONTAL)
			outRect.right = marginPx;
		else if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1 && orientation == LinearLayout.VERTICAL)
			outRect.bottom = marginPx;
	}
}
