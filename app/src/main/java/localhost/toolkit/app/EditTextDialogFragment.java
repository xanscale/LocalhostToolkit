package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import localhost.toolkit.R;

public class EditTextDialogFragment extends DialogFragment {
	public static final String KEY_VALUE = "KEY_VALUE";
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_EXTRA = "KEY_EXTRA";
	private EditText editText;

	public static EditTextDialogFragment newInstance(Serializable extra, int title, String value) {
		Bundle args = new Bundle();
		args.putSerializable(KEY_EXTRA, extra);
		args.putInt(KEY_TITLE, title);
		args.putString(KEY_VALUE, value);
		EditTextDialogFragment fragment = new EditTextDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override public void onClick(DialogInterface dialogInterface, int i) {
				if (editText.length() != 0)
					getOnEditTextListener().onEditTextDialogResult(getArguments().getSerializable(KEY_EXTRA), editText.getText().toString());
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

	private OnEditTextListener getOnEditTextListener() {
		OnEditTextListener l = (OnEditTextListener) getParentFragment();
		if (l == null)
			l = (OnEditTextListener) getActivity();
		return l;
	}

	public interface OnEditTextListener {
		void onEditTextDialogResult(Serializable extra, String value);
	}
}