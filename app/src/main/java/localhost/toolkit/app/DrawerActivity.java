package localhost.toolkit.app;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import localhost.toolkit.R;

public abstract class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private NavigationView mNavigationView;
	private boolean homeSelected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer);
		if (getSupportActionBar() != null)
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mNavigationView = (NavigationView) getLayoutInflater().inflate(getNavigationViewLayoutRes(), drawerLayout, false);
		drawerLayout.addView(mNavigationView);
		mNavigationView.setNavigationItemSelectedListener(this);
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		invalidateNavigationMenu();
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
	 * @return Selected content fragment or null if you want to do manage locally
	 */
	@Nullable
	protected abstract Fragment getContentFragment(int menuItemId);

	/**
	 * Prepare menu to be displayed.
	 *
	 * @param menu The NavigationView Menu
	 */
	@CallSuper
	protected void onPrepareNavigationMenu(Menu menu) {
		onNavigationItemSelected(getFirstNavigationMenuItem());
	}

	public void invalidateNavigationMenu() {
		onPrepareNavigationMenu(mNavigationView.getMenu());
	}

	private MenuItem getFirstNavigationMenuItem() {
		for (int i = 0; i < mNavigationView.getMenu().size(); i++)
			if (mNavigationView.getMenu().getItem(i).isVisible())
				return mNavigationView.getMenu().getItem(i);
		return null;
	}

	public ActionBarDrawerToggle getActionBarDrawerToggle() {
		return actionBarDrawerToggle;
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		drawerLayout.closeDrawer(GravityCompat.START);
		if (menuItem != null) {
			Fragment f = getContentFragment(menuItem.getItemId());
			if (f != null) {
				homeSelected = menuItem.equals(getFirstNavigationMenuItem());
				mNavigationView.setCheckedItem(menuItem.getItemId());
				setTitle(menuItem.getTitle());
				getFragmentManager().beginTransaction().replace(R.id.content_frame, f).commitAllowingStateLoss();
			}
		}
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(GravityCompat.START))
			drawerLayout.closeDrawer(GravityCompat.START);
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
		actionBarDrawerToggle.syncState();
	}
}