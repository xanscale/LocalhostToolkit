package localhost.toolkit.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	private static final String DATE = "DATE";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getArguments().getLong(DATE));
		return new TimePickerDialog(getActivity(), this, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), DateFormat.is24HourFormat(getActivity()));
	}

	public void show(FragmentManager manager, String tag, long date) {
		Bundle b = new Bundle(1);
		b.putLong(DATE, date);
		setArguments(b);
		super.show(manager, tag);
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		if (getParentFragment() != null)
			((OnTimeSetListener) getParentFragment()).onTimeSet(getTag(), hourOfDay, minute);
		else
			((OnTimeSetListener) getActivity()).onTimeSet(getTag(), hourOfDay, minute);
	}

	interface OnTimeSetListener {
		void onTimeSet(String tag, int hourOfDay, int minute);
	}
}