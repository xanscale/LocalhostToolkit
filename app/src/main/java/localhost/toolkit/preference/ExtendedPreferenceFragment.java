package localhost.toolkit.preference;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

/**
 * <pre>{@code
 * public static class PrefsFragment extends PreferenceFragment {
 *
 * @literal @Override
 * 	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
 * 		setPreferencesFromResource(R.xml.preferences, R.id.container ,rootKey);
 * 	}
 * }
 * }</pre>
 */
public abstract class ExtendedPreferenceFragment<PF extends ExtendedPreferenceFragment> extends PreferenceFragmentCompat implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {
	private int preferencesContainerResId;

	public void setPreferencesFromResource(@XmlRes int preferencesResId, @IdRes int preferencesContainerResId, @Nullable String key) {
		super.setPreferencesFromResource(preferencesResId, key);
		this.preferencesContainerResId = preferencesContainerResId;
	}

	@Override public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
		if (preferenceScreen.getKey() == null) {
			Log.w("ExtendedPreferenceFrag", "ExtendedPreferenceFragment require 'android:key' attribute to be set in PreferenceScreen.");
			return false;
		} else {
			ExtendedPreferenceFragment fragment = newInstance();
			Bundle args = new Bundle();
			args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, preferenceScreen.getKey());
			fragment.setArguments(args);
			getFragmentManager().beginTransaction().replace(preferencesContainerResId, fragment).addToBackStack(null).commit();
			return true;
		}
	}

	@Override public Fragment getCallbackFragment() {
		return this;
	}

	/**
	 * @return Instance of extended class
	 */
	protected abstract PF newInstance();
}