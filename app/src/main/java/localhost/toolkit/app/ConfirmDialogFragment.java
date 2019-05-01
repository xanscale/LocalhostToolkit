package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

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
    private static final String EXTRA = "EXTRA";
    private static final String POSITIVE_BUTTON = "POSITIVE_BUTTON";
    private static final String NEGATIVE_BUTTON = "NEGATIVE_BUTTON";
    private static final String NEUTRAL_BUTTON = "NEUTRAL_BUTTON";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getArguments().containsKey(TITLE))
            builder.setTitle(getArguments().getString(TITLE));
        if (getArguments().containsKey(MESSAGE))
            builder.setMessage(getArguments().getString(MESSAGE));
        if (getArguments().containsKey(ICON))
            builder.setIcon(new BitmapDrawable(getResources(), (Bitmap) getArguments().getParcelable(ICON)));
        builder.setPositiveButton(getArguments().containsKey(POSITIVE_BUTTON) ? getString(android.R.string.ok) : getArguments().getString(POSITIVE_BUTTON), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(getArguments().getSerializable(EXTRA), DialogInterface.BUTTON_POSITIVE);
            }
        });
        builder.setNegativeButton(getArguments().containsKey(NEGATIVE_BUTTON) ? getString(android.R.string.cancel) : getArguments().getString(NEGATIVE_BUTTON), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(getArguments().getSerializable(EXTRA), DialogInterface.BUTTON_NEGATIVE);
            }
        });
        if (getArguments().containsKey(NEUTRAL_BUTTON))
            builder.setNeutralButton(getArguments().getString(NEUTRAL_BUTTON), new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    assert getArguments() != null;
                    getOnConfirmedListener().onConfirmation(getArguments().getSerializable(EXTRA), DialogInterface.BUTTON_NEUTRAL);
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
        void onConfirmation(Serializable extra, @ConfirmDialogButton int buttonClicked);
    }

    public static class Builder {
        private Serializable extra;
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
            if (extra != null) args.putSerializable(EXTRA, extra);
            if (icon != null) args.putParcelable(ICON, icon);
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