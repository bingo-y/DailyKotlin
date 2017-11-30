package com.bingo.dailykotlin.mvp.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bingo.dailykotlin.R
import com.bingo.dailykotlin.common.LoadNewsType
import com.bingo.dailykotlin.mvp.entity.NewsSummary
import com.bingo.dailykotlin.mvp.presenter.NewsListPresenter
import com.bingo.dailykotlin.mvp.ui.adapter.BaseRecyclerViewAdapter
import com.bingo.dailykotlin.mvp.ui.adapter.NewsListAdapter
import com.bingo.dailykotlin.mvp.view.INewsListView
import com.bingo.dailykotlin.mvp.view.base.IView
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

/**
 * @author bingo.
 * @date Create on 2017/11/28.
 * @Description
 */
class NewsListFragment: BaseFragment<NewsListPresenter>(), SwipeRefreshLayout.OnRefreshListener, IView, BaseRecyclerViewAdapter.OnItemClickListener, INewsListView {

    @Inject
    lateinit var newsListAdapter: NewsListAdapter
    @Inject
    lateinit var mNewsListPresenter: NewsListPresenter
    @Inject
    lateinit var activity: Activity

    private var mNewsId: String? = null
    private var mNewsType: String? = null

    private var mIsAllLoaded: Boolean = false



    companion object {
        val CHANNEL_KEY = "channel_key"
        val NEWS_ID = "NEWS_ID"
        val NEWS_TYPE = "NEWS_TYPE"

        fun getNewsListFragment(newsId: String, newsType: String): NewsListFragment {
            val newsListFragment = NewsListFragment()
            val bundle = Bundle()
            bundle.putString(NEWS_ID, newsId)
            bundle.putString(NEWS_TYPE, newsType)
            newsListFragment.arguments = bundle
            return newsListFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mNewsId = arguments?.getString(NEWS_ID)
        mNewsType = arguments?.getString(NEWS_TYPE)
    }


    override fun initInjector() {
        mFragmentComponent.inject(this)
    }

    override fun initViews(view: View) {
        initSwipeRefreshLayout()
        initRecyclerView()
        initPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    private fun initSwipeRefreshLayout() {
        swipe_refresh_layout.setOnRefreshListener(this)
        val colors = activity.resources.getIntArray(R.array.gplus_colors)
        swipe_refresh_layout.setColorSchemeColors(*colors)
    }

    private fun initPresenter() {
        mNewsListPresenter.setNewsTypeAndId(mNewsType, mNewsId)
        mPresenter = mNewsListPresenter
        mPresenter?.attachView(this)
        mPresenter?.onCreate()
    }

    private fun initRecyclerView() {
        news_rv.setHasFixedSize(true)
        news_rv.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL, false)
        //mNewsRv.setItemAnimator(new DefaultItemAnimator());
        news_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView?.layoutManager

                val lastVisibleItemPosition = (layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition()
                val visibleItemCount = layoutManager.getChildCount()
                val totalItemCount = layoutManager.getItemCount()

                if (!mIsAllLoaded && visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    mNewsListPresenter.loadMore()
                    newsListAdapter.showFooter()
                    news_rv.scrollToPosition(newsListAdapter.itemCount - 1)
                }
            }

        })

        newsListAdapter.setOnItemClickListener(this)
        news_rv.adapter = newsListAdapter
    }

    override fun onRefresh() {
        mNewsListPresenter.refreshData()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun showMsg(message: String?) {
        progress_bar.visibility = View.GONE
        Snackbar.make(news_rv, message ?: "", Snackbar.LENGTH_LONG).show()
    }

    override fun onItemClick(view: View?, position: Int) {

    }

    override fun setNewsList(newsSummary: List<NewsSummary>?, loadType: Long) {
        when (loadType) {
            LoadNewsType.TYPE_REFRESH_SUCCESS -> {
                swipe_refresh_layout.isRefreshing = false
                newsListAdapter.list = newsSummary
                newsListAdapter.notifyDataSetChanged()
                checkIsEmpty(newsSummary)
            }
            LoadNewsType.TYPE_REFRESH_ERROR -> {
                swipe_refresh_layout.setRefreshing(false)
                checkIsEmpty(newsSummary)
            }
            LoadNewsType.TYPE_LOAD_MORE_SUCCESS -> {
                newsListAdapter.hideFooter()
                if (newsSummary == null || newsSummary.isEmpty()) {
                    mIsAllLoaded = true
                    Snackbar.make(news_rv, "全部加载完成", Snackbar.LENGTH_SHORT).show()
                } else {
                    newsListAdapter.addMore(newsSummary)
                }
            }
            LoadNewsType.TYPE_LOAD_MORE_ERROR -> newsListAdapter.hideFooter()
        }
    }

    private fun checkIsEmpty(newsSummary: List<NewsSummary>?) {
        if (newsSummary == null && newsListAdapter.list == null) {
            news_rv.visibility = View.GONE
            empty_view.visibility = View.VISIBLE

        } else {
            news_rv.visibility = View.VISIBLE
            empty_view.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mNewsListPresenter.onDestroy()
    }

}