package localhost.toolkit.os;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public abstract class NetworkProgressAsyncTask<A extends FragmentActivity, P, R> extends ProgressAsyncTask<A, P, R> {
    private NetworkInfo info;

    public NetworkProgressAsyncTask(A activity, boolean progress, boolean cancellable) {
        super(activity, progress, cancellable);
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
            info = connectivityManager.getActiveNetworkInfo();
    }

    @Override
    protected void onPreExecute() {
        if (info != null && info.isConnected())
            super.onPreExecute();
        else {
            if (getActivity() != null)
                Toast.makeText(getActivity(), localhost.toolkit.R.string.noConnection, Toast.LENGTH_SHORT).show();
            cancel(true);
        }
    }
}
