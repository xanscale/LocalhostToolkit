package localhost.toolkit.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	private static final String DATE = "DATE";

	public static DatePickerDialogFragment newInstance(long date) {
		Bundle args = new Bundle();
		args.putLong(DATE, date);
		DatePickerDialogFragment fragment = new DatePickerDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getArguments().getLong(DATE));
		return new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		if (getParentFragment() != null)
			((OnDateSetListener) getParentFragment()).onDateSet(getTag(), year, monthOfYear, dayOfMonth);
		else
			((OnDateSetListener) getActivity()).onDateSet(getTag(), year, monthOfYear, dayOfMonth);
	}

	public interface OnDateSetListener {
		void onDateSet(String tag, int year, int monthOfYear, int dayOfMonth);
	}
}