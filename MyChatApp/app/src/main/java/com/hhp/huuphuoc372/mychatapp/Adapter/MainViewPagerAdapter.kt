package com.hhp.huuphuoc372.mychatapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hhp.huuphuoc372.mychatapp.Fragment.ChatListFragment
import com.hhp.huuphuoc372.mychatapp.Fragment.ListUserFragment

class MainViewPagerAdapter(fragmentManager: FragmentManager, lifeCycle:Lifecycle): FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{ChatListFragment()}
            else -> {ListUserFragment()}
        }
    }
}