package com.bingo.dailykotlin.mvp.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.bingo.dailykotlin.R
import com.bingo.dailykotlin.mvp.entity.NewsChannel
import com.bingo.dailykotlin.mvp.presenter.NewsPresenter
import com.bingo.dailykotlin.mvp.ui.adapter.NewsFragmentPagerAdapter
import com.bingo.dailykotlin.mvp.ui.base.BaseActivity
import com.bingo.dailykotlin.mvp.ui.fragment.BaseFragment
import com.bingo.dailykotlin.mvp.ui.fragment.NewsListFragment
import com.bingo.dailykotlin.mvp.view.INewsView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<NewsPresenter>(), INewsView {

    val mNewsFragmentList: MutableList<Fragment> = ArrayList()
    var mChannelNames: List<String>? = null
    var mCurrentViewPagerName: String? = null

    @Inject
    lateinit var newsPresenter: NewsPresenter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initInjector(): Unit {
        mActivityComponent.inject(this)
    }

    override fun initViews() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        mPresenter = newsPresenter
        newsPresenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMsg(message: String?) {
        message?.let {
            Snackbar.make(fab, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun initViewPager(newsChannels: List<NewsChannel>) {
        val channelNames = ArrayList<String>()
        setNewsList(newsChannels, channelNames)
        setViewPager(channelNames)
    }

    private fun setNewsList(newsChannels: List<NewsChannel>, channelNames: MutableList<String>) {
        mNewsFragmentList.clear()
        for (newsChannel in newsChannels) {
            val newsListFragment = NewsListFragment.getNewsListFragment(newsChannel.newsChannelId, newsChannel.newsChannelType)
            mNewsFragmentList.add(newsListFragment)
            channelNames.add(newsChannel.newsChannelName)
        }
    }

    private fun setViewPager(channelNames: List<String>) {
        val adapter = NewsFragmentPagerAdapter(
                supportFragmentManager, channelNames, mNewsFragmentList)
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)
        tabs.tabMode = TabLayout.MODE_SCROLLABLE
        //        mTabs.setTabsFromPagerAdapter(adapter);
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mCurrentViewPagerName = mChannelNames?.get(position)
            }
        })



        mChannelNames = channelNames
        val currentViewPagerPosition = getCurrentViewPagerPosition()
        view_pager.setCurrentItem(currentViewPagerPosition, false)
    }

    private fun getCurrentViewPagerPosition(): Int {
        var position = 0
        if (mCurrentViewPagerName != null) {
            for (i in mChannelNames!!.indices) {
                if (mCurrentViewPagerName == mChannelNames?.get(i)) {
                    position = i
                }
            }
        }
        return position
    }
}
