package localhost.toolkit.os;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import localhost.toolkit.R;
import localhost.toolkit.app.ProgressDialogFragment;

public abstract class ProgressAsyncTask<Params, Result> extends AsyncTask<Params, String, Result> {
	private ProgressDialogFragment progressFragment;
	protected FragmentActivity activity;
	private boolean cancellable;

	public ProgressAsyncTask(FragmentActivity activity, boolean progress, boolean cancellable) {
		this.activity = activity;
		this.cancellable = cancellable;
		if (progress)
			progressFragment = new ProgressDialogFragment();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		if (progressFragment != null) {
			progressFragment.show(activity.getSupportFragmentManager(), R.string.prgsMessage, cancellable);
			progressFragment.onCancel(new DialogInterface() {
				@Override
				public void dismiss() {
				}

				@Override
				public void cancel() {
					ProgressAsyncTask.this.cancel(false);
				}
			});
		}
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		if (progressFragment != null)
			((ProgressDialog) progressFragment.getDialog()).setMessage((String) values[0]);
	}

	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		onFinish();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCancelled(Result result) {
		super.onCancelled(result);
		onFinish();
	}

	private void onFinish() {
		activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		if (progressFragment != null)
			try {
				progressFragment.dismissAllowingStateLoss();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
