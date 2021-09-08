package com.wasambo.news.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wasambo.news.databinding.FragmentHomeBinding
import com.wasambo.news.ui.adapter.ArticlesAdapter
import com.wasambo.news.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, ArticleViewModel>() {
    override val viewModel: ArticleViewModel by viewModel()
    private var listAdapter = ArticlesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = tryFindNavController(view)
        binding.newsListRecyclerView.apply {
            listAdapter = ArticlesAdapter()
            adapter = listAdapter
            setHasFixedSize(true)
        }

        listAdapter.onItemClick = { article ->
            val action = HomeFragmentDirections.toArticleDetail(article)
            navController?.navigate(action)
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.vmGetArticleList()
        viewModel.articleList.observe(
            viewLifecycleOwner,
            { listAdapter.setNewsData(it) }
        )
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)
}
