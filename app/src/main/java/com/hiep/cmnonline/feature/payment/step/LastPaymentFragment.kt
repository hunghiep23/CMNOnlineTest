package com.hiep.cmnonline.feature.payment.step


import android.view.View
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.payment.BasePaymentFragment
import kotlinx.android.synthetic.main.fragment_payment.*


class LastPaymentFragment : BasePaymentFragment() {


    override fun initViews() {
        super.initViews()
        imvPaymentState.setImageResource(R.drawable.ic_tick)
        tvPaymentState.text = "last fragment"
        paymentProgress = 3f
    }
    override fun onStart() {
        super.onStart()
        paymentActivity?.apply{
            setTextNextButton(R.string.payment_finish)
            hideHomeButton()
        }
    }
    override fun onBack() {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_UP)
            paymentActivity?.setFragment(SecondPaymentFragment(), allowAnimation = false)
        }
    }

    override fun onNext() {
        paymentActivity?.finish()
    }
}
