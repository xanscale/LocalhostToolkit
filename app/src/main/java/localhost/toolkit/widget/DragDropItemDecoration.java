package localhost.toolkit.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

public class DragDropItemDecoration extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {
	private DragDropListener dragDropListener;
	private int selectedDragItemPos = -1;
	private int fingerAnchorY;
	private int fingerY;
	private int fingerOffsetInViewY;
	private BitmapDrawable floatingItem;
	private Rect floatingItemStatingBounds;
	private Rect floatingItemBounds;
	private int viewHandleId;

	private DragDropItemDecoration() {
	}

	public static void addDragDropItemDecoration(RecyclerView recyclerView, int viewHandleId, DragDropListener dragDropListener) {
		final DragDropItemDecoration dec = new DragDropItemDecoration();
		dec.viewHandleId = viewHandleId;
		dec.dragDropListener = dragDropListener;
		recyclerView.addItemDecoration(dec);
		recyclerView.addOnItemTouchListener(dec);
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				dec.fingerAnchorY -= dy;
			}
		});
	}

	@Override public void getItemOffsets(Rect outRect, View view, RecyclerView rv, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, rv, state);
		if (selectedDragItemPos != -1) {
			int itemPos = rv.getChildAdapterPosition(view);
			if (itemPos == selectedDragItemPos)
				view.setVisibility(View.INVISIBLE);
			else {
				view.setVisibility(View.VISIBLE);
				float floatMiddleY = floatingItemBounds.top + floatingItemBounds.height() / 2;
				if ((itemPos > selectedDragItemPos) && (view.getTop() < floatMiddleY)) {
					float amountUp = (floatMiddleY - view.getTop()) / (float) view.getHeight();
					if (amountUp > 1)
						amountUp = 1;
					outRect.top = -(int) (floatingItemBounds.height() * amountUp);
					outRect.bottom = (int) (floatingItemBounds.height() * amountUp);
				}
				if ((itemPos < selectedDragItemPos) && (view.getBottom() > floatMiddleY)) {
					float amountDown = (view.getBottom() - floatMiddleY) / (float) view.getHeight();
					if (amountDown > 1)
						amountDown = 1;
					outRect.top = (int) (floatingItemBounds.height() * amountDown);
					outRect.bottom = -(int) (floatingItemBounds.height() * amountDown);
				}
			}
		} else {
			outRect.top = 0;
			outRect.bottom = 0;
			view.setVisibility(View.VISIBLE);
		}
	}

	@Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		if (floatingItem != null) {
			floatingItem.setAlpha(127);
			floatingItem.draw(c);
		}
	}

	@Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
		View itemView = rv.findChildViewUnder(e.getX(), e.getY());
		if (itemView == null)
			return false;
		View handleView = itemView.findViewById(viewHandleId);
		if (handleView == null || handleView.getVisibility() != View.VISIBLE)
			return false;
		int[] parentItemPos = new int[2];
		int[] handlePos = new int[2];
		itemView.getLocationInWindow(parentItemPos);
		handleView.getLocationInWindow(handlePos);
		if (new Rect(itemView.getLeft() + handlePos[0] - parentItemPos[0], itemView.getTop() + handlePos[1] - parentItemPos[1], itemView.getLeft() + handlePos[0] - parentItemPos[0] + handleView.getWidth(), itemView.getTop() + handlePos[1] - parentItemPos[1] + handleView.getHeight()).contains((int) e.getX(), (int) e.getY())) {
			floatingItemStatingBounds = new Rect(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom());
			floatingItemBounds = new Rect(floatingItemStatingBounds);
			Bitmap bitmap = Bitmap.createBitmap(floatingItemStatingBounds.width(), floatingItemStatingBounds.height(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			itemView.draw(canvas);
			floatingItem = new BitmapDrawable(itemView.getResources(), bitmap);
			floatingItem.setBounds(floatingItemBounds);
			fingerAnchorY = (int) e.getY();
			fingerOffsetInViewY = fingerAnchorY - itemView.getTop();
			fingerY = fingerAnchorY;
			selectedDragItemPos = rv.getChildAdapterPosition(itemView);
			return true;
		} else return false;
	}

	@Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {
		if ((e.getAction() == MotionEvent.ACTION_UP) || (e.getAction() == MotionEvent.ACTION_CANCEL)) {
			if ((e.getAction() == MotionEvent.ACTION_UP) && selectedDragItemPos != -1) {
				float floatMiddleY = floatingItemBounds.top + floatingItemBounds.height() / 2;
				int above = 0;
				int below = Integer.MAX_VALUE;
				for (int n = 0; n < rv.getLayoutManager().getChildCount(); n++) {
					View view = rv.getLayoutManager().getChildAt(n);
					if (view.getVisibility() != View.VISIBLE) continue;
					int itemPos = rv.getChildAdapterPosition(view);
					if (itemPos == selectedDragItemPos) continue;
					float viewMiddleY = view.getTop() + view.getHeight() / 2;
					if (floatMiddleY > viewMiddleY && itemPos > above)
						above = itemPos;
					else if (floatMiddleY <= viewMiddleY && itemPos < below)
						below = itemPos;
				}
				if (dragDropListener != null)
					if (below != Integer.MAX_VALUE) {
						if (below < selectedDragItemPos) below++;
						dragDropListener.onItemMoved(selectedDragItemPos, below - 1);
					} else {
						if (above < selectedDragItemPos) above++;
						dragDropListener.onItemMoved(selectedDragItemPos, above);
					}
			}
			selectedDragItemPos = -1;
			floatingItem = null;
			rv.invalidateItemDecorations();
			return;
		}
		fingerY = (int) e.getY();
		if (floatingItem != null) {
			floatingItemBounds.top = fingerY - fingerOffsetInViewY;
			if (floatingItemBounds.top < -floatingItemStatingBounds.height() / 2)
				floatingItemBounds.top = -floatingItemStatingBounds.height() / 2;
			floatingItemBounds.bottom = floatingItemBounds.top + floatingItemStatingBounds.height();
			floatingItem.setBounds(floatingItemBounds);
		}
		if (fingerY > (rv.getHeight() * 0.9))
			rv.scrollBy(0, (int) ((fingerY - (rv.getHeight() * 0.9f)) * 0.5f));
		else if (fingerY < (rv.getHeight() * 0.1f))
			rv.scrollBy(0, (int) ((fingerY - (rv.getHeight() * 0.1f)) * 0.5f));
		rv.invalidateItemDecorations();
	}

	@Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
	}

	public interface DragDropListener {
		void onItemMoved(int from, int to);
	}
}