package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import java.io.Serializable;

public class ConfirmDialogFragment extends DialogFragment {
	private static final String KEY_MSGID = "messageId";
	private static final String KEY_TITLEID = "titleId";
	private static final String KEY_MSG = "message";
	private static final String KEY_TITLE = "title";
	private static final String KEY_EXTRA = "extra";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		int titleId = getArguments().getInt(KEY_TITLEID);
		int msgId = getArguments().getInt(KEY_MSGID);
		String title = getArguments().getString(KEY_TITLE);
		String msg = getArguments().getString(KEY_MSG);
		if (titleId != 0)
			builder.setTitle(titleId);
		else
			builder.setTitle(title);
		if (msgId != 0)
			builder.setMessage(msgId);
		else
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

	public void show(FragmentManager fragmentManager, Serializable extra, int titleId, int msgId) {
		Bundle arguments = new Bundle(3);
		arguments.putInt(KEY_TITLEID, titleId);
		arguments.putInt(KEY_MSGID, msgId);
		arguments.putSerializable(KEY_EXTRA, extra);
		setArguments(arguments);
		try {
			show(fragmentManager, ConfirmDialogFragment.class.getSimpleName());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	public void show(FragmentManager fragmentManager, Serializable extra, String title, String msg) {
		Bundle arguments = new Bundle(3);
		arguments.putString(KEY_TITLE, title);
		arguments.putString(KEY_MSG, msg);
		arguments.putSerializable(KEY_EXTRA, extra);
		setArguments(arguments);
		try {
			show(fragmentManager, ConfirmDialogFragment.class.getSimpleName());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	private OnConfirmedListener getOnConfirmedListener() {
		OnConfirmedListener l = (OnConfirmedListener) getParentFragment();
		if (l == null)
			l = (OnConfirmedListener) getActivity();
		return l;
	}

	public interface OnConfirmedListener {
		public boolean onConfirmation(Serializable extra, boolean confirmed);
	}
}