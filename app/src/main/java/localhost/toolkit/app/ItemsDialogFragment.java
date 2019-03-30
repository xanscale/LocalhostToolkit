package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ItemsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_EXTRA = "KEY_EXTRA";
	private static final String KEY_LIST_RESID = "KEY_LIST_RESID";
	private static final String KEY_LIST_STRGS = "KEY_LIST_STRGS";

	public static ItemsDialogFragment newInstance(Serializable extra, int title, int list) {
		Bundle args = new Bundle();
		args.putSerializable(KEY_EXTRA, extra);
		args.putInt(KEY_TITLE, title);
		args.putInt(KEY_LIST_RESID, list);
		ItemsDialogFragment fragment = new ItemsDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	public static ItemsDialogFragment newInstance(Serializable extra, int title, String[] list) {
		Bundle args = new Bundle();
		args.putSerializable(KEY_EXTRA, extra);
		args.putInt(KEY_TITLE, title);
		args.putStringArray(KEY_LIST_STRGS, list);
		ItemsDialogFragment fragment = new ItemsDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		assert getActivity() != null;
		assert getArguments() != null;
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		if (getArguments().getStringArray(KEY_LIST_STRGS) != null)
			builder.setItems(getArguments().getStringArray(KEY_LIST_STRGS), this);
		else
			builder.setItems(getArguments().getInt(KEY_LIST_RESID), this);
		return builder.create();
	}

	@Override public void onClick(DialogInterface dialog, int which) {
		assert getActivity() != null;
		assert getArguments() != null;
		OnListDialogClickListener l = (OnListDialogClickListener) getParentFragment();
		if (l == null)
			l = (OnListDialogClickListener) getActivity();
		l.onClick(getArguments().getSerializable(KEY_EXTRA), which);
	}

	public interface OnListDialogClickListener {
		void onClick(Serializable extra, int which);
	}
}