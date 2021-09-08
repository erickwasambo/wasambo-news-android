package com.wasambo.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wasambo.news.BR
import com.wasambo.news.R
import com.wasambo.news.api.models.articles.Article
import com.wasambo.news.databinding.ArticleListContentBinding
import de.hdodenhof.circleimageview.CircleImageView

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    var onItemClick: ((Article) -> Unit)? = null
    private val newsList: MutableList<Article> = mutableListOf()

    fun setNewsData(news: List<Article>?) {
        newsList.clear()
        news?.let {
            newsList.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ArticleListContentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.article_list_content, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newsList[position]
        holder.bind(item)

        item.media?.first {
            it.type.equals("image")
        }?.mediaList?.first {
            it.format.equals("Standard Thumbnail")
        }?.url?.apply {
            Glide.with(holder.itemView.context).load(this).into(holder.newsIcon)
        }
    }

    override fun getItemCount() = newsList.size

    inner class ViewHolder(val binding: ArticleListContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val newsIcon: CircleImageView = binding.newsIcon

        fun bind(data: Article) {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
            binding.setVariable(BR.newsData, data)
            binding.executePendingBindings()
        }
    }
}
