package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class ListDialogFragment extends DialogFragment {
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_LIST_RESID = "KEY_LIST_RESID";
	private static final String KEY_LIST_STRGS = "KEY_LIST_STRGS";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		if (getArguments().getStringArray(KEY_LIST_STRGS) != null)
			builder.setItems(getArguments().getStringArray(KEY_LIST_STRGS), getOnClickListener());
		else
			builder.setItems(getArguments().getInt(KEY_LIST_RESID), getOnClickListener());
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, int title, int list) {
		Bundle arguments = new Bundle(1);
		arguments.putInt(KEY_TITLE, title);
		arguments.putInt(KEY_LIST_RESID, list);
		setArguments(arguments);
		show(fragmentManager, ListDialogFragment.class.getSimpleName());
	}

	public void show(FragmentManager fragmentManager, int title, String[] list) {
		Bundle arguments = new Bundle(1);
		arguments.putInt(KEY_TITLE, title);
		arguments.putStringArray(KEY_LIST_STRGS, list);
		setArguments(arguments);
		show(fragmentManager, ListDialogFragment.class.getSimpleName());
	}

	private DialogInterface.OnClickListener getOnClickListener() {
		DialogInterface.OnClickListener l = (DialogInterface.OnClickListener) getParentFragment();
		if (l == null)
			l = (DialogInterface.OnClickListener) getActivity();
		return l;
	}
}