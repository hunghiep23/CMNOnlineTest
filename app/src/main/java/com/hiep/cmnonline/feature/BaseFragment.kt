package com.hiep.cmnonline.feature

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hiep.cmnonline.R

abstract class BaseFragment : Fragment() {
    private var pressBack = false
    val activity: BaseActivity?
        get() = getActivity() as? BaseActivity

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initViewModel()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            onPause()
            onStop()
        } else {
            onStart()
            onResume()
        }
    }

    protected fun backToExit() {
        if (pressBack)
            activity?.finish()
        else {
            pressBack = true
            Toast.makeText(activity,
                R.string.press_back_again_to_exit, Toast.LENGTH_SHORT)
                .show()
            Handler().postDelayed({ pressBack = false }, 1000)
        }
    }

    protected abstract fun initViews()
    protected open fun initViewModel() {}
    abstract fun onBack()
}