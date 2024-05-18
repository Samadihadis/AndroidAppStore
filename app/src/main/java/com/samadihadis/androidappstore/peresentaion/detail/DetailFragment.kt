package com.samadihadis.androidappstore.peresentaion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var binding: FragmentDetailBinding
    private var onInstallClicked: ((String) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Glide.with(requireContext())
                .load(args.appInfoModel.icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .into(detailPageIconImageView)
            detailPageTitleTextView.text = args.appInfoModel.title
            detailPageTypeTextView.text = args.appInfoModel.category
            detailPageReviewsTextView.text = args.appInfoModel.rating.toString()
            detailPageSizeTextView.text = args.appInfoModel.size.toString()
            detailPageDownloadValueTextView.text = args.appInfoModel.downloads
            detailPageInstallButton.setOnClickListener{
                onInstallClicked?.invoke(args.appInfoModel.packageName)
            }
            detailPageAboutValueTextView.text = args.appInfoModel.description
            detailPageSafetyValueTextView.text = args.appInfoModel.privacyPolicy
            detailPageRatingTwoTextView.text = args.appInfoModel.rating.toString()
            detailPageRatingBar.rating
            progressBarOne.progress = args.appInfoModel.ratings1.toInt()
            progressBarTwo.progress = args.appInfoModel.ratings2.toInt()
            progressBarThree.progress = args.appInfoModel.ratings3.toInt()
            progressBarFour.progress = args.appInfoModel.ratings4.toInt()
            progressBarFive.progress = args.appInfoModel.ratings5.toInt()
            detailPageNumberOfRatingTextView.text = args.appInfoModel.numberRatings.toString()
        }
    }
}