package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import localhost.toolkit.R;

public class EditTextDialogFragment extends DialogFragment {
	public static final String KEY_VALUE = "KEY_VALUE";
	private static final String KEY_TITLE = "KEY_TITLE";
	private EditText editText;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override public void onClick(DialogInterface dialogInterface, int i) {
				if (editText.length() != 0)
					getOnEditTextListener().onEditTextDialogResult(editText.getText().toString());
			}
		});
		builder.setNegativeButton(android.R.string.cancel, null);
		View v = View.inflate(getActivity(), R.layout.edittext, null);
		editText = v.findViewById(R.id.editText);
		editText.setText(getArguments().getString(KEY_VALUE));
		builder.setView(v);
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, int title, String value) {
		Bundle arguments = new Bundle(4);
		arguments.putInt(KEY_TITLE, title);
		arguments.putString(KEY_VALUE, value);
		setArguments(arguments);
		show(fragmentManager, getClass().getSimpleName());
	}

	private OnEditTextListener getOnEditTextListener() {
		OnEditTextListener l = (OnEditTextListener) getParentFragment();
		if (l == null)
			l = (OnEditTextListener) getActivity();
		return l;
	}

	public interface OnEditTextListener {
		boolean onEditTextDialogResult(String value);
	}
}