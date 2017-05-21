package localhost.toolkit.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

public class HeterogeneousRecyclerAdapter<HI extends HeterogeneousRecyclerItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private HashMap<Class, Integer> classToType;
	private SparseIntArray typeToPos;
	private LayoutInflater inflater;
	private List<HI> items;

	public HeterogeneousRecyclerAdapter(Context context, List<HI> items) {
		this.items = items;
		inflater = LayoutInflater.from(context);
		classToType = new HashMap<>();
		typeToPos = new SparseIntArray();
		int typeCount = 0;
		for (int i = 0; i < items.size(); i++)
			if (!classToType.containsKey(items.get(i).getClass())) {
				classToType.put(items.get(i).getClass(), typeCount);
				typeToPos.append(typeCount++, i);
			}
	}

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return items.get(typeToPos.get(viewType)).onCreateViewHolder(inflater, parent);
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
		items.get(position).onBindViewHolder(viewHolder, this);
	}

	public HI getItem(int position) {
		return items.get(position);
	}

	@Override public int getItemCount() {
		return items.size();
	}

	@Override public int getItemViewType(int position) {
		return classToType.isEmpty() ? super.getItemViewType(position) : classToType.get(items.get(position).getClass());
	}
}
