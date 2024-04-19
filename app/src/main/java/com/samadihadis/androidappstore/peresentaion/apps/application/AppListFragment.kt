package com.samadihadis.androidappstore.peresentaion.apps.application

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
import com.samadihadis.androidappstore.util.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AppListFragment : Fragment() {

    private lateinit var binding: FragmentAppsBinding
    private var appInfoList = listOf<AppInfoModel>()
    private val appListAdaptor by lazy {
        ApplicationListAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        getData()
    }

    private fun setupAdapter() {
        with(binding.applicationRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = appListAdaptor
        }
    }

    private fun getData() {
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
                onServerResponse(response)
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

    private fun onServerResponse(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                appInfoList = response.body()?.appList!!
                appListAdaptor.addItemList(appInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}