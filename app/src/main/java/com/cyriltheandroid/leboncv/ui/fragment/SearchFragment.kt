package com.cyriltheandroid.leboncv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.cyriltheandroid.leboncv.ui.adapter.CategoryHistoryAdapter
import com.cyriltheandroid.leboncv.ui.adapter.TopCategoryAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentSearchBinding
import com.cyriltheandroid.leboncv.data.model.Article
import com.cyriltheandroid.leboncv.data.model.Category
import com.cyriltheandroid.leboncv.data.model.CategoryType
import com.cyriltheandroid.leboncv.ui.viewmodel.ArticleViewModel
import com.cyriltheandroid.leboncv.ui.viewmodel.CategoryViewModel
import com.cyriltheandroid.leboncv.utils.startFavouriteAnimationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by activityViewModels()
    private val articleViewModel: ArticleViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var topCategoriesAdapter: TopCategoryAdapter

    @Inject
    lateinit var categoryHistoryAdapter: CategoryHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateHistoryArticlesRecyclerView(isVisible = false)
        initObservers()
        initClickListeners()
        initTopCategoriesRecyclerView()
        articleViewModel.getAllArticles()
    }

    private fun initClickListeners() {
        binding.searchCardView.setOnClickListener {
            navigateToCategoryDetails(CategoryType.NO_CATEGORY)
        }
    }

    private fun initObservers() {
        articleViewModel.allArticles.observe(viewLifecycleOwner) {
            updateHistoryArticlesRecyclerView(isVisible = true)
            categoryHistoryAdapter.allArticles = it
            categoryHistoryAdapter.categories = categoryViewModel.categories
            initItemsClickListeners()
            binding.categoryHistoryRecyclerView.adapter = categoryHistoryAdapter
        }
    }

    private fun updateHistoryArticlesRecyclerView(isVisible: Boolean) {
        binding.articlesHistoryLayout.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        binding.allArticlesLoadingProgressBar.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    private fun initItemsClickListeners() {
        categoryHistoryAdapter.categoryClickListener.onItemClick = { _, obj ->
            val category = obj as Category
            navigateToCategoryDetails(category.type)
        }
        categoryHistoryAdapter.articlesClickListener.onItemClick = { _, obj ->
            val article = obj as Article
            navigateToArticleDetails(article)
        }
        categoryHistoryAdapter.favClickListener.onItemClick = { _, view, article ->
            val animationView = view as LottieAnimationView
            startFavouriteAnimationView(article.isFav, animationView)
            article.isFav = !article.isFav
            articleViewModel.updateFavArticle(article.id, article.isFav)
        }
    }

    private fun initTopCategoriesRecyclerView() {
        topCategoriesAdapter.topCategories = categoryViewModel.categories
        topCategoriesAdapter.clickListener.onItemClick = { _, obj ->
            val topCategory = obj as Category
            navigateToCategoryDetails(topCategory.type)
        }
        binding.topCategoriesRecyclerView.adapter = topCategoriesAdapter
    }

    private fun navigateToCategoryDetails(type: CategoryType) {
        val action = SearchFragmentDirections.actionSearchFragmentToCategoryDetailsFragment(type)
        val navController = findNavController()
        navController.navigate(action)
    }

    private fun navigateToArticleDetails(article: Article) {
        val action = SearchFragmentDirections.actionSearchFragmentToArticleFragment(article)
        val navController = findNavController()
        navController.navigate(action)
    }
}