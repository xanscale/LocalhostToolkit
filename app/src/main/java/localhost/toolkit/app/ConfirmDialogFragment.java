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
    private static final String KEY_ICON = "icon";
    private static final String KEY_MSG = "message";
    private static final String KEY_TITLE = "title";
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_BT_POS = "positiveButtonText";
    private static final String KEY_BT_NEG = "negativeButtonText";
    private static final String KEY_BT_NEU = "neutralButtonText";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String title = getArguments().getString(KEY_TITLE);
        String msg = getArguments().getString(KEY_MSG);
        String positiveButtonText = getArguments().getString(KEY_BT_POS);
        String negativeButtonText = getArguments().getString(KEY_BT_NEG);
        String neutralButtonText = getArguments().getString(KEY_BT_NEU);
        Bitmap icon = getArguments().getParcelable(KEY_ICON);
        builder.setTitle(title);
        builder.setMessage(msg);
        if (icon != null)
            builder.setIcon(new BitmapDrawable(getResources(), icon));
        builder.setPositiveButton(positiveButtonText == null ? getString(android.R.string.ok) : positiveButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_POSITIVE);
            }
        });
        builder.setNegativeButton(negativeButtonText == null ? getString(android.R.string.cancel) : negativeButtonText, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEGATIVE);
            }
        });
        if (neutralButtonText != null)
            builder.setNeutralButton(neutralButtonText, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    assert getArguments() != null;
                    getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEUTRAL);
                }
            });
        setCancelable(false);
        builder.setCancelable(false);
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

    public class Builder {
        private Serializable extra;
        private String title;
        private String msg;
        private Bitmap icon;
        private String positiveButtonText;
        private String negativeButtonText;
        private String neutralButtonText;

        public ConfirmDialogFragment build() {
            ConfirmDialogFragment fragment = new ConfirmDialogFragment();
            Bundle args = new Bundle();
            if (title != null)
                args.putString(KEY_TITLE, title);
            if (msg != null)
                args.putString(KEY_MSG, msg);
            if (positiveButtonText != null)
                args.putString(KEY_BT_POS, positiveButtonText);
            if (negativeButtonText != null)
                args.putString(KEY_BT_NEG, negativeButtonText);
            if (neutralButtonText != null)
                args.putString(KEY_BT_NEU, neutralButtonText);
            if (extra != null)
                args.putSerializable(KEY_EXTRA, extra);
            if (icon != null)
                args.putParcelable(KEY_ICON, icon);
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

        public Builder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder withIcon(Bitmap icon) {
            this.icon = icon;
            return this;
        }

        public Builder withPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder withNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder withNeutralButtonText(String neutralButtonText) {
            this.neutralButtonText = neutralButtonText;
            return this;
        }
    }
}