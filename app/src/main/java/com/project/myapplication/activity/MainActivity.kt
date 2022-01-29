package com.project.myapplication.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.project.myapplication.R
import com.project.myapplication.fragments.*
import com.project.myapplication.interfac.MainActivityInterface
import kotlinx.android.synthetic.main.activity_main.*


class  MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    MainActivityInterface {

    lateinit var nav: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//        if(actionBar!=null)
//            this.actionBar!!.hide();

//        this.getWindow().setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // This is the code to hide the navigation and enabling full screen mode
     //nable full screen



        supportFragmentManager.beginTransaction()
            .replace(R.id.frame1, SalezFragment(), "SalezFragment").commit()


        val bot_nav: com.google.android.material.bottomnavigation.BottomNavigationView =
            findViewById(R.id.bot_nav)
        val drawer: androidx.drawerlayout.widget.DrawerLayout = findViewById(R.id.drawer)
        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(findViewById(R.id.toolbar))

        nav = findViewById(R.id.navigation_view)

        nav.setNavigationItemSelectedListener(this@MainActivity)


       //bot_nav.setOnNavigationItemSelectedListener { BottomNavigationView.OnNavigationItemSelectedListener() }


        bot_nav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

          when  (it.itemId)
          {
              R.id.bot_home->   replaceFragment("SalezFragment")
              R.id.bot_report-> replaceFragment("ReportFragment")
              R.id.bot_portfolio-> replaceFragment("StockReportFragment")
              R.id.bot_pur_sale-> replaceFragment("DoSaleFragment")
              R.id.bot_dep_draw-> replaceFragment("DepositWithdrawFragment")



          }
             true

        })

        }





    override fun onBackPressed() {
//        val count = supportFragmentManager.backStackEntryCount
//        if (count == 0) {
//            super.onBackPressed()
//            //additional code
//        } else {

        // supportFragmentManager.popBackStack()
//        when (Constants.from) {
//            "SalezFragment" -> replaceFragment("SalezFragment")
//
//
//
//        }

        replaceFragment("SalezFragment")

        //  }
    }


    fun replaceFragment(tag:String)
    {
        when (tag)
        {
            "SalezFragment" ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, SalezFragment(), "SalezFragment").commit()

            "ReportFragment" ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, ReportFragment(), "ReportFragment").commit()

            "StockReportFragment" ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, StockReportFragment(), "StockReportFragment").commit()

            "DoSaleFragment" ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, DoSalePurFragment(), "DoSaleFragment").commit()

            "DepositWithdrawFragment" ->  supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, DepositWithdrawFragment(), "DepositWithdrawFragment").commit()

        }


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                replaceFragment("SalezFragment")
            }
            R.id.nav_report -> {
                replaceFragment("ReportFragment")
            }

            else -> { // Note the block

            }
        }


        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun switchFragment(tag: String) {
        when (tag) {
            "SalezFragment" -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame1, SalezFragment(), "SalezFragment").commit()
            }

        }
    }


}