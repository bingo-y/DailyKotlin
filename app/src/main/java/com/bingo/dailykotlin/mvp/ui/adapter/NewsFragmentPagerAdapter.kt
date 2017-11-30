package com.bingo.dailykotlin.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * @author bingo.
 * @date Create on 2017/11/28.
 * @Description
 */
class NewsFragmentPagerAdapter(fm: FragmentManager, titles: List<String>, newsFragmentList: MutableList<Fragment>): FragmentPagerAdapter(fm) {

    private var mTitles: List<String>
    private val mNewsFragmentList: MutableList<Fragment>

    init {
        mTitles = titles
        mNewsFragmentList = newsFragmentList
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return mNewsFragmentList[position]
    }

    override fun getCount(): Int {
        return mNewsFragmentList.size
    }

    override fun getItemId(position: Int): Long {
        super.getItemId(position)
        when {
            position < mNewsFragmentList.size -> return mNewsFragmentList[position].hashCode().toLong()
            else -> return super.getItemId(position)
        }
    }

}
