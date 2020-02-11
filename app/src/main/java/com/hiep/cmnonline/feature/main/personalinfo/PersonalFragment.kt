package com.hiep.cmnonline.feature.main.personalinfo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.login.LoginContainerActivity
import com.hiep.cmnonline.feature.main.BaseMainFragment
import com.hiep.cmnonline.helper.SharedPreferencesHelper
import kotlinx.android.synthetic.main.fragment_personal.*

class PersonalFragment : BaseMainFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun initViews() {
        btnLogout.setOnClickListener {
            mainActivity?.let {
                SharedPreferencesHelper.getInstance(it.applicationContext).logged=false
                LoginContainerActivity.start(it)
                it.finish()
            }
        }
    }

    override fun onBack() {
        mainActivity?.setPage(0)
    }
}
