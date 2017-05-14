package localhost.toolkit.app;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import localhost.toolkit.R;

public class ProgressDialogFragment extends DialogFragment {
	private static final String KEY_MSG = "KEY_MSG";
	private static final String KEY_CANCELABLE = "KEY_CANCELABLE";

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		ProgressDialog pd = new ProgressDialog(getActivity());
		pd.setTitle(R.string.prgsTitle);
		pd.setMessage(getString(getArguments().getInt(KEY_MSG)));
		setCancelable((getArguments().getBoolean(KEY_CANCELABLE)));
		pd.setCancelable((getArguments().getBoolean(KEY_CANCELABLE)));
		pd.setCanceledOnTouchOutside((getArguments().getBoolean(KEY_CANCELABLE)));
		getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		return pd;
	}

	@Override public void onStop() {
		super.onStop();
		getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	public void show(FragmentManager fragmentManager, int stringId, boolean cancellable) {
		Bundle arguments = new Bundle(2);
		arguments.putInt(KEY_MSG, stringId);
		arguments.putBoolean(KEY_CANCELABLE, cancellable);
		setArguments(arguments);
		show(fragmentManager, getClass().getSimpleName());
	}
}