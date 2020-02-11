package com.hiep.cmnonline.feature.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ProgressBar
import com.hiep.cmnonline.feature.BaseFragment
import com.hiep.cmnonline.R
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlinx.android.synthetic.main.layout_payment_progress.*

abstract class BasePaymentFragment : BaseFragment() {
    val paymentActivity: PaymentContainerActivity?
        get() = activity as? PaymentContainerActivity
    private var _paymentProgress = 0f
    private var maxProgress = 0f
    private val progressBarList = ArrayList<ProgressBar>()

    var paymentProgress: Float
        get() = _paymentProgress
        set(value) {
            var validValue = value
            if (validValue > maxProgress)
                validValue = maxProgress
            for (i in 1..progressBarList.size) {
                val progress = if (validValue >= i)
                    100
                else
                    ((validValue - i + 1) * 100).toInt()
                progressBarList[i - 1].progress = progress
            }
            _paymentProgress = validValue
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun initViews() {
        scrollView.viewTreeObserver.addOnScrollChangedListener(onScrollChangedListener)
        progressBarList.apply {
            add(progressBarFirst)
            add(progressBarSecond)
            add(progressBarLast)
        }
        maxProgress = progressBarList.size.toFloat()
    }

    private val onScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
        val atBottom = scrollView.getChildAt(0).bottom <= scrollView.height + scrollView.scrollY
        if (atBottom) {
            if (paymentActivity?.showButtonNext() == true)
                scrollView.post {
                    scrollView.fullScroll(View.FOCUS_DOWN)
                }
        }
    }

    abstract fun onNext()
}