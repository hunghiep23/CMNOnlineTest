package com.hiep.cmnonline.feature.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    private val pageList = arrayListOf<Fragment>()
    override fun getItem(position: Int): Fragment = pageList[position]
    override fun getCount(): Int = pageList.size

    fun addPage(page:Fragment){
        pageList.add(page)
        notifyDataSetChanged()
    }

    fun setPageList(pages: List<Fragment>) {
        pageList.clear()
        pageList.addAll(pages)
        notifyDataSetChanged()
    }
}