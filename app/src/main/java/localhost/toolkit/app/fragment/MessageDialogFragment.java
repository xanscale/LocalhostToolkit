package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MessageDialogFragment extends DialogFragment implements OnClickListener {
    private static final String TITLE = "TITLE";
    private static final String MESSAGE = "MESSAGE";
    private static final String EXIT = "EXIT";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(requireArguments().getString(TITLE));
        if (requireArguments().containsKey(MESSAGE))
            builder.setMessage(HtmlCompat.fromHtml(requireArguments().getString(MESSAGE), HtmlCompat.FROM_HTML_MODE_COMPACT));
        builder.setPositiveButton(android.R.string.ok, requireArguments().getBoolean(EXIT) ? this : null);
        setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        requireActivity().onBackPressed();
    }

    public static class Builder {
        private String title;
        private String message;
        private Boolean exit;

        public MessageDialogFragment build() {
            MessageDialogFragment fragment = new MessageDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (message != null) args.putString(MESSAGE, message);
            if (exit != null) args.putBoolean(EXIT, exit);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withExit(Boolean exit) {
            this.exit = exit;
            return this;
        }
    }
}