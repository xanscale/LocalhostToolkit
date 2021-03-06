package localhost.toolkit.os;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import localhost.toolkit.app.fragment.ProgressDialogFragment;

public abstract class ProgressAsyncTask<A extends FragmentActivity, P, R> extends AsyncTask<P, String, R> {
    private final WeakReference<A> activity;
    private final ProgressDialogFragment progressFragment;

    public ProgressAsyncTask(A activity, ProgressDialogFragment progressFragment) {
        this.activity = new WeakReference<>(activity);
        this.progressFragment = progressFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (getActivity() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null)
                inputMethodManager.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (progressFragment != null) {
                progressFragment.show(getActivity().getSupportFragmentManager(), null);
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
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if (progressFragment != null && progressFragment.getDialog() != null)
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

    @Nullable
    protected A getActivity() {
        A activity = this.activity.get();
        if (activity == null || activity.isFinishing())
            return null;
        else
            return activity;
    }
}
