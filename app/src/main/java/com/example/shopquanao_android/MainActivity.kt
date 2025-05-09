package com.example.shopquanao_android

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.ui.NavigationUI
import com.example.shopquanao_android.Firebase.FirebaseHelper
import com.example.shopquanao_android.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener {
    //        view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
            //Toast.makeText(applicationContext, "ok ngon", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, testactivity::class.java)
//            startActivity(intent)

            val intent = Intent(this, testactivity::class.java)
            startActivity(intent)
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val header = binding.navView.getHeaderView(0)
        val name = header.findViewById<TextView>(R.id.txtnameHeader)
        val email = header.findViewById<TextView>(R.id.txtEmail)
        if(FirebaseAuth.getInstance().currentUser!= null){
            name.text = FirebaseAuth.getInstance().currentUser?.displayName
            email.text = FirebaseAuth.getInstance().currentUser?.email
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_order
            ), drawerLayout
        )

        updateVisible()

        navView.setNavigationItemSelectedListener {
            item ->
            if(item.itemId == R.id.nav_SignIn){
                val intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }else if(item.itemId == R.id.nav_SignOut){
                FirebaseHelper.signOutUser()
                updateVisible()
            }else{
                NavigationUI.onNavDestinationSelected(item, navController)
                val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
                drawer.closeDrawer(GravityCompat.START)
            }
            true
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun updateVisible(){
        val navView: NavigationView = binding.navView
        if(FirebaseAuth.getInstance().currentUser != null){
            navView.menu.findItem(R.id.nav_SignOut).isVisible = true
            navView.menu.findItem(R.id.nav_SignIn).isVisible = false
        }else{
            navView.menu.findItem(R.id.nav_SignIn).isVisible = true
            navView.menu.findItem(R.id.nav_SignOut).isVisible = false
        }
    }
}