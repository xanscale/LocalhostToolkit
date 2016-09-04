package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.Serializable;


public class MultiChoiceDialog extends DialogFragment implements DialogInterface.OnMultiChoiceClickListener,DialogInterface.OnClickListener {
	public static final String KEY_CHECKED_ITEMS = "KEY_CHECKED_ITEMS";
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_EXTRA = "KEY_EXTRA";
	private static final String KEY_LIST_RESID = "KEY_LIST_RESID";
	private static final String KEY_LIST_STRGS = "KEY_LIST_STRGS";
	private boolean[] checkedItems;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		checkedItems = getArguments().getBooleanArray(KEY_CHECKED_ITEMS);
		if (getArguments().getStringArray(KEY_LIST_STRGS) != null)
			builder.setMultiChoiceItems(getArguments().getStringArray(KEY_LIST_STRGS), checkedItems, this);
		else
			builder.setMultiChoiceItems(getArguments().getInt(KEY_LIST_RESID), checkedItems, this);
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.setPositiveButton(android.R.string.ok, this);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, Serializable extra, int title, int list, boolean[] checkedItems) {
		Bundle b = new Bundle(3);
		b.putSerializable(KEY_EXTRA, extra);
		b.putInt(KEY_TITLE, title);
		b.putInt(KEY_LIST_RESID, list);
		b.putBooleanArray(KEY_CHECKED_ITEMS, checkedItems);
		setArguments(b);
		show(fragmentManager, MultiChoiceDialog.class.getSimpleName());
	}

	public void show(FragmentManager fragmentManager, Serializable extra, int title, String[] list, boolean[] checkedItems) {
		Bundle b = new Bundle(3);
		b.putSerializable(KEY_EXTRA, extra);
		b.putInt(KEY_TITLE, title);
		b.putStringArray(KEY_LIST_STRGS, list);
		b.putBooleanArray(KEY_CHECKED_ITEMS, checkedItems);
		setArguments(b);
		show(fragmentManager, MultiChoiceDialog.class.getSimpleName());
	}

	public void onClick(DialogInterface dialog, int which) {
		OnMultiChoiceDialogClickListener l = (OnMultiChoiceDialogClickListener) getParentFragment();
		if (l == null)
			l = (OnMultiChoiceDialogClickListener) getActivity();
		l.onClick(getArguments().getSerializable(KEY_EXTRA), checkedItems);
	}

	@Override
	public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		checkedItems[which] = isChecked;
	}

	public interface OnMultiChoiceDialogClickListener {
		void onClick(Serializable extra, boolean[] checkedItems);
	}
}