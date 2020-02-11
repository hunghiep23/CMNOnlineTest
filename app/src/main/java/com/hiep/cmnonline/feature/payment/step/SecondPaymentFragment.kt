package com.hiep.cmnonline.feature.payment.step


import android.view.View
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.payment.BasePaymentFragment
import kotlinx.android.synthetic.main.fragment_payment.*


class SecondPaymentFragment : BasePaymentFragment() {


    override fun initViews() {
        super.initViews()
        imvPaymentState.setImageResource(R.drawable.ic_refresh)
        tvPaymentState.text = "second fragment"
        paymentProgress = 2.75f
    }

    override fun onStart() {
        super.onStart()
        paymentActivity?.apply {
            setTextNextButton(R.string.next)
            showHomeButton()
        }
    }

    override fun onBack() {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_UP)
            paymentActivity?.setFragment(FirstPaymentFragment(), allowAnimation = false)
        }
    }

    override fun onNext() {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_UP)
            paymentActivity?.setFragment(LastPaymentFragment(), allowAnimation = false)
        }
    }
}
