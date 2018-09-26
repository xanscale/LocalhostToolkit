package localhost.toolkit.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

public abstract class HeterogeneousItem<E extends Serializable> {
	protected Context context;
	protected E extra;
	private OnHeterogeneousItemClickListener onHeterogeneousItemClickListener;
	private OnHeterogeneousItemLongClickListener onHeterogeneousItemLongClickListener;

	public HeterogeneousItem(Context context, E extra) {
		this.context = context;
		this.extra = extra;
	}

	@NonNull public abstract View onCreateView(LayoutInflater inflater, ViewGroup container);

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

	public E getExtra() {
		return extra;
	}

	@Override
	public String toString() {
		return extra.toString();
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

	public interface OnHeterogeneousItemClickListener {
		boolean onHeterogeneousItemClick(View v, int position, Serializable extra);
	}

	public interface OnHeterogeneousItemLongClickListener {
		boolean onHeterogeneousItemLongClick(int position, Serializable extra);
	}
}