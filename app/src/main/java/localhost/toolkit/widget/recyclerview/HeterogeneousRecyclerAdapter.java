package localhost.toolkit.widget.recyclerview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class HeterogeneousRecyclerAdapter<I extends HeterogeneousRecyclerItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private Filter filter;
    private final HashMap<Class<?>, Integer> classToType;
    private final SparseIntArray typeToPos;
    private final LayoutInflater inflater;
    private List<I> items;
    private boolean loopScroll;

    public HeterogeneousRecyclerAdapter(Context context, List<I> items) {
        setHasStableIds(true);
        this.items = items;
        inflater = LayoutInflater.from(context);
        classToType = new HashMap<>();
        typeToPos = new SparseIntArray();
        notifyTypesChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItem(typeToPos.get(viewType)).onCreateViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        getItem(position).onBindViewHolder(viewHolder);
    }

    public I getItem(int position) {
        return items.get(loopScroll && !items.isEmpty() ? position % items.size() : position);
    }

    @Override
    public int getItemCount() {
        return loopScroll && !items.isEmpty() ? Integer.MAX_VALUE : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Integer type = classToType.get(getItem(position).getClass());
        return type == null ? super.getItemViewType(position) : type;
    }

    @Override
    public long getItemId(int position) {
        return System.identityHashCode(getItem(position));
    }

    public void notifyTypesChanged() {
        typeToPos.clear();
        for (int i = 0; i < items.size(); i++) {
            if (!classToType.containsKey(items.get(i).getClass()))
                classToType.put(items.get(i).getClass(), classToType.size());
            Integer type = classToType.get(items.get(i).getClass());
            if (type != null && typeToPos.indexOfKey(type) < 0)
                typeToPos.put(type, i);
        }
        notifyDataSetChanged();
    }

    public void setItems(List<I> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        if (filter == null)
            filter = new HeterogeneousFilter();
        return filter;
    }

    public void setLoopScroll(boolean loopScroll) {
        this.loopScroll = loopScroll;
    }

    private class HeterogeneousFilter extends Filter {
        private final List<I> originalItems = new ArrayList<>(items);

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = new ArrayList<>(originalItems);
                results.count = originalItems.size();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("^\\X*");
                for (String word : constraint.toString().toLowerCase().split("\\W"))
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

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            setItems((List<I>) results.values);
        }
    }
}