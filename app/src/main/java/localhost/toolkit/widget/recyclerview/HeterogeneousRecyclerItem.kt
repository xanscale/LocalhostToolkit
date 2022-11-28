package localhost.toolkit.widget.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class HeterogeneousRecyclerItem<E, H extends RecyclerView.ViewHolder> {
    protected E extra;

    public HeterogeneousRecyclerItem(E extra) {
        this.extra = extra;
    }

    public abstract H onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    void internalOnBindViewHolder(@NonNull RecyclerView.ViewHolder holder) {
        onBindViewHolder((H) holder);
    }

    void internalOnViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        onViewRecycled((H) holder);
    }

    public abstract void onBindViewHolder(@NonNull H holder);

    public void onViewRecycled(@NonNull H holder) {
    }

    public E getExtra() {
        return extra;
    }

    public void setExtra(E extra) {
        this.extra = extra;
    }

    public int getSpanSize() {
        return 1;
    }

    @NonNull
    @Override
    public String toString() {
        return extra.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeterogeneousRecyclerItem)) return false;
        HeterogeneousRecyclerItem<?, ?> that = (HeterogeneousRecyclerItem<?, ?>) o;
        return extra.equals(that.extra);
    }

    @Override
    public int hashCode() {
        return extra.hashCode();
    }
}
