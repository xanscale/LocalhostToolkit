package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import localhost.toolkit.R;

public class ProgressDialogFragment extends DialogFragment {
    private static final String MESSAGE = "MESSAGE";
    private static final String CANCELABLE = "CANCELABLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle(R.string.prgsTitle);
        if (getArguments().containsKey(MESSAGE))
            pd.setMessage(getString(getArguments().getInt(MESSAGE)));
        if (getArguments().containsKey(CANCELABLE))
            setCancelable((getArguments().getBoolean(CANCELABLE)));
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return pd;
    }

    @Override
    public void onStop() {
        assert getActivity() != null;
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onStop();
    }

    public static class Builder {
        private Integer message;
        private Boolean cancelable;

        public ProgressDialogFragment build() {
            ProgressDialogFragment fragment = new ProgressDialogFragment();
            Bundle args = new Bundle();
            if (message != null)
                args.putInt(MESSAGE, message);
            if (cancelable != null)
                args.putBoolean(CANCELABLE, cancelable);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withMessage(Integer message) {
            this.message = message;
            return this;
        }

        public Builder withCancelable(Boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }
    }
}