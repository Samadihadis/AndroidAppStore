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
import com.samadihadis.androidappstore.peresentaion.apps.application.ApplicationListAdapter
import com.samadihadis.androidappstore.peresentaion.apps.business.BusinessListAdapter
import com.samadihadis.androidappstore.peresentaion.apps.sport.SportListAdapter
import com.samadihadis.androidappstore.util.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AppListFragment : Fragment() {

    private lateinit var binding: FragmentAppsBinding
    private var applicationInfoList = listOf<AppInfoModel>()
    private var businessInfoList = listOf<AppInfoModel>()
    private var sportInfoList = listOf<AppInfoModel>()
    private val applicationListAdaptor by lazy {
        ApplicationListAdapter()
    }
    private val businessListAdaptor by lazy {
        BusinessListAdapter()
    }
    private val sportListAdapter by lazy {
        SportListAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterApplication()
        getDataApplication()
        setupAdapterBusiness()
        getDataBusiness()
        setupAdapterSport()
        getDataSport()
    }

    private fun setupAdapterApplication() {
        with(binding.applicationRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = applicationListAdaptor
        }
    }

    private fun setupAdapterBusiness() {
        with(binding.businessRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = businessListAdaptor
        }
    }

    private fun setupAdapterSport() {
        with(binding.sportRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = sportListAdapter
        }
    }

    private fun getDataApplication() {
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
                onServerResponseApplication(response)
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

    private fun onServerResponseApplication(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                applicationInfoList = response.body()?.appList!!
                applicationListAdaptor.addItemList(applicationInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getDataBusiness() {
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
                onServerResponseBusiness(response)
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

    private fun onServerResponseBusiness(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                businessInfoList = response.body()?.appList!!
                businessListAdaptor.addItemList(businessInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataSport() {
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
                onServerResponseSport(response)
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

    private fun onServerResponseSport(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                sportInfoList = response.body()?.appList!!
                sportListAdapter.addItemList(sportInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}
