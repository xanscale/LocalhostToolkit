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
	private DialogInterface.OnClickListener onClickListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		if (getArguments().getStringArray(KEY_LIST_STRGS) != null)
			builder.setItems(getArguments().getStringArray(KEY_LIST_STRGS), onClickListener);
		else
			builder.setItems(getArguments().getInt(KEY_LIST_RESID), onClickListener);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, int title, int list, DialogInterface.OnClickListener onClickListener) {
		Bundle arguments = new Bundle(1);
		arguments.putInt(KEY_TITLE, title);
		arguments.putInt(KEY_LIST_RESID, list);
		this.onClickListener = onClickListener;
		setArguments(arguments);
		show(fragmentManager, ListDialogFragment.class.getSimpleName());
	}

	public void show(FragmentManager fragmentManager, int title, String[] list, DialogInterface.OnClickListener onClickListener) {
		Bundle arguments = new Bundle(1);
		arguments.putInt(KEY_TITLE, title);
		arguments.putStringArray(KEY_LIST_STRGS, list);
		this.onClickListener = onClickListener;
		setArguments(arguments);
		show(fragmentManager, ListDialogFragment.class.getSimpleName());
	}
}