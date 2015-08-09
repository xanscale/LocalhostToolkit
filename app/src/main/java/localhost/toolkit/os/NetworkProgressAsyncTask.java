package localhost.toolkit.os;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import localhost.toolkit.R;

public abstract class NetworkProgressAsyncTask<Params, Result> extends ProgressAsyncTask<Params, Result> {
	private NetworkInfo info;

	public NetworkProgressAsyncTask(FragmentActivity activity, boolean progress, boolean cancellable) {
		super(activity, progress, cancellable);
		info = ((ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
	}

	@Override
	protected void onPreExecute() {
		if (info != null && info.isConnected())
			super.onPreExecute();
		else {
			Toast.makeText(activity, R.string.noConnection, Toast.LENGTH_SHORT).show();
			cancel(true);
		}
	}
}
