package localhost.toolkit.app;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import localhost.toolkit.R;

public abstract class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private Menu navigationMenu;
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
		navigationMenu = mNavigationView.getMenu();
		onPrepareNavigationMenu(navigationMenu);
		if (savedInstanceState == null)
			onNavigationItemSelected(getFirstNavigationMenuItem());
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
	protected abstract Fragment getContentFragment(int menuItemId);

	/**
	 * Prepare menu to be displayed.
	 *
	 * @param menu The NavigationView Menu
	 */
	public void onPrepareNavigationMenu(Menu menu) {
	}

	@Override public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	private MenuItem getFirstNavigationMenuItem() {
		for (int i = 0; i < navigationMenu.size(); i++)
			if (navigationMenu.getItem(i).isVisible())
				return navigationMenu.getItem(i);
		return null;
	}

	@Override
	public boolean onNavigationItemSelected(final MenuItem menuItem) {
		homeSelected = menuItem.equals(getFirstNavigationMenuItem());
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
				onNavigationItemSelected(getFirstNavigationMenuItem());
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
}