package localhost.toolkit.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

public class StartEndSpacesItemDecoration extends RecyclerView.ItemDecoration {
	private int startMarginPx;
	private int endMarginPx;
	private int orientation;
	private boolean isLayoutDirectionLtr;

	public StartEndSpacesItemDecoration(Context context, int orientation, int startMarginInDp, int endMarginInDp) {
		if (orientation != LinearLayout.HORIZONTAL && orientation != LinearLayout.VERTICAL)
			throw new IllegalArgumentException("Invalid orientation. It should be either LinearLayout.HORIZONTAL or LinearLayout.VERTICAL");
		this.orientation = orientation;
		this.startMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, startMarginInDp, context.getResources().getDisplayMetrics());
		this.endMarginPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, endMarginInDp, context.getResources().getDisplayMetrics());
		isLayoutDirectionLtr = context.getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		int childAdapterPosition = parent.getChildAdapterPosition(view);
		boolean isFirst = childAdapterPosition == 0;
		boolean isLast = childAdapterPosition == parent.getAdapter().getItemCount() - 1;
		if (orientation == LinearLayout.VERTICAL) {
			if (isFirst)
				outRect.top = startMarginPx;
			else if (isLast)
				outRect.bottom = endMarginPx;
		} else if (isLayoutDirectionLtr) {
			if (isFirst)
				outRect.left = startMarginPx;
			else if (isLast)
				outRect.right = endMarginPx;
		} else {
			if (isFirst)
				outRect.right = startMarginPx;
			else if (isLast)
				outRect.left = endMarginPx;
		}
	}
}
