package com.samadihadis.androidappstore.peresentaion.apps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.samadihadis.androidappstore.data.AppInfoModel
import com.samadihadis.androidappstore.data.AppListResponseModel
import com.samadihadis.androidappstore.databinding.FragmentAppsBinding
import com.samadihadis.androidappstore.util.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AppsFragment : Fragment() {

    private lateinit var binding: FragmentAppsBinding
    private var appInfoList = listOf<AppInfoModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getData() {
        RetrofitClient.apiService.topGoogleAppCharts()
            .enqueue(object : retrofit2.Callback<AppListResponseModel> {
                override fun onResponse(
                    call: Call<AppListResponseModel>,
                    response: Response<AppListResponseModel>
                ) {
                    onServerResponse(response)
                }

                override fun onFailure(call: Call<AppListResponseModel>, t: Throwable) {
                    Toast.makeText(requireContext(), "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }
    private fun onServerResponse(response: Response<AppListResponseModel>) {
        if (response.isSuccessful) {
            if (!response.body()?.appList.isNullOrEmpty()) {
                appInfoList = response.body()?.appList!!
            } else {
                Toast.makeText(requireContext(), "List is Empty!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Got an error!", Toast.LENGTH_SHORT).show()
        }
    }
}