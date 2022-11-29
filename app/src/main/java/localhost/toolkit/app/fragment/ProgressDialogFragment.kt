package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import localhost.toolkit.R;

public class ProgressDialogFragment extends DialogFragment {
    private static final String CANCELABLE = "CANCELABLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_MaterialComponents_Dialog_Alert_App);
        builder.setView(new ProgressBar(requireContext()));
        setCancelable((requireArguments().getBoolean(CANCELABLE, true)));
        requireActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return builder.create();
    }

    @Override
    public void onStop() {
        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onStop();
    }

    public static class Builder {
        private Boolean cancelable;

        public ProgressDialogFragment build() {
            ProgressDialogFragment fragment = new ProgressDialogFragment();
            Bundle args = new Bundle();
            if (cancelable != null)
                args.putBoolean(CANCELABLE, cancelable);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withCancelable(Boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }
    }
}