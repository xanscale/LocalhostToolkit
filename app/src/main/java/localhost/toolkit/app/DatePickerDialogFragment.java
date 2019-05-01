package localhost.toolkit.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TIME_IN_MILLIS = "TIME_IN_MILLIS";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        Calendar c = Calendar.getInstance();
        if (getArguments().containsKey(TIME_IN_MILLIS))
            c.setTimeInMillis(getArguments().getLong(TIME_IN_MILLIS));
        return new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        assert getActivity() != null;
        if (getParentFragment() != null)
            ((OnDateSetListener) getParentFragment()).onDateSet(getTag(), year, monthOfYear, dayOfMonth);
        else
            ((OnDateSetListener) getActivity()).onDateSet(getTag(), year, monthOfYear, dayOfMonth);
    }

    public interface OnDateSetListener {
        void onDateSet(String tag, int year, int monthOfYear, int dayOfMonth);
    }

    public class Builder {
        private Date date;

        public DatePickerDialogFragment build() {
            DatePickerDialogFragment fragment = new DatePickerDialogFragment();
            Bundle args = new Bundle();
            if (date != null)
                args.putLong(TIME_IN_MILLIS, date.getTime());
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }
    }
}