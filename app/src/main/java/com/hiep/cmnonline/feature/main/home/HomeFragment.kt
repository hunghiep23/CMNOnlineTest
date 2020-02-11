package com.hiep.cmnonline.feature.main.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.main.BaseMainFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseMainFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initViews() {

    }

    override fun onBack() {
        backToExit()
    }
}
