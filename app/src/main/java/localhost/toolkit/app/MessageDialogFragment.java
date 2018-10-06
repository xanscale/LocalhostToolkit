package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Html;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MessageDialogFragment extends DialogFragment {
	private static final String KEY_TIT = "KEY_TIT";
	private static final String KEY_MSG = "KEY_MSG";
	private static final String KEY_EXIT = "KEY_EXIT";

	public static MessageDialogFragment newInstance(String title, String result, boolean exit) {
		Bundle args = new Bundle();
		args.putString(KEY_TIT, title);
		args.putString(KEY_MSG, result);
		args.putBoolean(KEY_EXIT, exit);
		MessageDialogFragment fragment = new MessageDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(getArguments().getString(KEY_TIT));
		builder.setMessage(Html.fromHtml(getArguments().getString(KEY_MSG)));
		builder.setPositiveButton(android.R.string.ok, getArguments().getBoolean(KEY_EXIT) ? new ExitOnClickListener() : null);
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	private final class ExitOnClickListener implements OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			getActivity().finish();
		}
	}
}