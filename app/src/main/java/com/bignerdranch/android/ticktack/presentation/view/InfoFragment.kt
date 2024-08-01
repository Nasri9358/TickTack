package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.ticktack.BuildConfig
import com.bignerdranch.android.ticktack.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private val binding by lazy {FragmentInfoBinding.inflate(layoutInflater)}

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInfoBinding.inflate(layoutInflater, container, false)
        binding.tvAppVersionInfo.text = BuildConfig.VERSION_NAME
        return binding.root
    }
}