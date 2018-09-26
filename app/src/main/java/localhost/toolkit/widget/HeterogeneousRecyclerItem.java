package localhost.toolkit.widget;

import androidx.recyclerview.widget.RecyclerView;
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

	public void setExtra(E extra) {
		this.extra = extra;
	}

	@Override public String toString() {
		return extra.toString();
	}

	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof HeterogeneousRecyclerItem)) return false;
		HeterogeneousRecyclerItem<?, ?> that = (HeterogeneousRecyclerItem<?, ?>) o;
		return extra.equals(that.extra);
	}

	@Override public int hashCode() {
		return extra.hashCode();
	}
}
