package localhost.toolkit.app;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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
	public static final String CURR_MENU_ITEM_ID = "currMenuItemId";
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private NavigationView mNavigationView;
	private int currMenuItemId;

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
		if (savedInstanceState == null) {
			navigateToHomeMenuItem();
		} else {
			currMenuItemId = savedInstanceState.getInt(CURR_MENU_ITEM_ID);
			mNavigationView.setCheckedItem(currMenuItemId);
			setTitle(mNavigationView.getMenu().findItem(currMenuItemId).getTitle());
		}
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
	}

	public void invalidateNavigationMenu() {
		onPrepareNavigationMenu(mNavigationView.getMenu());
	}

	private MenuItem getHomeMenuItem(Menu menu) {
		for (int i = 0; i < menu.size(); i++) {
			if (menu.getItem(i).isVisible()) {
				if (menu.getItem(i).hasSubMenu()) {
					MenuItem menuItem = getHomeMenuItem(menu.getItem(i).getSubMenu());
					if (menuItem != null)
						return menuItem;
				} else
					return menu.getItem(i);
			}
		}
		return null;
	}

	private MenuItem getMenuItemById(Menu menu, @IdRes int id) {
		for (int i = 0; i < menu.size(); i++) {
			if (menu.getItem(i).hasSubMenu()) {
				MenuItem menuItem = getMenuItemById(menu.getItem(i).getSubMenu(), id);
				if (menuItem != null)
					return menuItem;
			} else if (menu.getItem(i).getItemId() == id)
				return menu.getItem(i);
		}
		return null;
	}

	public ActionBarDrawerToggle getActionBarDrawerToggle() {
		return actionBarDrawerToggle;
	}

	public DrawerLayout getDrawerLayout() {
		return drawerLayout;
	}

	public void navigateToHomeMenuItem() {
		MenuItem menuItem = getHomeMenuItem(mNavigationView.getMenu());
		if (menuItem != null)
			onNavigationItemSelected(menuItem);
	}

	public void navigateToMenuItemById(@IdRes int id) {
		MenuItem menuItem = getMenuItemById(mNavigationView.getMenu(), id);
		if (menuItem != null)
			onNavigationItemSelected(menuItem);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		drawerLayout.closeDrawer(GravityCompat.START);
		Fragment f = getContentFragment(menuItem.getItemId());
		if (f != null) {
			currMenuItemId = menuItem.getItemId();
			mNavigationView.setCheckedItem(menuItem.getItemId());
			setTitle(menuItem.getTitle());
			getFragmentManager().beginTransaction().replace(R.id.content_frame, f).commitAllowingStateLoss();
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
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(CURR_MENU_ITEM_ID, currMenuItemId);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(GravityCompat.START))
			drawerLayout.closeDrawer(GravityCompat.START);
		else if (getFragmentManager().getBackStackEntryCount() > 0)
			getFragmentManager().popBackStack();
		else {
			MenuItem menuItem = getHomeMenuItem(mNavigationView.getMenu());
			if (menuItem == null || currMenuItemId == menuItem.getItemId())
				super.onBackPressed();
			else
				onNavigationItemSelected(menuItem);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}
}