package localhost.toolkit.app;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static final String TIME_IN_MILLIS = "TIME_IN_MILLIS";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        Calendar c = Calendar.getInstance();
        if (getArguments().containsKey(TIME_IN_MILLIS))
            c.setTimeInMillis(getArguments().getLong(TIME_IN_MILLIS));
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

    public static class Builder {
        private Date date;

        public TimePickerDialogFragment build() {
            TimePickerDialogFragment fragment = new TimePickerDialogFragment();
            Bundle args = new Bundle();
            if (date != null) args.putLong(TIME_IN_MILLIS, date.getTime());
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }
    }
}