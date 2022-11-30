package localhost.toolkit.app.appcompat

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import localhost.toolkit.R

abstract class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        const val CURR_MENU_ITEM_ID = "currMenuItemId"
    }

    protected abstract val headerViewResId: Int
    protected abstract val menuResId: Int
    protected abstract val isVersionShowed: Boolean
    protected abstract fun getContentFragment(menuItemId: Int): Fragment?
    lateinit var drawerLayout: DrawerLayout
        private set
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
        private set
    private lateinit var navigationView: NavigationView
    private var currMenuItemId = 0
    val headerView: View
        get() = navigationView.getHeaderView(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation)
        navigationView.inflateHeaderView(headerViewResId)
        navigationView.inflateMenu(menuResId)
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(this)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        if (isVersionShowed) {
            navigationView.inflateMenu(R.menu.navigation_footer)
            packageManager.getPackageInfo(packageName, 0).let {
                (navigationView.menu.findItem(R.id.version).actionView as TextView).text =
                    getString(R.string.versionValue, it.versionName, PackageInfoCompat.getLongVersionCode(it))
            }
        }
        invalidateNavigationMenu()
        if (savedInstanceState == null) {
            navigateToHomeMenuItem()
        } else {
            currMenuItemId = savedInstanceState.getInt(CURR_MENU_ITEM_ID)
            navigationView.setCheckedItem(currMenuItemId)
            title = navigationView.menu.findItem(currMenuItemId).title
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        getContentFragment(menuItem.itemId)?.let {
            currMenuItemId = menuItem.itemId
            navigationView.setCheckedItem(menuItem.itemId)
            title = menuItem.title
            supportFragmentManager.commit {
                replace(R.id.content_frame, it)
            }
        }
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURR_MENU_ITEM_ID, currMenuItemId)
        super.onSaveInstanceState(outState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else getHomeMenuItem(navigationView.menu).let { menuItem ->
            if (menuItem == null || currMenuItemId == menuItem.itemId) super.onBackPressed()
            else onNavigationItemSelected(menuItem)
        }
    }

    private fun getHomeMenuItem(menu: Menu): MenuItem? {
        repeat(menu.size()) { i ->
            if (menu.getItem(i).isVisible && menu.getItem(i).isEnabled)
                menu.getItem(i).subMenu?.let {
                    getHomeMenuItem(it)?.let { menuItem -> return menuItem }
                } ?: return menu.getItem(i)
        }
        return null
    }

    private fun getMenuItemById(menu: Menu, @IdRes id: Int): MenuItem? {
        repeat(menu.size()) { i ->
            if (menu.getItem(i).itemId == id)
                return menu.getItem(i)
            menu.getItem(i).subMenu?.let {
                getMenuItemById(it, id)?.let { menuItem -> return menuItem }
            }
        }
        return null
    }

    fun onPrepareNavigationMenu(menu: Menu?) {
    }

    fun invalidateNavigationMenu() {
        navigationView.post { onPrepareNavigationMenu(navigationView.menu) }
    }

    fun navigateToHomeMenuItem() {
        getHomeMenuItem(navigationView.menu)?.let { onNavigationItemSelected(it) }
    }

    fun navigateToMenuItemById(@IdRes id: Int) {
        getMenuItemById(navigationView.menu, id)?.let { onNavigationItemSelected(it) }
    }
}