package localhost.toolkit.widget.recyclerview;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class HeterogeneousListAdapter extends ListAdapter<HeterogeneousRecyclerItem<?,? extends RecyclerView.ViewHolder>, RecyclerView.ViewHolder> implements Filterable {
    private Filter filter;
    private final HashMap<Class<?>, Integer> classToType;
    private final SparseIntArray typeToPos;

    public HeterogeneousListAdapter() {
        super(new DiffUtilItemCallback<>());
        setHasStableIds(true);
        classToType = new HashMap<>();
        typeToPos = new SparseIntArray();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return getItem(typeToPos.get(viewType)).onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        getItem(position).internalOnBindViewHolder(viewHolder);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        int pos = holder.getBindingAdapterPosition();
        if (pos != RecyclerView.NO_POSITION)
            getItem(pos).internalOnViewRecycled(holder);
    }

    @Override
    public int getItemViewType(int position) {
        Integer type = classToType.get(getItem(position).getClass());
        return type == null ? super.getItemViewType(position) : type;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public void submitList(@Nullable List<HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder>> list) {
        super.submitList(list, () -> {
            filter = null;
            updateTypes();
        });
    }

    @Override
    public void submitList(@Nullable List<HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder>> list, @Nullable Runnable commitCallback) {
        super.submitList(list, () -> {
            filter = null;
            updateTypes();
            if (commitCallback != null)
                commitCallback.run();
        });
    }

    private void applyFilter(@Nullable List<HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder>> list) {
        super.submitList(list, this::updateTypes);
    }

    private void updateTypes() {
        typeToPos.clear();
        for (int i = 0; i < getItemCount(); i++) {
            if (!classToType.containsKey(getItem(i).getClass()))
                classToType.put(getItem(i).getClass(), classToType.size());
            Integer type = classToType.get(getItem(i).getClass());
            if (type != null && typeToPos.indexOfKey(type) < 0)
                typeToPos.put(type, i);
        }
    }

    public Filter getFilter() {
        if (filter == null)
            filter = new HeterogeneousFilter();
        return filter;
    }

    private class HeterogeneousFilter extends Filter {
        private final List<HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder>> originalItems = new ArrayList<>(getCurrentList());

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
                ArrayList<HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder>> newValues = new ArrayList<>();
                Pattern pattern = Pattern.compile(sb.toString());
                for (HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder> value : originalItems)
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
            applyFilter((List<HeterogeneousRecyclerItem<?, ? extends RecyclerView.ViewHolder>>) results.values);
        }
    }

    private static class DiffUtilItemCallback<I extends HeterogeneousRecyclerItem<?, ?>> extends DiffUtil.ItemCallback<I> {
        @Override
        public boolean areItemsTheSame(@NonNull I oldItem, @NonNull I newItem) {
            return oldItem.hashCode() == newItem.hashCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull I oldItem, @NonNull I newItem) {
            return oldItem.equals(newItem);
        }
    }
}