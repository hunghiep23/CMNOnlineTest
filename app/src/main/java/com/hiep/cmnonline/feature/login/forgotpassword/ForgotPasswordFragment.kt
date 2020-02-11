package com.hiep.cmnonline.feature.login.forgotpassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hiep.cmnonline.feature.BaseFragment
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.login.LoginContainerActivity
import com.hiep.cmnonline.feature.login.LoginFragment
import com.hiep.cmnonline.model.State
import kotlinx.android.synthetic.main.fragment_forgot_password.*

class ForgotPasswordFragment : BaseFragment() {
    lateinit var viewModel: ForgotPasswordViewModel
    private val loginActivity: LoginContainerActivity?
        get() = activity as? LoginContainerActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun initViews() {
        btnGetOTP.setOnClickListener(onClickListener)
        btnResetPassword.setOnClickListener(onClickListener)
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this)[ForgotPasswordViewModel::class.java]
        viewModel.otpLiveData.observe(this, Observer {
            val (state, otp, error) = it
            when (state) {
                State.LOADING -> {
                    btnGetOTP.isEnabled = false
                }
                State.SUCCEED -> {
                    edtOTP.setText(otp)
                    btnGetOTP.isEnabled = true
                }
                State.ERROR -> {
                    btnGetOTP.isEnabled = true
                }
                else -> {
                }
            }
        })

        viewModel.checkOtpLiveData.observe(this, Observer {
            val (state, data, error) = it
            when (state) {
                State.SUCCEED -> {
                    changePassword()
                }
                else -> {
                }
            }
        })
        viewModel.changePasswordLiveData.observe(this, Observer {
            if (it) {
                Toast.makeText(
                    activity,
                    R.string.forgot_password_change_password_success,
                    Toast.LENGTH_SHORT
                ).show()
                loginActivity?.apply {
                    removeCurrentFragment() // reset state
                    setFragment(LoginFragment())
                }

            }
        })
        viewModel.invalidInputLiveData.observe(this, Observer { invalidList ->
            if (invalidList.isNotEmpty()) {
                Toast.makeText(activity, invalidList[0].messageRes, Toast.LENGTH_SHORT).show()
                invalidList.forEach {
                    Log.i("ForgotPasswordFragment","invalid input: ${getString(it.messageRes)}")
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        loginActivity?.apply {
            setActionbarTitle(R.string.forgot_password_title)
            showBackButton()
        }
    }

    private val onClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.btnGetOTP -> {
                val phone: String = edtPhone.text.toString()
                viewModel.getOTP(phone)
            }
            R.id.btnResetPassword -> {
                if (viewModel.isVerifiedOTP) {
                    changePassword()
                } else {
                    checkOTP()
                }
            }
        }
    }

    private fun checkOTP() {
        val phone = edtPhone.text.toString()
        val otp = edtOTP.text.toString()
        viewModel.checkOTP(phone, otp)
    }

    private fun changePassword() {
        val newPassword = edtNewPassword.text.toString()
        val confirmNewPassword = edtConfirmNewPassword.text.toString()
        viewModel.changePassword(newPassword, confirmNewPassword)
    }

    override fun onBack() {
        activity?.setFragment(LoginFragment())
    }
}
