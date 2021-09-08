package com.wasambo.news.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.wasambo.news.databinding.FragmentArticleDetailBinding
import com.wasambo.news.ui.base.BaseFragment
import com.wasambo.news.ui.home.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleDetailFragment : BaseFragment<FragmentArticleDetailBinding, ArticleViewModel>() {
    private val args: ArticleDetailFragmentArgs by navArgs()
    override val viewModel: ArticleViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        binding.article = article
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArticleDetailBinding.inflate(inflater, container, false)
}
