package localhost.toolkit.app.appcompat;

import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import localhost.toolkit.R;

public abstract class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String CURR_MENU_ITEM_ID = "currMenuItemId";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private int currMenuItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation);
        navigationView.inflateHeaderView(getHeaderViewResId());
        navigationView.inflateMenu(getMenu());
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        if (isVersionShowed())
            try {
                navigationView.inflateMenu(R.menu.navigation_footer);
                TextView version = (TextView) navigationView.getMenu().findItem(R.id.version).getActionView();
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                version.setText(getString(R.string.versionValue, packageInfo.versionName, PackageInfoCompat.getLongVersionCode(packageInfo)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        invalidateNavigationMenu();
        if (savedInstanceState == null) {
            navigateToHomeMenuItem();
        } else {
            currMenuItemId = savedInstanceState.getInt(CURR_MENU_ITEM_ID);
            navigationView.setCheckedItem(currMenuItemId);
            setTitle(navigationView.getMenu().findItem(currMenuItemId).getTitle());
        }
    }

    /**
     * Implementation must return header view layout
     *
     * @return header view layout resource ID
     */
    protected abstract int getHeaderViewResId();

    /**
     * Implementation must return menu of navigationView
     *
     * @return menu resource ID
     */
    protected abstract int getMenu();

    /**
     * Choose if want to show app version info or not
     *
     * @return true if want to show version info
     */
    protected abstract boolean isVersionShowed();

    /**
     * Implementation must return fragment used as main content
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
        navigationView.post(new Runnable() {
            @Override
            public void run() {
                onPrepareNavigationMenu(navigationView.getMenu());
            }
        });
    }

    private MenuItem getHomeMenuItem(Menu menu) {
        for (int i = 0; i < menu.size(); i++)
            if (menu.getItem(i).isVisible() && menu.getItem(i).isEnabled())
                if (menu.getItem(i).hasSubMenu()) {
                    MenuItem menuItem = getHomeMenuItem(menu.getItem(i).getSubMenu());
                    if (menuItem != null)
                        return menuItem;
                } else
                    return menu.getItem(i);
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
        MenuItem menuItem = getHomeMenuItem(navigationView.getMenu());
        if (menuItem != null)
            onNavigationItemSelected(menuItem);
    }

    public void navigateToMenuItemById(@IdRes int id) {
        MenuItem menuItem = getMenuItemById(navigationView.getMenu(), id);
        if (menuItem != null)
            onNavigationItemSelected(menuItem);
    }

    public View getHeaderView() {
        return navigationView.getHeaderView(0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        Fragment f = getContentFragment(menuItem.getItemId());
        if (f != null) {
            currMenuItemId = menuItem.getItemId();
            navigationView.setCheckedItem(menuItem.getItemId());
            setTitle(menuItem.getTitle());
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, f).commitAllowingStateLoss();
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
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
        else if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else {
            MenuItem menuItem = getHomeMenuItem(navigationView.getMenu());
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