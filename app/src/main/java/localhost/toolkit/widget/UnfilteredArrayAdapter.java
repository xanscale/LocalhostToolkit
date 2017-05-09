package localhost.toolkit.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.List;

public class UnfilteredArrayAdapter<T> extends ArrayAdapter<T> {
	public UnfilteredArrayAdapter(@NonNull Context context, @LayoutRes int resource) {
		super(context, resource);
	}

	public UnfilteredArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
		super(context, resource, textViewResourceId);
	}

	public UnfilteredArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull T[] objects) {
		super(context, resource, objects);
	}

	public UnfilteredArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull T[] objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public UnfilteredArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects) {
		super(context, resource, objects);
	}

	public UnfilteredArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<T> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	@NonNull @Override public Filter getFilter() {
		return new Filter() {
			@Override protected FilterResults performFiltering(CharSequence constraint) {
				return new FilterResults();
			}

			@Override protected void publishResults(CharSequence constraint, FilterResults results) {
				notifyDataSetChanged();
			}
		};
	}
}
