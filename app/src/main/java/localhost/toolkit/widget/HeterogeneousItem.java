package localhost.toolkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

public abstract class HeterogeneousItem {
	protected Context context;
	protected Serializable extra;
	private OnHeterogeneousItemClickListener onHeterogeneousItemClickListener;
	private OnHeterogeneousItemLongClickListener onHeterogeneousItemLongClickListener;

	public HeterogeneousItem(Context context, Serializable extra) {
		this.context = context;
		this.extra = extra;
	}

	public abstract View onCreateView(LayoutInflater inflater, ViewGroup container);

	public abstract void onResume(View view);

	public boolean onItemClick(View v, int position) {
		if (onHeterogeneousItemClickListener != null)
			return onHeterogeneousItemClickListener.onHeterogeneousItemClick(v, position, extra);
		else
			return false;
	}

	public boolean onItemLongClick(int position) {
		if (onHeterogeneousItemLongClickListener != null)
			return onHeterogeneousItemLongClickListener.onHeterogeneousItemLongClick(position, extra);
		else
			return false;
	}

	public void setOnHeterogeneousItemClickListener(OnHeterogeneousItemClickListener onHeterogeneousItemClickListener) {
		this.onHeterogeneousItemClickListener = onHeterogeneousItemClickListener;
	}

	public void setOnHeterogeneousItemLongClickListener(OnHeterogeneousItemLongClickListener onHeterogeneousItemLongClickListener) {
		this.onHeterogeneousItemLongClickListener = onHeterogeneousItemLongClickListener;
	}

	public Serializable getExtra() {
		return extra;
	}

	public interface OnHeterogeneousItemClickListener {
		boolean onHeterogeneousItemClick(View v, int position, Serializable extra);
	}

	public interface OnHeterogeneousItemLongClickListener {
		boolean onHeterogeneousItemLongClick(int position, Serializable extra);
	}
}