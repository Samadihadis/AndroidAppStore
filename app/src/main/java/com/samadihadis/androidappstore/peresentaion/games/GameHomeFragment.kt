package com.samadihadis.androidappstore.peresentaion.games

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
import com.samadihadis.androidappstore.databinding.FragmentGameHomeBinding
import com.samadihadis.androidappstore.peresentaion.adapters.bannerStyle.AppBannerStyleAdapter
import com.samadihadis.androidappstore.peresentaion.adapters.boxStyle.AppBoxStyleAdapter
import com.samadihadis.androidappstore.peresentaion.adapters.mediumDetailStyle.AppMediumDetailStyleAdapter
import com.samadihadis.androidappstore.peresentaion.apps.AppHomeFragmentDirections
import com.samadihadis.androidappstore.util.Constant
import com.samadihadis.androidappstore.util.RetrofitClient
import com.samadihadis.androidappstore.util.SharePreferencesManager
import com.samadihadis.androidappstore.util.gone
import com.samadihadis.androidappstore.util.visible
import retrofit2.Call
import retrofit2.Response

class GameHomeFragment : Fragment() {

    private lateinit var binding: FragmentGameHomeBinding
    private var appBannerStyleInfoList = listOf<AppInfoModel>()
    private var appMediumDetailStyleInfoList = listOf<AppInfoModel>()
    private var appBoxStyleInfoList = listOf<AppInfoModel>()


    private val storage by lazy {
        SharePreferencesManager(requireContext())
    }

    private val appBannerStyleAdaptor by lazy {
        AppBannerStyleAdapter()
    }
    private val appMediumDetailStyleAdaptor by lazy {
        AppMediumDetailStyleAdapter()
    }
    private val appBoxStyleAdapter by lazy {
        AppBoxStyleAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBannerStyleAdapter()
        setupMediumDetailStyleAdapter()
        setupBoxStyleAdapter()

        startLoadingState()

        getDataBannerStyle()
        getDataMediumDetailStyle()
        getDataBoxStyle()
        setupViews()
    }


    private fun setupViews() = with(binding) {
        nextMediumDetailStyleImageView.setOnClickListener {
            findNavController().navigate(
                GameHomeFragmentDirections.actionToAppListFragment(Constant.KAT_KEY_GAME_ACTION)
            )
        }
        nextBoxStyleImageView.setOnClickListener {
            findNavController().navigate(
                GameHomeFragmentDirections.actionToAppListFragment(Constant.KAT_KEY_GAME_CASUAL)
            )
        }
    }

    private fun startLoadingState() = with(binding) {
        shimmerFrameLayout.visible()
        shimmerFrameLayout.startShimmer()

        mediumDetailStyleTitleTextView.gone()
        nextMediumDetailStyleImageView.gone()

        boxStyleTitleTextView.gone()
        nextBoxStyleImageView.gone()
    }

    private fun stopLoadingState() = with(binding) {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.gone()

        mediumDetailStyleTitleTextView.visible()
        nextMediumDetailStyleImageView.visible()

        boxStyleTitleTextView.visible()
        nextBoxStyleImageView.visible()
    }

    private fun setupBannerStyleAdapter() {
        with(binding.bannerStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appBannerStyleAdaptor
        }
        appBannerStyleAdaptor.onItemBannerStyleClickListener {
            findNavController().navigate(
                GameHomeFragmentDirections.actionToDetailFragment(it)
            )
        }
    }

    private fun setupMediumDetailStyleAdapter() {
        with(binding.mediumDetailStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appMediumDetailStyleAdaptor
        }
        appMediumDetailStyleAdaptor.onItemMediumStyleClickListener {
            findNavController().navigate(
                GameHomeFragmentDirections.actionToDetailFragment(it)
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
                GameHomeFragmentDirections.actionToDetailFragment(it)
            )
        }
    }

    private fun getDataBannerStyle() {
        val applicationCatKeyResponseJsonFormatString =
            storage.retrieveString(Constant.KAT_KEY_GAME_STRATEGY)
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
                catKey = Constant.KAT_KEY_GAME_STRATEGY
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
                   Constant.KAT_KEY_GAME_STRATEGY,
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

    private fun getDataMediumDetailStyle() {
        val applicationCatKeyResponseJsonFormatString =
            storage.retrieveString(Constant.KAT_KEY_GAME_ACTION)
        if (!applicationCatKeyResponseJsonFormatString.isNullOrEmpty()) {
            val appListResponseModel = Gson().fromJson(
                applicationCatKeyResponseJsonFormatString,
                AppListResponseModel::class.java
            )
            stopLoadingState()
            appMediumDetailStyleInfoList = appListResponseModel?.appList!!
            appMediumDetailStyleAdaptor.addItemList(appMediumDetailStyleInfoList)
        } else {
            RetrofitClient.apiService.topGoogleAppCharts(
                catKey = Constant.KAT_KEY_GAME_ACTION
            ).enqueue(object : retrofit2.Callback<AppListResponseModel> {
                override fun onResponse(
                    call: Call<AppListResponseModel>,
                    response: Response<AppListResponseModel>
                ) {
                    onServerResponseMediumDetailStyle(response)
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

    private fun onServerResponseMediumDetailStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {

                val appListResponseModel: AppListResponseModel? = response.body()
                val applicationCatKeyResponseJsonFormatString = Gson().toJson(appListResponseModel)
                storage.saveString(
                    Constant.KAT_KEY_GAME_ACTION,
                    applicationCatKeyResponseJsonFormatString
                )

                stopLoadingState()
                appMediumDetailStyleInfoList = appListResponseModel?.appList!!
                appMediumDetailStyleAdaptor.addItemList(appMediumDetailStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataBoxStyle() {
        val applicationCatKeyResponseJsonFormatString =
            storage.retrieveString(Constant.KAT_KEY_GAME_CASUAL)
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
                catKey = Constant.KAT_KEY_GAME_CASUAL
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
                storage.saveString(
                    Constant.KAT_KEY_GAME_CASUAL,
                    applicationCatKeyResponseJsonFormatString
                )

                stopLoadingState()
                appBoxStyleInfoList = appListResponseModel?.appList!!
                appBoxStyleAdapter.addItemList(appBoxStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}