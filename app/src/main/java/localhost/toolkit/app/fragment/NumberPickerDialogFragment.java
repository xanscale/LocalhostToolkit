package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class NumberPickerDialogFragment extends DialogFragment {
    private static final String TITLE = "TITLE";
    private static final String MIN = "MIN";
    private static final String MAX = "MAX";
    private static final String VALUE = "VALUE";
    private static final String SERIALIZABLE = "SERIALIZABLE";
    private static final String PARCELABLE = "PARCELABLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getArguments().containsKey(TITLE))
            builder.setTitle(getArguments().getString(TITLE));
        final NumberPicker numberPicker = new NumberPicker(getActivity());
        if (getArguments().containsKey(MIN))
            numberPicker.setMinValue(getArguments().getInt(MIN));
        if (getArguments().containsKey(MAX))
            numberPicker.setMaxValue(getArguments().getInt(MAX));
        if (getArguments().containsKey(VALUE))
            numberPicker.setValue(getArguments().getInt(VALUE));
        builder.setView(numberPicker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnNumberSetListener().onNumberSet(getArguments().getSerializable(SERIALIZABLE), getArguments().getParcelable(PARCELABLE), numberPicker.getValue());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        setCancelable(false);
        return builder.create();
    }

    private OnNumberSetListener getOnNumberSetListener() {
        OnNumberSetListener l = (OnNumberSetListener) getParentFragment();
        if (l == null)
            l = (OnNumberSetListener) getActivity();
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

        public NumberPickerDialogFragment build() {
            NumberPickerDialogFragment fragment = new NumberPickerDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (serializable != null) args.putSerializable(SERIALIZABLE, serializable);
            if (parcelable != null) args.putParcelable(PARCELABLE, parcelable);
            if (min != null) args.putInt(MIN, min);
            if (max != null) args.putInt(MAX, max);
            if (value != null) args.putInt(VALUE, value);
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
    }
}