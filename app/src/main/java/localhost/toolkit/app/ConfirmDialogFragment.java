package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import java.io.Serializable;

public class ConfirmDialogFragment extends DialogFragment {
	private static final String KEY_MSG = "message";
	private static final String KEY_TITLE = "title";
	private static final String KEY_EXTRA = "extra";

	public static ConfirmDialogFragment newInstance(Serializable extra, String title, String msg) {
		Bundle args = new Bundle();
		args.putString(KEY_TITLE, title);
		args.putString(KEY_MSG, msg);
		args.putSerializable(KEY_EXTRA, extra);
		ConfirmDialogFragment fragment = new ConfirmDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		String title = getArguments().getString(KEY_TITLE);
		String msg = getArguments().getString(KEY_MSG);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), true);
			}
		});
		builder.setNegativeButton(android.R.string.cancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), false);
			}
		});
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	private OnConfirmedListener getOnConfirmedListener() {
		OnConfirmedListener l = (OnConfirmedListener) getParentFragment();
		if (l == null)
			l = (OnConfirmedListener) getActivity();
		return l;
	}

	public interface OnConfirmedListener {
		void onConfirmation(Serializable extra, boolean confirmed);
	}
}