package com.samadihadis.androidappstore.peresentaion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.data.AppListResponseModel
import com.samadihadis.androidappstore.databinding.FragmentDetailBinding
import com.samadihadis.androidappstore.peresentaion.adapters.screenshots.ScreenshotsAdapter
import com.samadihadis.androidappstore.util.RetrofitClient
import com.samadihadis.androidappstore.util.SharePreferencesManager
import com.samadihadis.androidappstore.util.formatNumber
import retrofit2.Call
import retrofit2.Response

class DetailFragment: Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var binding: FragmentDetailBinding
    private var onInstallClicked: ((String) -> Unit)? = null
    private val screenshotsAdaptor by lazy {
        ScreenshotsAdapter()
    }
    private val storage by lazy {
        SharePreferencesManager(requireContext())
    }
    private var screenshotsList = listOf<AppInfoModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupScreenshotsAdapter()
        getDataScreenshots()

        binding.apply {
            Glide.with(requireContext())
                .load(args.appInfoModel.icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .into(detailPageIconImageView)
            detailPageTitleTextView.text = args.appInfoModel.title
            detailPageTypeTextView.text = args.appInfoModel.category
            detailPageReviewsTextView.text = args.appInfoModel.rating.formatNumber() + " "
            detailPageSizeTextView.text = (args.appInfoModel.size/10000).toString()+ " " + "M"
            detailPageDownloadValueTextView.text = args.appInfoModel.downloads
            detailPageInstallButton.setOnClickListener{
                onInstallClicked?.invoke(args.appInfoModel.packageName)
            }
            detailPageAboutValueTextView.text = args.appInfoModel.description
            detailPageRatingTwoTextView.text = args.appInfoModel.rating.formatNumber() + " "
            detailPageRatingBar.rating
            progressBarOne.progress = args.appInfoModel.ratings1.toInt()
            progressBarTwo.progress = args.appInfoModel.ratings2.toInt()
            progressBarThree.progress = args.appInfoModel.ratings3.toInt()
            progressBarFour.progress = args.appInfoModel.ratings4.toInt()
            progressBarFive.progress = args.appInfoModel.ratings5.toInt()
            detailPageNumberOfRatingTextView.text = args.appInfoModel.numberRatings.toString()
        }
    }

    private fun setupScreenshotsAdapter() {
        with(binding.detailPageScreenshotsRecycleView) {
            layoutManager = LinearLayoutManager(requireContext(), androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false)
            adapter = screenshotsAdaptor
        }
    }

    private fun getDataScreenshots() {
        val applicationCatKeyResponseJsonFormatString = storage.retrieveString(args.appInfoModel.catKey)
        if (!applicationCatKeyResponseJsonFormatString.isNullOrEmpty()) {
            val appListResponseModel = Gson().fromJson(
                applicationCatKeyResponseJsonFormatString,
                AppListResponseModel::class.java
            )
            screenshotsList = appListResponseModel?.appList!!
            screenshotsAdaptor.addItemList(screenshotsList)
        } else {
            RetrofitClient.apiService.topGoogleAppCharts(
                catKey = args.appInfoModel.catKey
            ).enqueue(object : retrofit2.Callback<AppListResponseModel> {
                override fun onResponse(
                    call: Call<AppListResponseModel>,
                    response: Response<AppListResponseModel>
                ) {
                    onServerResponseScreenshots(response)
                }

                override fun onFailure(call: Call<AppListResponseModel>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "${t.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }


    private fun onServerResponseScreenshots(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {

                val appListResponseModel: AppListResponseModel? = response.body()
                val applicationCatKeyResponseJsonFormatString = Gson().toJson(appListResponseModel)
                storage.saveString(args.appInfoModel.catKey, applicationCatKeyResponseJsonFormatString)
                screenshotsList = appListResponseModel?.appList!!
                screenshotsAdaptor.addItemList(screenshotsList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }

}