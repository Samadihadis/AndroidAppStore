package com.samadihadis.androidappstore.peresentaion.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.samadihadis.androidappstore.R
import com.samadihadis.androidappstore.databinding.FragmentAboutAppBinding


class AboutAppFragment : Fragment() {

    private lateinit var binding: FragmentAboutAppBinding
    private val args by navArgs<AboutAppFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Glide.with(requireContext())
                .load(args.appInfoModel.icon72)
                .placeholder(R.drawable.banner_image_placeholder)
                .into(applicationImageView)
            titleValueTextView.text = args.appInfoModel.title
            aboutValueTextView.text = args.appInfoModel.description
            aboutNextImageView.setOnClickListener {
                findNavController().navigate(AboutAppFragmentDirections.actionToDetailFragment(args.appInfoModel))
            }
        }
    }
}