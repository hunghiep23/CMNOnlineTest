package com.hiep.cmnonline.feature.payment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.hiep.cmnonline.feature.BaseActivity
import com.hiep.cmnonline.feature.BaseFragment
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.payment.step.FirstPaymentFragment
import kotlinx.android.synthetic.main.activity_payment_container.*
import kotlinx.android.synthetic.main.layout_actionbar.*

class PaymentContainerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_container)
    }

    override fun initViews() {
        actionbarBack.setOnClickListener(onClickListener)
        btnNext.setOnClickListener(onClickListener)
        btnBackToHome.setOnClickListener(onClickListener)
        tvTitle.setText(R.string.payment_title)
        setFragment(FirstPaymentFragment(), allowAnimation = false)
    }

    override fun setFragment(fragment: BaseFragment, isForward: Boolean, allowAnimation: Boolean) {
        setFragment(fragment, R.id.flPaymentContainer, isForward, allowAnimation)
    }

    private val onClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.actionbarBack -> {
                onBackPressed()
            }
            R.id.btnNext -> {
                (currentFragment as? BasePaymentFragment)?.onNext()
            }
            R.id.btnBackToHome -> {
                finish()
            }
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBack()
    }

    fun showButtonNext(): Boolean {
        if (btnNext.visibility != View.VISIBLE) {
            btnNext.visibility = View.VISIBLE
            return true
        }
        return false
    }

    fun hideHomeButton() {
        if (btnBackToHome.visibility != View.GONE)
            btnBackToHome.visibility = View.GONE
    }

    fun showHomeButton() {
        if (btnBackToHome.visibility != View.VISIBLE)
            btnBackToHome.visibility = View.VISIBLE
    }

    fun setTextNextButton(@StringRes textRes: Int) {
        btnNext.setText(textRes)
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, PaymentContainerActivity::class.java)
            context.startActivity(intent)
        }
    }
}
