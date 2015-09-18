package localhost.toolkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeterogeneousArrayAdapter extends ArrayAdapter<HeterogeneousItem> {
	private ArrayList<Integer> types;
	private LayoutInflater inflater;

	public HeterogeneousArrayAdapter(Context context, List<HeterogeneousItem> objects) {
		super(context, 0, objects);
		inflater = LayoutInflater.from(context);
		types = new ArrayList<Integer>(objects.size());
		for (HeterogeneousItem item : objects)
			types.add(item.getClass().hashCode());
	}

	public static OnItemClickListener getOnItemClickListener() {
		return new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HeterogeneousItem item = getItem(position);
		if (convertView == null)
			convertView = item.onCreateView(inflater, parent);
		item.onResume(convertView);
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		return types.indexOf(getItem(position).getClass().hashCode());
	}

	@Override
	public int getViewTypeCount() {
		return types.size() == 0 ? 1 : types.size();
	}
}
