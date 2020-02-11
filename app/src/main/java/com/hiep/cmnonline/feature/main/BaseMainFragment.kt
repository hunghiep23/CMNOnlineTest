package com.hiep.cmnonline.feature.main

import com.hiep.cmnonline.feature.BaseFragment

abstract class BaseMainFragment : BaseFragment() {
    val mainActivity: MainActivity?
        get() = activity as? MainActivity
}