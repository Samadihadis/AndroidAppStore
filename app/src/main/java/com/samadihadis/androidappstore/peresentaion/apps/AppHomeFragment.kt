package com.samadihadis.androidappstore.peresentaion.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.data.AppListResponseModel
import com.samadihadis.androidappstore.databinding.FragmentAppHomeBinding
import com.samadihadis.androidappstore.peresentaion.adapters.smallDetailStyle.AppSmallDetailStyleAdapter
import com.samadihadis.androidappstore.peresentaion.adapters.boxStyle.AppBoxStyleAdapter
import com.samadihadis.androidappstore.peresentaion.adapters.bannerStyle.AppBannerStyleAdapter
import com.samadihadis.androidappstore.util.Constant.KAT_KEY_APPLICATION
import com.samadihadis.androidappstore.util.Constant.KAT_KEY_BUSINESS
import com.samadihadis.androidappstore.util.Constant.KAT_KEY_SPORTS
import com.samadihadis.androidappstore.util.RetrofitClient
import com.samadihadis.androidappstore.util.SharePreferencesManager
import com.samadihadis.androidappstore.util.Utils
import com.samadihadis.androidappstore.util.gone
import com.samadihadis.androidappstore.util.visible
import retrofit2.Call
import retrofit2.Response

class AppHomeFragment : Fragment() {

    private lateinit var binding: FragmentAppHomeBinding
    private var appBannerStyleInfoList = listOf<AppInfoModel>()
    private var appSmallDetailStyleInfoList = listOf<AppInfoModel>()
    private var appBoxStyleInfoList = listOf<AppInfoModel>()


    private val storage by lazy {
        SharePreferencesManager(requireContext())
    }

    private val appBannerStyleAdaptor by lazy {
        AppBannerStyleAdapter()
    }
    private val appSmallDetailStyleAdaptor by lazy {
        AppSmallDetailStyleAdapter()
    }
    private val appBoxStyleAdapter by lazy {
        AppBoxStyleAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBannerStyleAdapter()
        setupSmallDetailStyleAdapter()
        setupBoxStyleAdapter()

        startLoadingState()

        getDataBannerStyle()
        getDataSmallDetailStyle()
        getDataBoxStyle()
        setupViews()
    }

    private fun setupViews() = with(binding) {
        nextSmallDetailStyleImageView.setOnClickListener {
            findNavController().navigate(
                AppHomeFragmentDirections.actionToAppListFragment(KAT_KEY_BUSINESS)
            )
        }
        nextBoxStyleImageView.setOnClickListener {
            findNavController().navigate(
                AppHomeFragmentDirections.actionToAppListFragment(KAT_KEY_SPORTS)
            )
        }
    }

    private fun startLoadingState() = with(binding) {
        shimmerFrameLayout.visible()
        shimmerFrameLayout.startShimmer()

        smallDetailStyleTitleTextView.gone()
        nextSmallDetailStyleImageView.gone()

        boxStyleTitleTextView.gone()
        nextBoxStyleImageView.gone()
    }

    private fun stopLoadingState() = with(binding) {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.gone()

        smallDetailStyleTitleTextView.visible()
        nextSmallDetailStyleImageView.visible()

        boxStyleTitleTextView.visible()
        nextBoxStyleImageView.visible()
    }

    private fun setupBannerStyleAdapter() {
        with(binding.bannerStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appBannerStyleAdaptor
        }
        appBannerStyleAdaptor.onInstallButtonClickListener {
            Utils.openMarket(requireContext(), it)
        }
        appBannerStyleAdaptor.onItemBannerStyleClickListener {
           findNavController().navigate(
               AppHomeFragmentDirections.actionToDetailFragment(it)
           )
        }
    }

    private fun setupSmallDetailStyleAdapter() {
        with(binding.smallDetailStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appSmallDetailStyleAdaptor
        }
        appSmallDetailStyleAdaptor.onItemSmallStyleClickListener {
            findNavController().navigate(
                AppHomeFragmentDirections.actionToDetailFragment(it)
            )
        }
    }

    private fun setupBoxStyleAdapter() {
        with(binding.boxStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appBoxStyleAdapter
        }
        appBoxStyleAdapter.onItemBoxStyleClickListener {
            findNavController().navigate(
                AppHomeFragmentDirections.actionToDetailFragment(it)
            )
        }
    }

    private fun getDataBannerStyle() {
        val applicationCatKeyResponseJsonFormatString = storage.retrieveString(KAT_KEY_APPLICATION)
        if (!applicationCatKeyResponseJsonFormatString.isNullOrEmpty()) {
            val appListResponseModel = Gson().fromJson(
                applicationCatKeyResponseJsonFormatString,
                AppListResponseModel::class.java
            )
            stopLoadingState()
            appBannerStyleInfoList = appListResponseModel?.appList!!
            appBannerStyleAdaptor.addItemList(appBannerStyleInfoList)
        } else {
            RetrofitClient.apiService.topGoogleAppCharts(
                catKey = KAT_KEY_APPLICATION,
            ).enqueue(object : retrofit2.Callback<AppListResponseModel> {
                override fun onResponse(
                    call: Call<AppListResponseModel>,
                    response: Response<AppListResponseModel>
                ) {
                    onServerResponseBannerStyle(response)
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

    private fun onServerResponseBannerStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                val appListResponseModel: AppListResponseModel? = response.body()
                val applicationCatKeyResponseJsonFormatString = Gson().toJson(appListResponseModel)
                storage.saveString(
                    KAT_KEY_APPLICATION,
                    applicationCatKeyResponseJsonFormatString
                )
                stopLoadingState()
                appBannerStyleInfoList = appListResponseModel?.appList!!
                appBannerStyleAdaptor.addItemList(appBannerStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataSmallDetailStyle() {
        val applicationCatKeyResponseJsonFormatString = storage.retrieveString(KAT_KEY_BUSINESS)
        if (!applicationCatKeyResponseJsonFormatString.isNullOrEmpty()) {
            val appListResponseModel = Gson().fromJson(
                applicationCatKeyResponseJsonFormatString,
                AppListResponseModel::class.java
            )
            stopLoadingState()
            appSmallDetailStyleInfoList = appListResponseModel?.appList!!
            appSmallDetailStyleAdaptor.addItemList(appSmallDetailStyleInfoList)
        } else {
            RetrofitClient.apiService.topGoogleAppCharts(
                catKey = KAT_KEY_BUSINESS
            ).enqueue(object : retrofit2.Callback<AppListResponseModel> {
                override fun onResponse(
                    call: Call<AppListResponseModel>,
                    response: Response<AppListResponseModel>
                ) {
                    onServerResponseSmallDetailStyle(response)
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

    private fun onServerResponseSmallDetailStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {

                val appListResponseModel: AppListResponseModel? = response.body()
                val applicationCatKeyResponseJsonFormatString = Gson().toJson(appListResponseModel)
                storage.saveString(KAT_KEY_BUSINESS, applicationCatKeyResponseJsonFormatString)

                stopLoadingState()
                appSmallDetailStyleInfoList = appListResponseModel?.appList!!
                appSmallDetailStyleAdaptor.addItemList(appSmallDetailStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataBoxStyle() {
        val applicationCatKeyResponseJsonFormatString = storage.retrieveString(KAT_KEY_SPORTS)
        if (!applicationCatKeyResponseJsonFormatString.isNullOrEmpty()) {
            val appListResponseModel = Gson().fromJson(
                applicationCatKeyResponseJsonFormatString,
                AppListResponseModel::class.java
            )
            stopLoadingState()
            appBoxStyleInfoList = appListResponseModel?.appList!!
            appBoxStyleAdapter.addItemList(appBoxStyleInfoList)
        } else {
            RetrofitClient.apiService.topGoogleAppCharts(
                catKey = KAT_KEY_SPORTS
            ).enqueue(object : retrofit2.Callback<AppListResponseModel> {
                override fun onResponse(
                    call: Call<AppListResponseModel>,
                    response: Response<AppListResponseModel>
                ) {
                    onServerResponseBoxStyle(response)
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

    private fun onServerResponseBoxStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {

                val appListResponseModel: AppListResponseModel? = response.body()
                val applicationCatKeyResponseJsonFormatString = Gson().toJson(appListResponseModel)
                storage.saveString(KAT_KEY_SPORTS, applicationCatKeyResponseJsonFormatString)

                stopLoadingState()
                appBoxStyleInfoList = appListResponseModel?.appList!!
                appBannerStyleAdaptor.addItemList(appBoxStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}

