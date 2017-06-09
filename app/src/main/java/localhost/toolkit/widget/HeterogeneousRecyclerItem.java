package localhost.toolkit.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.Serializable;

public abstract class HeterogeneousRecyclerItem<E extends Serializable, H extends RecyclerView.ViewHolder> {
	protected E extra;

	public HeterogeneousRecyclerItem(E extra) {
		this.extra = extra;
	}

	public abstract H onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

	public abstract void onBindViewHolder(H holder);

	public E getExtra() {
		return extra;
	}

	@Override public String toString() {
		return extra.toString();
	}
}
