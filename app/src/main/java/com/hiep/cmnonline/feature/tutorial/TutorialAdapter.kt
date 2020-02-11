package com.hiep.cmnonline.feature.tutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.hiep.cmnonline.R
import com.hiep.cmnonline.model.Tutorial


class TutorialAdapter(private val context: Context?) : PagerAdapter() {
    private val tutorialList = ArrayList<Tutorial>()
    private var _lastPosition: Int = 0

    val lastPosition: Int
        get() = _lastPosition

    override fun getCount(): Int {
        return tutorialList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (context == null)
            return 1
        val tutorial = tutorialList[position]
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_tutorial, null)
        val imvTutorial = view?.findViewById<ImageView>(R.id.imvTutorial)
        val tvTutorialTitle = view?.findViewById<TextView>(R.id.tvTutorialTitle)
        val tvTutorialDescription = view?.findViewById<TextView>(R.id.tvTutorialDescription)

        tvTutorialTitle?.setText(tutorial.titleRes)
        tvTutorialDescription?.setText(tutorial.descriptionRes)
        imvTutorial?.setImageResource(tutorial.imageRes)
        container.addView(view)
        return view ?: 1
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as? RelativeLayout)
    }

    fun setList(newList: List<Tutorial>) {
        tutorialList.clear()
        tutorialList.addAll(newList)
        _lastPosition = if (tutorialList.isNotEmpty())
            tutorialList.size - 1
        else
            0
        notifyDataSetChanged()
    }
}