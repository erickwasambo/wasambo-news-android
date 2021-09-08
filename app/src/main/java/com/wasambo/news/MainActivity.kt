package com.wasambo.news
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun setupNavigation() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_nav_closed_icon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        drawerLayout = findViewById(R.id.drawer_layout)
    }

    override fun onSupportNavigateUp(): Boolean {
        toolbar.setNavigationIcon(R.drawable.ic_nav_closed_icon)
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawerLayout
        )
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
