package com.jmanrique.loldataproject.app.ui.champion_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jmanrique.loldataproject.app.ui.base.BaseFragment
import com.jmanrique.loldataproject.databinding.FragmentChampionDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChampionDetailFragment : BaseFragment<FragmentChampionDetailBinding>() {

    val viewModel: ChampionDetailViewModel by viewModels()

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentChampionDetailBinding =
        FragmentChampionDetailBinding.inflate(layoutInflater, container, attachToRoot)

}