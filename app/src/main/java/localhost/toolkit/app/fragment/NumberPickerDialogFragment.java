package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
    private static final String EXTRA = "EXTRA";

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
        builder.setView(numberPicker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnNumberSetListener().onNumberSet(getArguments().getSerializable(EXTRA), numberPicker.getValue());
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
        void onNumberSet(Serializable extra, int value);
    }

    public static class Builder {
        private Serializable extra;
        private String title;
        private Integer min;
        private Integer max;

        public NumberPickerDialogFragment build() {
            NumberPickerDialogFragment fragment = new NumberPickerDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (extra != null) args.putSerializable(EXTRA, extra);
            if (min != null) args.putInt(MIN, min);
            if (max != null) args.putInt(MAX, max);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withExtra(Serializable extra) {
            this.extra = extra;
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
    }
}
