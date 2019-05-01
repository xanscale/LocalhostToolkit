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
        if (getArguments().containsKey(KEY_TITLE))
            builder.setTitle(getArguments().getString(KEY_TITLE));
        if (getArguments().containsKey(KEY_MSG))
            builder.setMessage(getArguments().getString(KEY_MSG));
        if (getArguments().containsKey(KEY_ICON))
            builder.setIcon(new BitmapDrawable(getResources(), (Bitmap) getArguments().getParcelable(KEY_ICON)));
        builder.setPositiveButton(getArguments().containsKey(KEY_BT_POS) ? getString(android.R.string.ok) : getArguments().getString(KEY_BT_POS), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_POSITIVE);
            }
        });
        builder.setNegativeButton(getArguments().containsKey(KEY_BT_NEG) ? getString(android.R.string.cancel) : getArguments().getString(KEY_BT_NEG), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert getArguments() != null;
                getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEGATIVE);
            }
        });
        if (getArguments().containsKey(KEY_BT_NEU))
            builder.setNeutralButton(getArguments().getString(KEY_BT_NEU), new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    assert getArguments() != null;
                    getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEUTRAL);
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