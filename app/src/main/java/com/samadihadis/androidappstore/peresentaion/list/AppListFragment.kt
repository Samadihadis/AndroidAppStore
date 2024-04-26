package com.samadihadis.androidappstore.peresentaion.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.data.AppListResponseModel
import com.samadihadis.androidappstore.databinding.FragmentAppListBinding
import com.samadihadis.androidappstore.peresentaion.adapters.gridStyle.AppGridStyleAdapter
import com.samadihadis.androidappstore.util.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AppListFragment : Fragment() {

    private lateinit var binding: FragmentAppListBinding
    private var appGridStyleInfoList = listOf<AppInfoModel>()
    private val appGridStyleAdapter by lazy {
        AppGridStyleAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGridStyleAdapter()
        getDataGridStyle()
    }

    private fun setupGridStyleAdapter() {
        with(binding.gridStyleRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = appGridStyleAdapter
        }
    }

    private fun getDataGridStyle() {
        RetrofitClient.apiService.topGoogleAppCharts(
            listName = "topselling_free",
            catKey = "SPORTS",
            country = "US",
            limit = "10",
            accessToken = "3274a444a8462f7e43e2b00a0097e4d6c7bdc187"
        ).enqueue(object : retrofit2.Callback<AppListResponseModel> {
            override fun onResponse(
                call: Call<AppListResponseModel>,
                response: Response<AppListResponseModel>
            ) {
                onServerResponseGridStyle(response)
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

    private fun onServerResponseGridStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                appGridStyleInfoList = response.body()?.appList!!
                appGridStyleAdapter.addItemList(appGridStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}