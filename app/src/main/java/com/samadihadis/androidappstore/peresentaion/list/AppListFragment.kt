package com.samadihadis.androidappstore.peresentaion.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.data.AppListResponseModel
import com.samadihadis.androidappstore.databinding.FragmentAppListBinding
import com.samadihadis.androidappstore.peresentaion.adapters.gridStyle.AppGridStyleAdapter
import com.samadihadis.androidappstore.util.RetrofitClient
import com.samadihadis.androidappstore.util.SharePreferencesManager
import retrofit2.Call
import retrofit2.Response

class AppListFragment : Fragment() {

    private lateinit var binding: FragmentAppListBinding
    private val args by navArgs<AppListFragmentArgs>()
    private var appGridStyleInfoList = listOf<AppInfoModel>()
    private val appGridStyleAdapter by lazy {
        AppGridStyleAdapter()
    }
    private val storage by lazy {
        SharePreferencesManager(requireContext())
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
        appGridStyleAdapter.onItemGridStyleClickListener {
            findNavController().navigate(
                AppListFragmentDirections.actionToDetailFragment(it)
            )
        }
    }

    private fun getDataGridStyle() {
        val applicationCatKeyResponseJsonFormatString = storage.retrieveString(args.catKey)
        if (!applicationCatKeyResponseJsonFormatString.isNullOrEmpty()) {
            val appListResponseModel = Gson().fromJson(
                applicationCatKeyResponseJsonFormatString,
                AppListResponseModel::class.java
            )
            appGridStyleInfoList = appListResponseModel?.appList!!
            appGridStyleAdapter.addItemList(appGridStyleInfoList)
        } else {
            RetrofitClient.apiService.topGoogleAppCharts(
                catKey = args.catKey
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
    }

    private fun onServerResponseGridStyle(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {

                val appListResponseModel: AppListResponseModel? = response.body()
                val applicationCatKeyResponseJsonFormatString = Gson().toJson(appListResponseModel)
                storage.saveString(args.catKey, applicationCatKeyResponseJsonFormatString)
                appGridStyleInfoList = appListResponseModel?.appList!!
                appGridStyleAdapter.addItemList(appGridStyleInfoList)
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}