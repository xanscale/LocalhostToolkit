package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment implements OnClickListener {
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

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		assert getActivity() != null;
		assert getArguments() != null;
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setPositiveButton(android.R.string.ok, getArguments().getBoolean(KEY_EXIT) ? this : null);
		ImageView image = new ImageView(getActivity());
		image.setImageResource(getArguments().getInt(KEY_IMG));
		builder.setView(image);
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		builder.setIcon(getArguments().getInt(KEY_ICON));
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	@Override public void onClick(DialogInterface dialog, int which) {
		assert getActivity() != null;
		getActivity().onBackPressed();
	}
}