package com.hiep.cmnonline.feature.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.hiep.cmnonline.feature.BaseActivity
import com.hiep.cmnonline.feature.BaseFragment
import com.hiep.cmnonline.R
import kotlinx.android.synthetic.main.layout_actionbar.*

class LoginContainerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_container)
    }

    override fun initViews() {
        actionbarBack.setOnClickListener(onClickListener)
        setFragment(LoginFragment())
    }

    override fun setFragment(fragment: BaseFragment, isForward: Boolean, allowAnimation: Boolean) {
        setFragment(fragment, R.id.flLoginContainer, isForward, allowAnimation)
    }

    private val onClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.actionbarBack -> onBackPressed()
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBack()
    }

    fun setActionbarTitle(@StringRes titleRes: Int) {
        tvTitle.setText(titleRes)
    }

    fun showBackButton() {
        if (actionbarBack.visibility != View.VISIBLE)
            actionbarBack.visibility = View.VISIBLE
    }

    fun hideBackButton() {
        if (actionbarBack.visibility != View.GONE)
            actionbarBack.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, LoginContainerActivity::class.java)
            context.startActivity(intent)
        }
    }
}
