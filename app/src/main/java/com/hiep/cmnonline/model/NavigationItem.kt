package com.hiep.cmnonline.model

import android.view.Menu
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.main.home.HomeFragment
import com.hiep.cmnonline.feature.main.personalinfo.PersonalFragment
import com.hiep.cmnonline.feature.main.payment.TradeFragment

enum class NavigationItem(
    @IdRes val idRes: Int, @StringRes val titleRes: Int, @DrawableRes val iconRes: Int, val fragmentName: String
) {
    HOME(
        R.id.nav_home,
        R.string.navigation_home,
        R.drawable.ic_home,
        HomeFragment::class.java.name
    ) {
        override fun toFragment() =
            HomeFragment()
    },
    TRADE(
        R.id.nav_trade,
        R.string.navigation_trade,
        R.drawable.ic_wallet,
        TradeFragment::class.java.name
    ) {
        override fun toFragment() =
            TradeFragment()
    },
    PERSONAL(
        R.id.nav_personal,
        R.string.navigation_personal,
        R.drawable.ic_user,
        PersonalFragment::class.java.name
    ) {
        override fun toFragment() =
            PersonalFragment()
    };

    abstract fun toFragment(): Fragment

    companion object {
        @JvmStatic

        fun fromFragment(fragment: Fragment): NavigationItem? =
            values().firstOrNull {
                it.fragmentName == fragment::class.java.name
            }
    }
}

fun Menu.indexOf(item: MenuItem): Int {
    for (i in 0..size())
        if (item.itemId == this.getItem(i).itemId)
            return i
    return 0
}