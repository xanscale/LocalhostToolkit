package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class MessageDialogFragment extends DialogFragment {
	private static final String KEY_TIT = "KEY_TIT";
	private static final String KEY_MSG = "KEY_MSG";
	private static final String KEY_EXIT = "KEY_EXIT";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getString(KEY_TIT));
		builder.setMessage(getArguments().getString(KEY_MSG));
		builder.setPositiveButton(android.R.string.ok, getArguments().getBoolean(KEY_EXIT) ? new ExitOnClickListener() : null);
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, String title, String result, boolean exit) {
		Bundle arguments = new Bundle(3);
		arguments.putString(KEY_TIT, title);
		arguments.putString(KEY_MSG, result);
		arguments.putBoolean(KEY_EXIT, exit);
		setArguments(arguments);
		try {
			show(fragmentManager, MessageDialogFragment.class.getSimpleName());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	private final class ExitOnClickListener implements OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			getActivity().finish();
		}
	}
}