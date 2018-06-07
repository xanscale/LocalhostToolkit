package localhost.toolkit.preference;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.XmlRes;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceScreen;

/**
 * This is v14 PreferenceFragment with PreferenceScreen managed to works correctly with <code>AppCompatActivity</code>. Example of usage:
 * <pre>{@code public class MyPreferenceFragment extends ExtendedPreferenceFragment<MyPreferenceFragment> {
 *    @literal @Override protected int getPreferenceResId() { return R.xml.preferences; }
 *    @literal @Override protected int getPreferenceContainerResId() { return R.id.content; }
 *    @literal @Override protected MyPreferenceFragment newInstance() { return new MyPreferenceFragment(); }
 * }}</pre>
 */
public abstract class ExtendedPreferenceFragment<PF extends ExtendedPreferenceFragment> extends PreferenceFragment implements PreferenceFragment.OnPreferenceStartScreenCallback {
	@Override public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		setPreferencesFromResource(getPreferenceResId(), rootKey);
	}

	@Override public boolean onPreferenceStartScreen(PreferenceFragment preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
		ExtendedPreferenceFragment fragment = newInstance();
		Bundle args = new Bundle();
		args.putString(PreferenceFragment.ARG_PREFERENCE_ROOT, preferenceScreen.getKey());
		fragment.setArguments(args);
		getFragmentManager().beginTransaction().replace(getPreferenceContainerResId(), fragment).addToBackStack(null).commit();
		return true;
	}

	@Override public Fragment getCallbackFragment() {
		return this;
	}

	/**
	 * @return preference resource id like R.xml.preferences
	 */
	protected abstract @XmlRes int getPreferenceResId();

	/**
	 * @return preference container resource Id like R.id.content
	 */
	protected abstract @IdRes int getPreferenceContainerResId();

	/**
	 * @return Instance of extended class
	 */
	protected abstract PF newInstance();
}