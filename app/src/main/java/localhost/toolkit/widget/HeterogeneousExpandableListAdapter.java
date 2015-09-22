package localhost.toolkit.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import java.util.ArrayList;

public class HeterogeneousExpandableListAdapter extends BaseExpandableListAdapter {
	private ArrayList<HeterogeneousItem> groupItems;
	private ArrayList<ArrayList<HeterogeneousItem>> childItems;
	private ArrayList<Integer> groupTypes, childTypes;
	private LayoutInflater inflater;

	public HeterogeneousExpandableListAdapter(Context context, ArrayList<HeterogeneousItem> groupItems, ArrayList<ArrayList<HeterogeneousItem>> childItems) {
		this.groupItems = groupItems;
		this.childItems = childItems;
		groupTypes = new ArrayList<>();
		childTypes = new ArrayList<>();
		inflater = LayoutInflater.from(context);
		for (HeterogeneousItem abstractItem : groupItems)
			groupTypes.add(abstractItem.getClass().hashCode());
		for (ArrayList<HeterogeneousItem> childData : childItems)
			for (HeterogeneousItem abstractItem : childData)
				childTypes.add(abstractItem.getClass().hashCode());
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
		return childTypes.indexOf(getChild(groupPosition, childPosition).getClass().hashCode());
	}

	@Override
	public int getChildTypeCount() {
		return childTypes.size() + 1;
	}

	@Override
	public int getGroupType(int groupPosition) {
		return groupTypes.indexOf(getGroup(groupPosition).getClass().hashCode());
	}

	@Override
	public int getGroupTypeCount() {
		return groupTypes.size() + 1;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		HeterogeneousItem rowModel = groupItems.get(groupPosition);
		if (convertView == null)
			convertView = rowModel.onCreateView(inflater, parent);
		rowModel.onResume(convertView);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		HeterogeneousItem rowModel = childItems.get(groupPosition).get(childPosition);
		if (convertView == null)
			convertView = rowModel.onCreateView(inflater, parent);
		rowModel.onResume(convertView);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
