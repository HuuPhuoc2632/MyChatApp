package com.hhp.huuphuoc372.mychatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.hhp.huuphuoc372.mychatapp.Adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_menu.itemIconTintList = null

        val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager){tab, pos->
            when(pos){
                0->{tab.text="Chat"}
                else->{tab.text="Users"}
            }
        }.attach()



    }

    override fun onBackPressed() {
    }
}