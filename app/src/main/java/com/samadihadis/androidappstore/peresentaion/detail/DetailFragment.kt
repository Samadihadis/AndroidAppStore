package com.samadihadis.androidappstore.peresentaion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.databinding.FragmentDetailBinding
import com.samadihadis.androidappstore.peresentaion.adapters.screenshots.ScreenshotsAdapter
import com.samadihadis.androidappstore.util.Utils
import com.samadihadis.androidappstore.util.Utils.orEmpty
import com.samadihadis.androidappstore.util.Utils.orZero
import com.samadihadis.androidappstore.util.formatNumberDouble
import com.samadihadis.androidappstore.util.formatNumberFloat
import com.samadihadis.androidappstore.util.separatorNumbers

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var binding: FragmentDetailBinding
    private val screenshotsAdaptor by lazy {
        ScreenshotsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIconApp()
        setupInformationApp()
        setupRatingViews()
        setupScreenshotsAdapter()
        setupClickListener()
    }

    private fun setupIconApp() = with(binding) {
        Glide.with(requireContext())
            .load(args.appInfoModel.icon72)
            .placeholder(R.drawable.banner_image_placeholder)
            .into(detailPageIconImageView)
    }

    private fun setupClickListener() = with(binding) {
        detailPageAboutNextImageView.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionToAboutAppFragment(args.appInfoModel))
        }
        detailPageInstallButton.setOnClickListener {
            Utils.openMarket(requireContext(), args.appInfoModel.packageName.orEmpty())
        }
    }

    private fun setupInformationApp() = with(binding) {
        detailPageTitleTextView.text = args.appInfoModel.title
        detailPageTypeTextView.text = args.appInfoModel.category
        detailPageRatingOneTextView.text = args.appInfoModel.rating?.formatNumberFloat() + " " + "★"
        detailPageReviewsTextView.text =
            (args.appInfoModel.numberRatings?.toDouble().orZero() / 10000).toInt()
                .toString() + "k reviews"
        detailPageSizeTextView.text = (args.appInfoModel.size?.toDouble()
            .orZero() / 100000000).formatNumberDouble() + " " + "MB"
        detailPageDownloadValueTextView.text = args.appInfoModel.downloads
        detailPageAboutValueTextView.text = args.appInfoModel.description
    }

    private fun setupRatingViews() = with(binding) {

        detailPageRatingBar.rating = args.appInfoModel.rating.orZero()
        detailPageRatingTwoTextView.text = args.appInfoModel.rating?.formatNumberFloat()
        detailPageNumberOfRatingTextView.text =
            args.appInfoModel.numberRatings?.toInt()?.separatorNumbers()

        progressBarFive.progress = getRating(5)

        progressBarFour.progress = getRating(4)

        progressBarThree.progress = getRating(3)

        progressBarTow.progress = getRating(2)

        progressBarOne.progress = getRating(1)

    }

    private fun getRating(index: Int): Int {
        val totalRating = args.appInfoModel.numberRatings?.toDouble().orZero()

        val rating = when (index) {
            1 -> args.appInfoModel.ratings1
            2 -> args.appInfoModel.ratings2
            3 -> args.appInfoModel.ratings3
            4 -> args.appInfoModel.ratings4
            5 -> args.appInfoModel.ratings5
            else -> 0
        }

        return ((rating?.toDouble().orZero() / totalRating) * 100).toInt()
    }

    private fun setupScreenshotsAdapter() {
        with(binding.detailPageScreenshotsRecycleView) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                androidx.recyclerview.widget.RecyclerView.HORIZONTAL,
                false
            )
            adapter = screenshotsAdaptor
        }
        args.appInfoModel.screenshots?.let {
            screenshotsAdaptor.addItemList(it)
        }
    }

}