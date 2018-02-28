package localhost.toolkit.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class HeterogeneousRecyclerAdapter<I extends HeterogeneousRecyclerItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
	private HeterogeneousFilter heterogeneousFilter;
	private HashMap<Class, Integer> classToType;
	private ArrayList<Integer> typeToPos;
	private LayoutInflater inflater;
	private List<I> items;
	private List<I> originalItems;

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
		classToType.clear();
		typeToPos.clear();
		for (int i = 0; i < items.size(); i++)
			if (!classToType.containsKey(items.get(i).getClass())) {
				classToType.put(items.get(i).getClass(), typeToPos.size());
				typeToPos.add(i);
			}
		notifyDataSetChanged();
	}

	@Override public @NonNull Filter getFilter() {
		if (heterogeneousFilter == null)
			heterogeneousFilter = new HeterogeneousFilter();
		return heterogeneousFilter;
	}

	private class HeterogeneousFilter extends Filter {
		@Override protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			if (originalItems == null)
				originalItems = new ArrayList<>(items);
			if (constraint == null || constraint.length() == 0) {
				results.values = new ArrayList<>(originalItems);
				results.count = originalItems.size();
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append("^\\X*");
				for (String word : constraint.toString().toLowerCase().split("\\W\\D"))
					sb.append("(?=.*").append(word).append(")");
				sb.append("\\X*$");
				ArrayList<I> newValues = new ArrayList<>();
				Pattern pattern = Pattern.compile(sb.toString());
				for (I value : originalItems)
					if (pattern.matcher(value.toString().toLowerCase()).matches())
						newValues.add(value);
				results.values = newValues;
				results.count = newValues.size();
			}
			return results;
		}

		@Override protected void publishResults(CharSequence constraint, FilterResults results) {
			items = (List<I>) results.values;
			notifyDataSetChanged();
		}
	}
}