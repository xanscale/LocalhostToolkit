package localhost.toolkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

public abstract class HeterogeneousItem<Extra extends Serializable> {
	protected Context context;
	protected Extra extra;
	private OnHeterogeneousItemClickListener onHeterogeneousItemClickListener;
	private OnHeterogeneousItemLongClickListener onHeterogeneousItemLongClickListener;

	public HeterogeneousItem(Context context, Extra extra) {
		this.context = context;
		this.extra = extra;
	}

	public abstract View onCreateView(LayoutInflater inflater, ViewGroup container);

	public abstract void onResume(View view);

	public boolean onItemClick(View v, int position) {
		return onHeterogeneousItemClickListener != null && onHeterogeneousItemClickListener.onHeterogeneousItemClick(v, position, extra);
	}

	public boolean onItemLongClick(int position) {
		return onHeterogeneousItemLongClickListener != null && onHeterogeneousItemLongClickListener.onHeterogeneousItemLongClick(position, extra);
	}

	public void setOnHeterogeneousItemClickListener(OnHeterogeneousItemClickListener onHeterogeneousItemClickListener) {
		this.onHeterogeneousItemClickListener = onHeterogeneousItemClickListener;
	}

	public void setOnHeterogeneousItemLongClickListener(OnHeterogeneousItemLongClickListener onHeterogeneousItemLongClickListener) {
		this.onHeterogeneousItemLongClickListener = onHeterogeneousItemLongClickListener;
	}

	public Extra getExtra() {
		return extra;
	}

	@Override
	public String toString() {
		return extra.toString();
	}

	public interface OnHeterogeneousItemClickListener {
		boolean onHeterogeneousItemClick(View v, int position, Serializable extra);
	}

	public interface OnHeterogeneousItemLongClickListener {
		boolean onHeterogeneousItemLongClick(int position, Serializable extra);
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HeterogeneousItem)) return false;
		HeterogeneousItem<?> that = (HeterogeneousItem<?>) o;
		return extra.equals(that.extra);
	}

	@Override public int hashCode() {
		return extra.hashCode();
	}
}