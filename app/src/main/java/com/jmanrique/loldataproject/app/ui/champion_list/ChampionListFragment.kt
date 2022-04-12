package com.jmanrique.loldataproject.app.ui.champion_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmanrique.loldataproject.app.ui.base.BaseFragment
import com.jmanrique.loldataproject.databinding.FragmentChampionListBinding
import com.jmanrique.loldataproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChampionListFragment : BaseFragment<FragmentChampionListBinding>(),
    SearchView.OnQueryTextListener {

    val viewModel: ChampionListViewModel by viewModels()

    @Inject
    lateinit var championListAdapter: ChampionListAdapter

    lateinit var layoutManager: LinearLayoutManager

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentChampionListBinding =
        FragmentChampionListBinding.inflate(layoutInflater, container, attachToRoot)

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = GridLayoutManager(context, 3)
        binding.championList.layoutManager = layoutManager
        binding.championList.adapter = championListAdapter

        viewModel.getChampionSummary()

        viewModel.championsList.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    championListAdapter.data = it.data!!
                    championListAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                }
            }
        })
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

}