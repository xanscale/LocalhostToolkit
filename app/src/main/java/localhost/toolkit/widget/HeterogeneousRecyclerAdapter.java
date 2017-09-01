package localhost.toolkit.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeterogeneousRecyclerAdapter<I extends HeterogeneousRecyclerItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private HashMap<Class, Integer> classToType;
	private ArrayList<Integer> typeToPos;
	private LayoutInflater inflater;
	private List<I> items;

	public HeterogeneousRecyclerAdapter(Context context, List<I> items) {
		setHasStableIds(true);
		this.items = items;
		inflater = LayoutInflater.from(context);
		classToType = new HashMap<>();
		typeToPos = new ArrayList<>();
		notifyTypesChanged();
	}

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return items.get(typeToPos.get(viewType)).onCreateViewHolder(inflater, parent);
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
		items.get(position).onBindViewHolder(viewHolder);
	}

	public I getItem(int position) {
		return items.get(position);
	}

	@Override public int getItemCount() {
		return items.size();
	}

	@Override public int getItemViewType(int position) {
		return classToType.isEmpty() ? super.getItemViewType(position) : classToType.get(items.get(position).getClass());
	}

	@Override public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	public void notifyTypesChanged() {
		for (int i = 0; i < items.size(); i++)
			if (!classToType.containsKey(items.get(i).getClass())) {
				classToType.put(items.get(i).getClass(), typeToPos.size());
				typeToPos.add(i);
			}
	}
}