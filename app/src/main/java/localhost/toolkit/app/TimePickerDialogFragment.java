package localhost.toolkit.app;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	private static final String DATE = "DATE";

	public static TimePickerDialogFragment newInstance(long date) {
		Bundle args = new Bundle();
		args.putLong(DATE, date);
		TimePickerDialogFragment fragment = new TimePickerDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		assert getActivity() != null;
		assert getArguments() != null;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getArguments().getLong(DATE));
		return new TimePickerDialog(getActivity(), this, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		assert getActivity() != null;
		if (getParentFragment() != null)
			((OnTimeSetListener) getParentFragment()).onTimeSet(getTag(), hourOfDay, minute);
		else
			((OnTimeSetListener) getActivity()).onTimeSet(getTag(), hourOfDay, minute);
	}

	public interface OnTimeSetListener {
		void onTimeSet(String tag, int hourOfDay, int minute);
	}
}