package com.example.doctorstesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.doctorstesttask.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navigationView: NavigationView
    lateinit var appBarController:AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        FirebaseApp.initializeApp(this)

//        val registrFragment = RegistrationFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.drawer_layout, registrFragment).commit()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        navigationView = binding.navView
        navigationView.setupWithNavController(navController )
        val drawerLayout = binding.drawerLayout
        appBarController = AppBarConfiguration(navController.graph, drawerLayout)

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarController)
        binding.toolbar.setupWithNavController(navController, drawerLayout)
        navigationView.setupWithNavController(navController)








        appBarController = AppBarConfiguration(
            setOf(
                R.id.mainMenuFragment
            ), drawerLayout
        )
        appBarController.topLevelDestinations
        appBarController = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarController)
        navigationView.setupWithNavController(navController)





//        val toogle = getActionBarDrawerToggle(drawerLayout, binding.toolbar)
//        navController.addOnDestinationChangedListener{_, destination, _ ->
//            if (destination.id == R.id.mainMenuFragment)
//                toogle.isDrawerIndicatorEnabled = true
//        }
//
//        toogle.setToolbarNavigationClickListener {
//            toogle.isDrawerIndicatorEnabled = false
//            navController.navigate(R.id.mainMenuFragment)
//        }


    }

    fun getActionBarDrawerToggle(
        drawerLayout: DrawerLayout,
        toolbar: androidx.appcompat.widget.Toolbar
    ):ActionBarDrawerToggle{
        val toggle = ActionBarDrawerToggle(this,
        drawerLayout,
        toolbar,
        R.string.open,
        R.string.close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        return toggle
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        return true
    }










}