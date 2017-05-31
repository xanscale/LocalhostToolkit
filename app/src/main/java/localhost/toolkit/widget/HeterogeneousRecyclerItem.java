package localhost.toolkit.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.Serializable;

public abstract class HeterogeneousRecyclerItem<Extra extends Serializable, VH extends RecyclerView.ViewHolder> {
	protected Extra extra;

	public HeterogeneousRecyclerItem(Extra extra) {
		this.extra = extra;
	}

	public abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

	public abstract void onBindViewHolder(VH holder);

	public Extra getExtra() {
		return extra;
	}

	@Override public String toString() {
		return extra.toString();
	}
}
