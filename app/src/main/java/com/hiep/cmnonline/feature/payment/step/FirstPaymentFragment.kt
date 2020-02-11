package com.hiep.cmnonline.feature.payment.step


import android.view.View
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.payment.BasePaymentFragment
import kotlinx.android.synthetic.main.fragment_payment.*


class FirstPaymentFragment : BasePaymentFragment() {


    override fun initViews() {
        super.initViews()
        imvPaymentState.setImageResource(R.drawable.ic_tick)
        tvPaymentState.text = "first fragment"
        paymentProgress = 2f
    }

    override fun onBack() {
        paymentActivity?.finish()
    }

    override fun onStart() {
        super.onStart()
        paymentActivity?.apply{
            setTextNextButton(R.string.next)
            hideHomeButton()
        }
    }
    override fun onNext() {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_UP)
            paymentActivity?.setFragment(
                SecondPaymentFragment(),
                allowAnimation = false
            )
        }
    }
}
