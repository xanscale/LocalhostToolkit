package localhost.toolkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HeterogeneousExpandableListAdapter extends BaseExpandableListAdapter {
	private ArrayList<HeterogeneousItem> groupItems;
	private ArrayList<ArrayList<HeterogeneousItem>> childItems;
	private HashMap<Class, Integer> groupTypes, childTypes;
	private LayoutInflater inflater;

	public HeterogeneousExpandableListAdapter(Context context, ArrayList<HeterogeneousItem> groupItems, ArrayList<ArrayList<HeterogeneousItem>> childItems) {
		this.groupItems = groupItems;
		this.childItems = childItems;
		inflater = LayoutInflater.from(context);
		groupTypes = new HashMap<>();
		childTypes = new HashMap<>();
		int groupTypeCount = 0;
		int childTypeCount = 0;
		for (HeterogeneousItem item : groupItems)
			if (!groupTypes.containsKey(item.getClass()))
				groupTypes.put(item.getClass(), groupTypeCount++);
		for (ArrayList<HeterogeneousItem> items : childItems)
			for (HeterogeneousItem item : items)
				if (!childTypes.containsKey(item.getClass()))
					childTypes.put(item.getClass(), childTypeCount++);
	}

	public static OnChildClickListener getOnChildClickListener() {
		return new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				return ((HeterogeneousItem) parent.getExpandableListAdapter().getChild(groupPosition, childPosition)).onItemClick(v, 0);
			}
		};
	}

	@Override
	public int getGroupCount() {
		return groupItems.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childItems.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupItems.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return childItems.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition * childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getChildType(int groupPosition, int childPosition) {
		return childTypes.isEmpty() ? super.getChildType(groupPosition, childPosition) : childTypes.get(getChild(groupPosition, childPosition).getClass());
	}

	@Override
	public int getChildTypeCount() {
		return childTypes.isEmpty() ? super.getChildTypeCount() : childTypes.size();
	}

	@Override
	public int getGroupType(int groupPosition) {
		return groupTypes.isEmpty() ? super.getGroupType(groupPosition) : groupTypes.get(getGroup(groupPosition).getClass());
	}

	@Override
	public int getGroupTypeCount() {
		return groupTypes.isEmpty() ? super.getGroupTypeCount() : groupTypes.size();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		HeterogeneousItem rowModel = groupItems.get(groupPosition);
		View v = convertView == null ? rowModel.onCreateView(inflater, parent) : convertView;
		rowModel.onResume(v);
		return v;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		HeterogeneousItem rowModel = childItems.get(groupPosition).get(childPosition);
		View v = convertView == null ? rowModel.onCreateView(inflater, parent) : convertView;
		rowModel.onResume(v);
		return v;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
