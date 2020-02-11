package com.hiep.cmnonline.feature.tutorial

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.hiep.cmnonline.feature.BaseActivity
import com.hiep.cmnonline.R
import com.hiep.cmnonline.feature.login.LoginContainerActivity
import com.hiep.cmnonline.feature.main.MainActivity
import com.hiep.cmnonline.helper.SharedPreferencesHelper
import com.hiep.cmnonline.model.Tutorial
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : BaseActivity() {
    private val tutorialAdapter =
        TutorialAdapter(this)
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val sharedPref = SharedPreferencesHelper.getInstance(applicationContext)
        if (sharedPref.logged) {
            MainActivity.start(this)
            finish()
            return
        }
        if (sharedPref.skipTutorial) {
            LoginContainerActivity.start(this)
            finish()
            return
        }
        tutorialAdapter.setList(getTutorials())
    }

    override fun initViews() {
        vpTutorialContainer.addOnPageChangeListener(onPageChangeListener)
        btnSkip.setOnClickListener(onClickListener)
        btnNext.setOnClickListener(onClickListener)
        btnBegin.setOnClickListener(onClickListener)
        vpTutorialContainer.adapter = tutorialAdapter
    }

    private val onPageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            if (position == tutorialAdapter.lastPosition) {
                llNavigation.visibility = View.GONE
                btnBegin.visibility = View.VISIBLE
            } else {
                llNavigation.visibility = View.VISIBLE
                btnBegin.visibility = View.GONE
            }
            currentPage = position
        }
    }

    private val onClickListener = View.OnClickListener {
        when (it?.id) {
            R.id.btnSkip -> vpTutorialContainer.currentItem = tutorialAdapter.lastPosition
            R.id.btnNext -> {
                if (currentPage < tutorialAdapter.lastPosition)
                    vpTutorialContainer.currentItem = currentPage + 1
            }
            R.id.btnBegin -> {
                SharedPreferencesHelper.getInstance(applicationContext).skipTutorial = true
                LoginContainerActivity.start(this)
                finish()
            }
        }
    }


    private fun getTutorials() = ArrayList<Tutorial>().apply {
        add(
            Tutorial(
                R.drawable.ic_coupon,
                R.string.tutorial_first_title,
                R.string.tutorial_first_description
            )
        )
        add(
            Tutorial(
                R.drawable.ic_pizza,
                R.string.tutorial_second_title,
                R.string.tutorial_second_description
            )
        )
        add(
            Tutorial(
                R.drawable.ic_bill,
                R.string.tutorial_last_title,
                R.string.tutorial_last_description
            )
        )
    }
}
