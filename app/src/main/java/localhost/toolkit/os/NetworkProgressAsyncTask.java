package localhost.toolkit.os;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public abstract class NetworkProgressAsyncTask<P, R> extends ProgressAsyncTask<P, R> {
	private NetworkInfo info;

	public NetworkProgressAsyncTask(Activity activity, boolean progress, boolean cancellable) {
		super(activity, progress, cancellable);
		info = ((ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
	}

	@Override
	protected void onPreExecute() {
		if (info != null && info.isConnected())
			super.onPreExecute();
		else {
			Toast.makeText(activity, localhost.toolkit.R.string.noConnection, Toast.LENGTH_SHORT).show();
			cancel(true);
		}
	}
}
