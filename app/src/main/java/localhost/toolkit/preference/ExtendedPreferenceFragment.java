package localhost.toolkit.preference;

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
	private int containerViewId;

	public void setPreferencesFromResource(@XmlRes int preferencesResId, @IdRes int containerViewId, @Nullable String key) {
		super.setPreferencesFromResource(preferencesResId, key);
		this.containerViewId = containerViewId;
	}

	@Override public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
		if (preferenceScreen.getKey() == null) {
			Log.w("ExtendedPreferenceFrag", "ExtendedPreferenceFragment require 'android:key' attribute to be set in PreferenceScreen.");
			return false;
		} else {
			getFragmentManager().beginTransaction().replace(containerViewId, newConcreteInstance(preferenceScreen.getKey())).addToBackStack(null).commit();
			return true;
		}
	}

	@Override public Fragment getCallbackFragment() {
		return this;
	}

	/**
	 * @param rootKey put in bundle arguments with ARG_PREFERENCE_ROOT key
	 * @return Instance of concrete class
	 */
	protected abstract PF newConcreteInstance(String rootKey);
}