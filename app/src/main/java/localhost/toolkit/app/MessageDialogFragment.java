package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Html;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MessageDialogFragment extends DialogFragment implements OnClickListener {
    private static final String TITLE = "TITLE";
    private static final String MESSAGE = "MESSAGE";
    private static final String EXIT = "EXIT";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getArguments().containsKey(TITLE))
            builder.setTitle(getArguments().getString(TITLE));
        if (getArguments().containsKey(MESSAGE))
            builder.setMessage(Html.fromHtml(getArguments().getString(MESSAGE)));
        builder.setPositiveButton(android.R.string.ok, getArguments().containsKey(EXIT) && getArguments().getBoolean(EXIT) ? this : null);
        setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        assert getActivity() != null;
        getActivity().onBackPressed();
    }

    public class Builder {
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