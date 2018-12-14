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
	public static final String VALUE = "value";
	public static final String INPUT_TYPE = "inputType";
	private static final String TITLE = "title";
	private static final String EXTRA = "extra";
	private EditText editText;

	public static EditTextDialogFragment newInstance(Serializable extra, int title, String value, int inputType) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA, extra);
		args.putInt(TITLE, title);
		args.putString(VALUE, value);
		args.putInt(INPUT_TYPE, inputType);
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
					getOnEditTextListener().onEditTextDialogResult(getArguments().getSerializable(EXTRA), editText.getText().toString());
			}
		});
		builder.setNegativeButton(android.R.string.cancel, null);
		View v = View.inflate(getActivity(), R.layout.edittext, null);
		editText = v.findViewById(R.id.editText);
		editText.setText(getArguments().getString(VALUE));
		editText.setInputType(getArguments().getInt(INPUT_TYPE));
		builder.setView(v);
		builder.setTitle(getArguments().getInt(TITLE));
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