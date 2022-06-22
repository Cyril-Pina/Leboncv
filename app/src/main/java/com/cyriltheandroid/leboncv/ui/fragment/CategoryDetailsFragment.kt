package com.cyriltheandroid.leboncv.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.cyriltheandroid.leboncv.data.model.Article
import com.cyriltheandroid.leboncv.databinding.FragmentCategoryDetailsBinding
import com.cyriltheandroid.leboncv.ui.adapter.ArticleAdapter
import com.cyriltheandroid.leboncv.ui.viewmodel.ArticleViewModel
import com.cyriltheandroid.leboncv.utils.setCloseFragmentOnClick
import com.cyriltheandroid.leboncv.utils.startFavouriteAnimationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CategoryDetailsFragment : Fragment() {

    private val articleViewModel: ArticleViewModel by viewModels()
    private lateinit var binding: FragmentCategoryDetailsBinding

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDetailsBinding.inflate(inflater, container, false)
        binding.categoryType = articleViewModel.categoryType
        setCloseFragmentOnClick(binding.backArrowImageButton)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
        requestSearchEditTextFocus()
    }

    private fun requestSearchEditTextFocus() {
        binding.searchEditText.requestFocus()
        val manager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun initObservers() {
        articleViewModel.articles.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                initArticlesResultRecyclerView(it)
                updateResultsRecyclerViewVisibility(isVisible = true)
            } else {
                updateResultsRecyclerViewVisibility(isVisible = false)
            }
        }
    }

    private fun initClickListeners() {
        binding.searchCardView.setOnClickListener {
            binding.searchEditText.requestFocus()
        }
        binding.searchEditText.addTextChangedListener {
            val text = it.toString()
            articleViewModel.getArticlesOfCategory(text)
        }
    }

    private fun initArticlesResultRecyclerView(articles: List<Article>) {
        articleAdapter.articles = articles
        articleAdapter.clickListener.onItemClick = { _, obj ->
            val article = obj as Article
            navigateToArticleDetails(article)
        }
        articleAdapter.favClickListener.onItemClick = { _, view, article ->
            val animationView = view as LottieAnimationView
            startFavouriteAnimationView(article.isFav, animationView)
            article.isFav = !article.isFav
            articleViewModel.updateFavArticle(article.id, article.isFav)
        }
        binding.articlesRecyclerView.adapter = articleAdapter
    }

    private fun updateResultsRecyclerViewVisibility(isVisible: Boolean) {
        binding.articlesRecyclerView.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        binding.noResultLayout.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    private fun navigateToArticleDetails(article: Article) {
        val action =
            CategoryDetailsFragmentDirections.actionCategoryDetailsFragmentToArticleFragment(article)
        val navController = findNavController()
        navController.navigate(action)
    }
}