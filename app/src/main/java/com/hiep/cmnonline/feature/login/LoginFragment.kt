package com.hiep.cmnonline.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hiep.cmnonline.feature.BaseFragment
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.login.forgotpassword.ForgotPasswordFragment
import com.hiep.cmnonline.feature.main.MainActivity
import com.hiep.cmnonline.helper.SharedPreferencesHelper
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    private val loginActivity: LoginContainerActivity?
        get() = activity as? LoginContainerActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun initViews() {
        btnLogin.setOnClickListener(onClickListener)
        tvForgotPassword.setOnClickListener(onClickListener)
        tvRegister.setOnClickListener(onClickListener)
    }

    override fun onStart() {
        super.onStart()
        loginActivity?.apply {
            setActionbarTitle(R.string.login_title)
            hideBackButton()
        }
    }

    private val onClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.btnLogin -> {
                activity?.let { activity ->
                    SharedPreferencesHelper.getInstance(activity.applicationContext).logged = true
                    MainActivity.start(activity)
                    activity.finish()
                }
            }
            R.id.tvForgotPassword -> {
                activity?.setFragment(ForgotPasswordFragment(), true)
            }
        }
    }

    override fun onBack() {
        backToExit()
    }
}
