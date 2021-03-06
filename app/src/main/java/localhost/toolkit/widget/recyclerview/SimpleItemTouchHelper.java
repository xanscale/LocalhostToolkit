package localhost.toolkit.widget.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * SimpleItemTouchHelper.attachToRecyclerView(recyclerView);
 */
public class SimpleItemTouchHelper extends ItemTouchHelper.Callback {
    private final RecyclerView.Adapter<?> adapter;
    private final int dragFlags;
    private final int swipeFlags;

    private SimpleItemTouchHelper(RecyclerView.Adapter<?> adapter, int dragFlags, int swipeFlags) {
        this.adapter = adapter;
        this.dragFlags = dragFlags;
        this.swipeFlags = swipeFlags;
    }

    public static void attachToRecyclerView(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() == null)
            throw new IllegalStateException("LayoutManager is null !");
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager)
            new ItemTouchHelper(new SimpleItemTouchHelper(recyclerView.getAdapter(),
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.START | ItemTouchHelper.END
            )).attachToRecyclerView(recyclerView);
        else if (recyclerView.getLayoutManager() instanceof GridLayoutManager)
            new ItemTouchHelper(new SimpleItemTouchHelper(recyclerView.getAdapter(),
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,
                    0
            )).attachToRecyclerView(recyclerView);
        else
            throw new IllegalStateException(recyclerView.getLayoutManager().getClass().getSimpleName() + " is not supported");
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (adapter instanceof SimpleItemTouchHelperCallback)
            ((SimpleItemTouchHelperCallback) adapter).getItems().remove(viewHolder.getAdapterPosition());
        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if (adapter instanceof SimpleItemTouchHelperCallback) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition)
                for (int i = fromPosition; i < toPosition; i++)
                    Collections.swap(((SimpleItemTouchHelperCallback) adapter).getItems(), i, i + 1);
            else
                for (int i = fromPosition; i > toPosition; i--)
                    Collections.swap(((SimpleItemTouchHelperCallback) adapter).getItems(), i, i - 1);
        }
        adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    public interface SimpleItemTouchHelperCallback {
        List<?> getItems();
    }
}