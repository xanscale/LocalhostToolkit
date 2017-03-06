package localhost.toolkit.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	private static final String DATE = "DATE";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getArguments().getLong(DATE));
		return new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
	}

	public void show(FragmentManager manager, String tag, long date) {
		Bundle b = new Bundle(1);
		b.putLong(DATE, date);
		setArguments(b);
		super.show(manager, tag);
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