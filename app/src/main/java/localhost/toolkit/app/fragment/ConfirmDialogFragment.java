package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ConfirmDialogFragment extends DialogFragment {
    private static final String ICON = "ICON";
    private static final String MESSAGE = "MESSAGE";
    private static final String TITLE = "TITLE";
    private static final String POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final String NEGATIVE_BUTTON = "NEGATIVE_BUTTON";
    private static final String NEUTRAL_BUTTON = "NEUTRAL_BUTTON";
    private static final String SERIALIZABLE = "SERIALIZABLE";
    private static final String PARCELABLE = "PARCELABLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString(TITLE));
        builder.setMessage(getArguments().getString(MESSAGE));
        if (getArguments().containsKey(ICON))
            builder.setIcon(new BitmapDrawable(getResources(), (Bitmap) getArguments().getParcelable(ICON)));
        builder.setPositiveButton(getArguments().getString(POSITIVE_BUTTON, getString(android.R.string.ok)), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), DialogInterface.BUTTON_POSITIVE);
            }
        });
        builder.setNegativeButton(getArguments().getString(NEGATIVE_BUTTON, getString(android.R.string.cancel)), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), DialogInterface.BUTTON_NEGATIVE);
            }
        });
        if (getArguments().containsKey(NEUTRAL_BUTTON))
            builder.setNeutralButton(getArguments().getString(NEUTRAL_BUTTON), new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    assert getArguments() != null;
                    getOnConfirmedListener().onConfirmation(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), DialogInterface.BUTTON_NEUTRAL);
                }
            });
        setCancelable(false);
        return builder.create();
    }

    private OnConfirmedListener getOnConfirmedListener() {
        OnConfirmedListener l = (OnConfirmedListener) getParentFragment();
        if (l == null)
            l = (OnConfirmedListener) getActivity();
        return l;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL})
    @interface ConfirmDialogButton {
    }

    public interface OnConfirmedListener {
        void onConfirmation(Serializable serializable, Parcelable parcelable, @ConfirmDialogButton int buttonClicked);
    }

    public static class Builder {
        private Serializable serializable;
        private Parcelable parcelable;
        private String title;
        private String message;
        private Bitmap icon;
        private String positiveButton;
        private String negativeButton;
        private String neutralButton;

        public ConfirmDialogFragment build() {
            ConfirmDialogFragment fragment = new ConfirmDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (message != null) args.putString(MESSAGE, message);
            if (positiveButton != null) args.putString(POSITIVE_BUTTON, positiveButton);
            if (negativeButton != null) args.putString(NEGATIVE_BUTTON, negativeButton);
            if (neutralButton != null) args.putString(NEUTRAL_BUTTON, neutralButton);
            if (serializable != null) args.putSerializable(SERIALIZABLE, serializable);
            if (parcelable != null) args.putParcelable(PARCELABLE, parcelable);
            if (icon != null) args.putParcelable(ICON, icon);
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

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withIcon(Bitmap icon) {
            this.icon = icon;
            return this;
        }

        public Builder withPositiveButton(String positiveButton) {
            this.positiveButton = positiveButton;
            return this;
        }

        public Builder withNegativeButton(String negativeButton) {
            this.negativeButton = negativeButton;
            return this;
        }

        public Builder withNeutralButton(String neutralButton) {
            this.neutralButton = neutralButton;
            return this;
        }
    }
}