package com.samadihadis.androidappstore.peresentaion.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.data.AppListResponseModel
import com.samadihadis.androidappstore.databinding.FragmentAppsBinding
import com.samadihadis.androidappstore.peresentaion.apps.smallDetailStyle.AppSmallDetailStyleAdapter
import com.samadihadis.androidappstore.peresentaion.apps.boxStyle.AppBoxStyleAdapter
import com.samadihadis.androidappstore.peresentaion.apps.bannerStyle.AppBannerStyleAdapter
import com.samadihadis.androidappstore.util.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AppListFragment : Fragment() {

    private lateinit var binding: FragmentAppsBinding
    private var appBannerStyleInfoList = listOf<AppInfoModel>()
    private var appSmallDetailStyleInfoList = listOf<AppInfoModel>()
    private var appBoxStyleInfoList = listOf<AppInfoModel>()

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
        binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBannerStyleAdapter()
        getDataBannerStyle()
        setupSmallDetailStyleAdapter()
        getDataSmallDetailStyle()
        setupBoxStyleAdapter()
        getDataBoxStyle()
    }

    private fun setupBannerStyleAdapter() {
        with(binding.bannerStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appBannerStyleAdaptor
        }
    }

    private fun setupSmallDetailStyleAdapter() {
        with(binding.smallDetailStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appSmallDetailStyleAdaptor
        }
    }

    private fun setupBoxStyleAdapter() {
        with(binding.boxStyleRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appBoxStyleAdapter
        }
    }

    private fun getDataBannerStyle() {
        RetrofitClient.apiService.topGoogleAppCharts(
            listName = "topselling_free",
            catKey = "APPLICATION",
            country = "US",
            limit = "10",
            accessToken = "9619eb26cf48144f6fd92af896bb1eb0f2458c02"
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

    private fun onServerResponseBannerStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                appBannerStyleInfoList = response.body()?.appList!!
                appBannerStyleAdaptor.addItemList(appBannerStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getDataSmallDetailStyle() {
        RetrofitClient.apiService.topGoogleAppCharts(
            listName = "topselling_free",
            catKey = "BUSINESS",
            country = "US",
            limit = "10",
            accessToken = "9619eb26cf48144f6fd92af896bb1eb0f2458c02"
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

    private fun onServerResponseSmallDetailStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                appSmallDetailStyleInfoList = response.body()?.appList!!
                appSmallDetailStyleAdaptor.addItemList(appSmallDetailStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataBoxStyle() {
        RetrofitClient.apiService.topGoogleAppCharts(
            listName = "topselling_free",
            catKey = "SPORTS",
            country = "US",
            limit = "10",
            accessToken = "9619eb26cf48144f6fd92af896bb1eb0f2458c02"
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

    private fun onServerResponseBoxStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                appBoxStyleInfoList = response.body()?.appList!!
                appBoxStyleAdapter.addItemList(appBoxStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}
