package localhost.toolkit.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

public class HeterogeneousArrayAdapter extends ArrayAdapter<HeterogeneousItem> {
	private HashMap<Class, Integer> types;
	private LayoutInflater inflater;

	public HeterogeneousArrayAdapter(Context context, List<HeterogeneousItem> objects) {
		super(context, 0, objects);
		inflater = LayoutInflater.from(context);
		types = new HashMap<>();
		int typeCount = 0;
		for (HeterogeneousItem item : objects)
			if (!types.containsKey(item.getClass()))
				types.put(item.getClass(), typeCount++);
	}

	public static OnItemClickListener getOnItemClickListener() {
		return new OnItemClickListener() {
			@Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((HeterogeneousArrayAdapter) parent.getAdapter()).getItem(position).onItemClick(view, position);
			}
		};
	}

	public static OnItemLongClickListener getOnItemLongClickListener() {
		return new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				return ((HeterogeneousArrayAdapter) parent.getAdapter()).getItem(position).onItemLongClick(position);
			}
		};
	}

	@NonNull @Override public View getView(int position, View convertView, @NonNull ViewGroup parent) {
		HeterogeneousItem item = getItem(position);
		if (item == null)
			throw new IllegalStateException();
		else {
			View v = convertView == null ? item.onCreateView(inflater, parent) : convertView;
			item.onResume(v);
			return v;
		}
	}

	@Override public int getItemViewType(int position) {
		return types.isEmpty() ? super.getItemViewType(position) : types.get(getItem(position).getClass());
	}

	@Override public int getViewTypeCount() {
		return types.isEmpty() ? super.getViewTypeCount() : types.size();
	}
}
