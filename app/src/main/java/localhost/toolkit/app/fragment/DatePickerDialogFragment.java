package localhost.toolkit.app.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * @deprecated use {@link com.google.android.material.datepicker.MaterialDatePicker} instead
 */
@Deprecated
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String DATE = "DATE";
    private static final String MIN_DATE = "MIN_DATE";
    private static final String MAX_DATE = "MAX_DATE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        Calendar c = Calendar.getInstance();
        if (getArguments().containsKey(DATE))
            c.setTimeInMillis(getArguments().getLong(DATE));
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        if (getArguments().containsKey(MIN_DATE))
            datePickerDialog.getDatePicker().setMinDate(getArguments().getLong(MIN_DATE));
        if (getArguments().containsKey(MAX_DATE))
            datePickerDialog.getDatePicker().setMaxDate(getArguments().getLong(MAX_DATE));
        return datePickerDialog;
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

    public static class Builder {
        private Date date;
        private Date minDate;
        private Date maxDate;

        public DatePickerDialogFragment build() {
            DatePickerDialogFragment fragment = new DatePickerDialogFragment();
            Bundle args = new Bundle();
            if (date != null) args.putLong(DATE, date.getTime());
            if (minDate != null) args.putLong(MIN_DATE, minDate.getTime());
            if (maxDate != null) args.putLong(MAX_DATE, maxDate.getTime());
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withMinDate(Date minDate) {
            this.minDate = minDate;
            return this;
        }

        public Builder withMaxDate(Date maxDate) {
            this.maxDate = maxDate;
            return this;
        }
    }
}