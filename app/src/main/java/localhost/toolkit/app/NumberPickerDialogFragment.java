package localhost.toolkit.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.NumberPicker;

import localhost.toolkit.R;

public class NumberPickerDialogFragment extends DialogFragment {
	private static final String KEY_TITLE = "KEY_TITLE";
	private static final String KEY_MIN = "KEY_MIN";
	private static final String KEY_MAX = "KEY_MAX";
	private OnNumberPickerClickListener onNumberPickerClickListener;
	private NumberPicker np;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		np = (NumberPicker) View.inflate(getActivity(), R.layout.number_picker, null);
		np.setMinValue(getArguments().getInt(KEY_MIN));
		np.setMaxValue(getArguments().getInt(KEY_MAX));
		builder.setTitle(getArguments().getInt(KEY_TITLE));
		builder.setView(np);
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				onNumberPickerClickListener.onClick(np.getValue());
			}
		});
		return builder.create();
	}

	public void show(FragmentManager fragmentManager, int title, int min, int max, OnNumberPickerClickListener onNumberPickerClickListener) {
		Bundle arguments = new Bundle(3);
		arguments.putInt(KEY_TITLE, title);
		arguments.putInt(KEY_MIN, min);
		arguments.putInt(KEY_MAX, max);
		this.onNumberPickerClickListener = onNumberPickerClickListener;
		setArguments(arguments);
		show(fragmentManager, NumberPickerDialogFragment.class.getSimpleName());
	}

	public interface OnNumberPickerClickListener {
		public void onClick(int value);
	}
}