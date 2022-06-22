package com.cyriltheandroid.leboncv.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.cyriltheandroid.leboncv.R
import com.cyriltheandroid.leboncv.ui.adapter.ImagesSlidePagerAdapter
import com.cyriltheandroid.leboncv.ui.adapter.SmallArticleAdapter
import com.cyriltheandroid.leboncv.databinding.FragmentArticleBinding
import com.cyriltheandroid.leboncv.data.model.Article
import com.cyriltheandroid.leboncv.data.model.ArticleImage
import com.cyriltheandroid.leboncv.ui.viewmodel.ArticleViewModel
import com.cyriltheandroid.leboncv.utils.disableGesture
import com.cyriltheandroid.leboncv.utils.setCloseFragmentOnClick
import com.cyriltheandroid.leboncv.utils.startFavouriteAnimationView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


const val DEFAULT_MAP_ZOOM = 13.0f

@AndroidEntryPoint
class ArticleFragment : Fragment(), OnMapReadyCallback {

    private val articleViewModel: ArticleViewModel by viewModels()
    private lateinit var binding: FragmentArticleBinding

    private var locationMap: GoogleMap? = null

    @Inject
    lateinit var imagesSlidePagerAdapter: ImagesSlidePagerAdapter

    @Inject
    lateinit var smallArticleAdapter: SmallArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        binding.viewModel = articleViewModel
        initLocationMap()
        setCloseFragmentOnClick(binding.backArrowImageButton)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        articleViewModel.article.observe(viewLifecycleOwner) {
            it.location?.latLng?.let { latLng ->
                addMarkerOnLocationMap(latLng)
                moveCameraToLocationLatLng(latLng)
            }
            initImagesSlidePager(it.images)
        }
        articleViewModel.articles.observe(viewLifecycleOwner) {
            smallArticleAdapter.articles = it
            smallArticleAdapter.clickListener.onItemClick = { _, obj ->
                val article = obj as Article
                navigateToArticleDetails(article)
            }
            smallArticleAdapter.favClickListener.onItemClick = { _, view, article ->
                val animationView = view as LottieAnimationView
                startFavouriteAnimationView(article.isFav, animationView)
                article.isFav = !article.isFav
                articleViewModel.updateFavArticle(article.id, article.isFav)
            }
            binding.linkedArticlesRecyclerView.adapter = smallArticleAdapter
        }
    }

    private fun initImagesSlidePager(images: List<ArticleImage>) {
        imagesSlidePagerAdapter.images = images
        binding.imagesViewPager.adapter = imagesSlidePagerAdapter
        binding.imagesViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val currentPageIndex = position + 1
                val totalPages = images.size
                binding.imagesLayout.nbImages = "$currentPageIndex/$totalPages"
            }
        })
    }

    private fun navigateToArticleDetails(article: Article) {
        val action =
            ArticleFragmentDirections.actionArticleFragmentSelf(article)
        val navController = findNavController()
        navController.navigate(action)
    }

    private fun initLocationMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.small_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun moveCameraToLocationLatLng(latLng: LatLng) {
        locationMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_MAP_ZOOM))
    }

    private fun addMarkerOnLocationMap(latLng: LatLng) {
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)

        locationMap?.clear()
        locationMap?.addMarker(markerOptions)
    }

    private fun showGoogleMapsApp(latLng: LatLng) {
        val googleMapUri = Uri.parse("geo:${latLng.latitude},${latLng.longitude}")

        val mapIntent = Intent(Intent.ACTION_VIEW, googleMapUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context?.packageManager?.let {
            mapIntent.resolveActivity(it)?.let {
                startActivity(mapIntent)
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        map.disableGesture()
        map.setOnMapClickListener {
            showGoogleMapsApp(it)
        }
        locationMap = map
        articleViewModel.article.value?.location?.latLng?.let {
            addMarkerOnLocationMap(it)
            moveCameraToLocationLatLng(it)
        }
    }
}