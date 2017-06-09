package localhost.toolkit.os;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;

import localhost.toolkit.app.ProgressDialogFragment;

public abstract class ProgressAsyncTask<P, R> extends AsyncTask<P, String, R> {
	protected Activity activity;
	private ProgressDialogFragment progressFragment;
	private boolean cancellable;

	public ProgressAsyncTask(Activity activity, boolean progress, boolean cancellable) {
		this.activity = activity;
		this.cancellable = cancellable;
		if (progress)
			progressFragment = new ProgressDialogFragment();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		if (progressFragment != null) {
			progressFragment.show(activity.getFragmentManager(), localhost.toolkit.R.string.prgsMessage, cancellable);
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
			((ProgressDialog) progressFragment.getDialog()).setMessage(values[0]);
	}

	@Override
	protected void onPostExecute(R result) {
		super.onPostExecute(result);
		onFinish();
	}

	@Override
	protected void onCancelled(R result) {
		super.onCancelled(result);
		onFinish();
	}

	private void onFinish() {
		if (progressFragment != null)
			try {
				progressFragment.dismissAllowingStateLoss();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
