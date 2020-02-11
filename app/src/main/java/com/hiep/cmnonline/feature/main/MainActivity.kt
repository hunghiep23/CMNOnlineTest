package com.hiep.cmnonline.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hiep.cmnonline.feature.BaseActivity
import com.hiep.cmnonline.feature.BaseFragment
import com.hiep.cmnonline.R
import com.hiep.cmnonline.model.NavigationItem
import com.hiep.cmnonline.model.indexOf
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    lateinit var mainPagerAdapter: MainPagerAdapter
    var navScroll = false
    var navTap = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initViews() {
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        val navMenu = navMain.menu
        NavigationItem.values().forEach {
            navMenu.add(0, it.idRes, 0, it.titleRes)
                .setIcon(it.iconRes)
            mainPagerAdapter.addPage(it.toFragment())
        }
        vpHomeContainer.apply {
            adapter = mainPagerAdapter
            offscreenPageLimit = 2
            addOnPageChangeListener(onPageChangedListener)
        }
        navMain.setOnNavigationItemSelectedListener(onNavigationItemSelectedList)
    }

    private val onPageChangedListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            currentFragment = mainPagerAdapter.getItem(position) as? BaseFragment
            if (!navTap) {
                navScroll = true
                val itemId = NavigationItem.fromFragment(mainPagerAdapter.getItem(position))?.idRes
                itemId?.let { navMain.selectedItemId = it }
            } else {
                navTap = false
            }
            if (position == mainPagerAdapter.count - 1)
                vSpacing.visibility = View.GONE
            else
                vSpacing.visibility = View.VISIBLE

        }
    }

    private val onNavigationItemSelectedList =
        BottomNavigationView.OnNavigationItemSelectedListener {
            if (!navScroll) {
                navTap = true
                setPage(navMain.menu.indexOf(it))
            } else {
                navScroll = false
            }
            true
        }

    fun setPage(position: Int) {
        if (mainPagerAdapter.count > position)
            vpHomeContainer.currentItem = position
    }

    override fun onBackPressed() {
        currentFragment?.onBack()
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}


