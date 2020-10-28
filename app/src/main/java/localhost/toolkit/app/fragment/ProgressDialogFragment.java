package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {
    private static final String MESSAGE = "MESSAGE";
    private static final String TITLE = "TITLE";
    private static final String CANCELABLE = "CANCELABLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog pd = new ProgressDialog(requireContext());
        pd.setTitle(requireArguments().getString(TITLE));
        pd.setMessage(requireArguments().getString(MESSAGE));
        setCancelable((requireArguments().getBoolean(CANCELABLE, true)));
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return pd;
    }

    @Override
    public void onStop() {
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onStop();
    }

    public static class Builder {
        private String title;
        private String message;
        private Boolean cancelable;

        public ProgressDialogFragment build() {
            ProgressDialogFragment fragment = new ProgressDialogFragment();
            Bundle args = new Bundle();
            if (title != null)
                args.putString(TITLE, title);
            if (message != null)
                args.putString(MESSAGE, message);
            if (cancelable != null)
                args.putBoolean(CANCELABLE, cancelable);
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

        public Builder withCancelable(Boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }
    }
}