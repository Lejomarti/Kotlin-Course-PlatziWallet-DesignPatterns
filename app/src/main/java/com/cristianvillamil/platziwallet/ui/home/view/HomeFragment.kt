package com.cristianvillamil.platziwallet.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cristianvillamil.platziwallet.R
import com.cristianvillamil.platziwallet.databinding.FragmentHomeBinding
import com.cristianvillamil.platziwallet.ui.home.FavoriteTransfer
import com.cristianvillamil.platziwallet.ui.home.HomeContract
import com.cristianvillamil.platziwallet.ui.home.presenter.HomePresenter
import com.squareup.picasso.Picasso

class HomeFragment : Fragment(),HomeContract.View {

    private val favoriteTransferAdapter = FavoriteTransferAdapter()
    private lateinit var binding: FragmentHomeBinding
    private var homePresenter:HomeContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        homePresenter = HomePresenter(this)
        homePresenter?.retrieveFavoriteTransfers()
        binding.circularProgress.setProgressWithAnimation(
            70f,
            1000,
            AccelerateDecelerateInterpolator(),
            500
        )
        Picasso
            .get()
            .load("https://media.licdn.com/dms/image/C4E03AQFcCuDIJl0mKg/profile-displayphoto-shrink_200_200/0?e=1583366400&v=beta&t=ymt3xgMe5bKS-2knNDL9mQYFksP9ZHne5ugIqEyRjZs")
            .into(binding.profilePhotoImageView)
    }

    private fun initRecyclerView() {
        binding.favoriteTransfersRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.favoriteTransfersRecyclerView.adapter = favoriteTransferAdapter
        //        favoriteTransferAdapter.setData(items)

    }

    override fun showLoader() {
        binding.homeLoader.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding.homeLoader.visibility = View.GONE
    }

    override fun showFavoriteTransfers(favoriteTransfer: List<FavoriteTransfer>) {
        favoriteTransferAdapter.setData(favoriteTransfer)
    }
}