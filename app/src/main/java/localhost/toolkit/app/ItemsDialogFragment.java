package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.Serializable;

public class ItemsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_EXTRA = "KEY_EXTRA";
	private static final String KEY_LIST_RESID = "KEY_LIST_RESID";
	private static final String KEY_LIST_STRGS = "KEY_LIST_STRGS";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		if (getArguments().getStringArray(KEY_LIST_STRGS) != null)
			builder.setItems(getArguments().getStringArray(KEY_LIST_STRGS), this);
		else
			builder.setItems(getArguments().getInt(KEY_LIST_RESID), this);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, Serializable extra, int title, int list) {
		Bundle b = new Bundle(3);
		b.putSerializable(KEY_EXTRA, extra);
		b.putInt(KEY_TITLE, title);
		b.putInt(KEY_LIST_RESID, list);
		setArguments(b);
		show(fragmentManager, ItemsDialogFragment.class.getSimpleName());
	}

	public void show(FragmentManager fragmentManager, Serializable extra, int title, String[] list) {
		Bundle b = new Bundle(3);
		b.putSerializable(KEY_EXTRA, extra);
		b.putInt(KEY_TITLE, title);
		b.putStringArray(KEY_LIST_STRGS, list);
		setArguments(b);
		show(fragmentManager, ItemsDialogFragment.class.getSimpleName());
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		OnListDialogClickListener l = (OnListDialogClickListener) getParentFragment();
		if (l == null)
			l = (OnListDialogClickListener) getActivity();
		l.onClick(getArguments().getSerializable(KEY_EXTRA), which);
	}

	public interface OnListDialogClickListener {
		void onClick(Serializable extra, int which);
	}
}