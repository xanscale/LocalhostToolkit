package localhost.toolkit.widget.recyclerview;

import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

public class HeterogeneousSpanSizeLookup<I extends HeterogeneousRecyclerItem<?, ?>> extends GridLayoutManager.SpanSizeLookup {
    private final List<I> items;

    public HeterogeneousSpanSizeLookup(List<I> items) {
        this.items = items;
    }

    @Override
    public int getSpanSize(int position) {
        return position < items.size() ? items.get(position).getSpanSize() : 1;
    }
}