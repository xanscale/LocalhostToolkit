package localhost.toolkit.app;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import localhost.toolkit.R;

public abstract class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	protected DrawerLayout mDrawerLayout;
	protected ActionBarDrawerToggle mDrawerToggle;
	protected MenuItem homeItem;
	private boolean homeSelected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer);
		if (getSupportActionBar() != null)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		NavigationView mNavigationView = (NavigationView) getLayoutInflater().inflate(getNavigationViewLayoutRes(), mDrawerLayout, false);
		mDrawerLayout.addView(mNavigationView);
		mNavigationView.setNavigationItemSelectedListener(this);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
		mDrawerLayout.addDrawerListener(mDrawerToggle);
		homeItem = mNavigationView.getMenu().getItem(0);
		if (savedInstanceState == null)
			onNavigationItemSelected(homeItem);
	}

	/**
	 * Delegate must return NavigationView Layout Resource
	 *
	 * @return something like R.layout.navigationView
	 */
	@LayoutRes
	protected abstract int getNavigationViewLayoutRes();

	/**
	 * Delegate must return fragment used as main content
	 *
	 * @param menuItemId of selected menu item
	 * @return Selected content fragment
	 */
	public abstract Fragment getContentFragment(int menuItemId);

	@Override
	public boolean onNavigationItemSelected(final MenuItem menuItem) {
		homeSelected = menuItem.equals(homeItem);
		menuItem.setChecked(true);
		setTitle(menuItem.getTitle());
		mDrawerLayout.closeDrawer(GravityCompat.START);
		getFragmentManager().beginTransaction().replace(R.id.content_frame, getContentFragment(menuItem.getItemId())).commitAllowingStateLoss();
		return true;
	}

	@Override
	public void onConfigurationChanged(final Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
			mDrawerLayout.closeDrawer(GravityCompat.START);
		else {
			if (homeSelected)
				super.onBackPressed();
			else
				onNavigationItemSelected(homeItem);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
}