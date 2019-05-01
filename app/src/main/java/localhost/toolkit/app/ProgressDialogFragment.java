package localhost.toolkit.app;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import localhost.toolkit.R;

public class ProgressDialogFragment extends DialogFragment {
	private static final String KEY_MSG = "KEY_MSG";
	private static final String KEY_CANCELABLE = "KEY_CANCELABLE";

	public static ProgressDialogFragment newInstance(int stringId, boolean cancellable) {
		Bundle args = new Bundle();
		args.putInt(KEY_MSG, stringId);
		args.putBoolean(KEY_CANCELABLE, cancellable);
		ProgressDialogFragment fragment = new ProgressDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		assert getActivity() != null;
		assert getArguments() != null;
		ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setTitle(R.string.prgsTitle);
		pd.setMessage(getString(getArguments().getInt(KEY_MSG)));
		setCancelable((getArguments().getBoolean(KEY_CANCELABLE)));
		pd.setCanceledOnTouchOutside((getArguments().getBoolean(KEY_CANCELABLE)));
		getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		return pd;
	}

	@Override public void onStop() {
		assert getActivity() != null;
		getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		super.onStop();
	}
}