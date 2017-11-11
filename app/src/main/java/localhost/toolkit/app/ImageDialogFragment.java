package localhost.toolkit.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageDialogFragment extends DialogFragment {
	private static final String KEY_ICON = "KEY_ICON";
	private static final String KEY_IMG = "KEY_IMG";
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_EXIT = "KEY_EXIT";

	public static ImageDialogFragment newInstance(int icon, int title, int image, boolean exit) {
		Bundle args = new Bundle();
		args.putInt(KEY_ICON, icon);
		args.putInt(KEY_TITLE, title);
		args.putInt(KEY_IMG, image);
		args.putBoolean(KEY_EXIT, exit);
		ImageDialogFragment fragment = new ImageDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setPositiveButton(android.R.string.ok, getArguments().getBoolean(KEY_EXIT) ? new ExitOnClick() : null);
		ImageView image = new ImageView(getActivity());
		image.setImageResource(getArguments().getInt(KEY_IMG));
		builder.setView(image);
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		builder.setIcon(getArguments().getInt(KEY_ICON));
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	private final class ExitOnClick implements OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			getActivity().setResult(Activity.RESULT_CANCELED);
			getActivity().finish();
		}
	}
}