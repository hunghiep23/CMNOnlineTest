package com.hiep.cmnonline.feature.main.payment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.main.BaseMainFragment
import com.hiep.cmnonline.feature.payment.PaymentContainerActivity
import kotlinx.android.synthetic.main.fragment_trade.*

/**
 * A simple [Fragment] subclass.
 */
class TradeFragment : BaseMainFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trade, container, false)
    }

    override fun initViews() {
        btnPay.setOnClickListener {
            activity?.let {
                PaymentContainerActivity.start(it)
            }
        }
    }

    override fun onBack() {
        mainActivity?.setPage(0)
    }
}
