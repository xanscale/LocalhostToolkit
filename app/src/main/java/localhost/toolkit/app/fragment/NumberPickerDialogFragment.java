package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;

public class NumberPickerDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TITLE = "TITLE";
    private static final String MIN = "MIN";
    private static final String MAX = "MAX";
    private static final String VALUE = "VALUE";
    private static final String SERIALIZABLE = "SERIALIZABLE";
    private static final String PARCELABLE = "PARCELABLE";
    private static final String DISPLAYED_VALUES = "DISPLAYED_VALUES";
    private NumberPicker numberPicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        builder.setTitle(requireArguments().getString(TITLE));
        numberPicker = new NumberPicker(requireActivity());
        if (requireArguments().containsKey(MIN))
            numberPicker.setMinValue(requireArguments().getInt(MIN));
        if (requireArguments().containsKey(MAX))
            numberPicker.setMaxValue(requireArguments().getInt(MAX));
        if (requireArguments().containsKey(VALUE))
            numberPicker.setValue(requireArguments().getInt(VALUE));
        if (requireArguments().containsKey(DISPLAYED_VALUES))
            numberPicker.setDisplayedValues(requireArguments().getStringArray(DISPLAYED_VALUES));
        builder.setView(numberPicker);
        builder.setPositiveButton(android.R.string.ok, this);
        builder.setNegativeButton(android.R.string.cancel, null);
        setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        getOnNumberSetListener().onNumberSet(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), numberPicker.getValue());
    }

    private OnNumberSetListener getOnNumberSetListener() {
        OnNumberSetListener l = (OnNumberSetListener) getParentFragment();
        if (l == null)
            l = (OnNumberSetListener) requireActivity();
        return l;
    }

    public interface OnNumberSetListener {
        void onNumberSet(Serializable serializable, Parcelable parcelable, int value);
    }

    public static class Builder {
        private Serializable serializable;
        private Parcelable parcelable;
        private String title;
        private Integer min;
        private Integer max;
        private Integer value;
        private String[] displayedValues;

        public NumberPickerDialogFragment build() {
            NumberPickerDialogFragment fragment = new NumberPickerDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (serializable != null) args.putSerializable(SERIALIZABLE, serializable);
            if (parcelable != null) args.putParcelable(PARCELABLE, parcelable);
            if (min != null) args.putInt(MIN, min);
            if (max != null) args.putInt(MAX, max);
            if (value != null) args.putInt(VALUE, value);
            if (displayedValues != null) args.putStringArray(DISPLAYED_VALUES, displayedValues);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withSerializable(Serializable serializable) {
            this.serializable = serializable;
            return this;
        }

        public Builder withParcelable(Parcelable parcelable) {
            this.parcelable = parcelable;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMin(Integer min) {
            this.min = min;
            return this;
        }

        public Builder withMax(Integer max) {
            this.max = max;
            return this;
        }

        public Builder withValue(Integer value) {
            this.value = value;
            return this;
        }

        public Builder withDisplayedValues(String[] displayedValues) {
            this.displayedValues = displayedValues;
            return this;
        }
    }
}
