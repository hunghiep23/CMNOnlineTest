package com.hiep.cmnonline.feature

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hiep.cmnonline.R


abstract class BaseActivity : AppCompatActivity() {
    protected var currentFragment: BaseFragment? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initViews()
        initViewModel()
    }

    protected abstract fun initViews()
    protected open fun initViewModel() {}

    open fun setFragment(
        fragment: BaseFragment,
        isForward: Boolean = false,
        allowAnimation: Boolean = true
    ) {
        //please call setFragment(fragment, R.id.container, isForward) in activity child
    }

    protected fun setFragment(
        fragment: BaseFragment, @IdRes container: Int,
        isForward: Boolean = false,
        allowAnimation: Boolean = true
    ) {
        val tag = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        if (allowAnimation && currentFragment != null) {
            if (isForward)
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_right_to_center,
                    R.anim.slide_center_to_left
                )
            else
                fragmentTransaction.setCustomAnimations(
                    R.anim.slide_left_to_center,
                    R.anim.slide_center_to_right
                )
        }
        if (currentFragment?.isAdded == true) {
            fragmentTransaction.hide(currentFragment as Fragment)
        }
        val existFragment = manager.findFragmentByTag(tag)
        existFragment?.apply {
            this.arguments = fragment.arguments ?: this.arguments //update new params
            currentFragment = this as? BaseFragment
            fragmentTransaction.show(this)
        } ?: let {
            currentFragment = fragment
            fragmentTransaction.add(container, fragment, tag)
        }
        fragmentTransaction.commit()
    }

    fun removeCurrentFragment() {
        val manager = supportFragmentManager
        currentFragment?.let { manager.beginTransaction().remove(it).commit() }
    }
}