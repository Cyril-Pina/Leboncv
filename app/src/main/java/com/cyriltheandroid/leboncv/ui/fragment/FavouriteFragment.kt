package com.cyriltheandroid.leboncv.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.ui.adapter.FavouriteAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentFavouriteBinding
import com.cyriltheandroid.leboncv.data.model.Article
import com.cyriltheandroid.leboncv.ui.viewmodel.ArticleViewModel
import com.cyriltheandroid.leboncv.utils.addDivider
import com.cyriltheandroid.leboncv.utils.startFavouriteAnimationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private val articleViewModel: ArticleViewModel by viewModels()
    private lateinit var binding: FragmentFavouriteBinding

    @Inject
    lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initObservers() {
        articleViewModel.favArticles.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                initFavouritesRecyclerView(it)
                updateFavRecyclerViewVisibility(isVisible = true)
            } else {
                updateFavRecyclerViewVisibility(isVisible = false)
            }
        }
    }

    private fun initClickListeners() {
        binding.deleteFavArticlesFab.setOnClickListener {
            showDeleteArticleAlertDialog()
        }
    }

    private fun initFavouritesRecyclerView(favArticles: List<Article>) {
        favouriteAdapter.articles = favArticles
        favouriteAdapter.clickListener.onItemClick = { _, obj ->
            val article = obj as Article
            navigateToArticleDetails(article)
        }
        favouriteAdapter.favClickListener.onItemClick = { _, view, article ->
            val animationView = view as LottieAnimationView
            startFavouriteAnimationView(article.isFav, animationView)
            article.isFav = !article.isFav
            articleViewModel.updateFavArticle(article.id, article.isFav)
        }
        binding.favouritesRecyclerView.addDivider()
        binding.favouritesRecyclerView.adapter = favouriteAdapter
    }

    private fun updateFavRecyclerViewVisibility(isVisible: Boolean) {
        binding.favouritesMainLayout.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
        binding.noFavouriteLayout.visibility = if (isVisible) View.GONE else View.VISIBLE
    }

    private fun showDeleteArticleAlertDialog() {
        val builder = AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_trash_can)
            .setTitle(R.string.del_fav_title_a_d)
            .setMessage(R.string.del_fav_msg_a_d)
            .setPositiveButton(R.string.confirm_delete) { _, _ ->
                articleViewModel.deleteAllFavArticles()
            }
            .setNegativeButton(R.string.cancel) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun navigateToArticleDetails(article: Article) {
        val action = FavouriteFragmentDirections.actionFavouritePageToArticleFragment(article)
        val navController = findNavController()
        navController.navigate(action)
    }
}