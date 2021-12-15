package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MessageDialogFragment extends DialogFragment {
    private static final String TITLE = "TITLE";
    private static final String MESSAGE = "MESSAGE";
    private static final String EXIT = "EXIT";
    private static final String POSITIVE_BUTTON = "POSITIVE_BUTTON";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(requireArguments().getString(TITLE));
        if (requireArguments().containsKey(MESSAGE))
            builder.setMessage(HtmlCompat.fromHtml(requireArguments().getString(MESSAGE), HtmlCompat.FROM_HTML_MODE_COMPACT));
        builder.setPositiveButton(requireArguments().getString(POSITIVE_BUTTON, getString(android.R.string.ok)), (dialog, which) -> {
            if (requireArguments().getBoolean(EXIT))
                requireActivity().onBackPressed();
        });
        setCancelable(false);
        return builder.create();
    }

    public static class Builder {
        private String title;
        private String message;
        private Boolean exit;
        private String positiveButton;

        public MessageDialogFragment build() {
            MessageDialogFragment fragment = new MessageDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (message != null) args.putString(MESSAGE, message);
            if (exit != null) args.putBoolean(EXIT, exit);
            if (positiveButton != null) args.putString(POSITIVE_BUTTON, positiveButton);
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

        public Builder withPositiveButton(String positiveButton) {
            this.positiveButton = positiveButton;
            return this;
        }
    }
}