package com.jmanrique.loldataproject.app.ui.champion_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.jmanrique.loldataproject.app.ui.base.BaseFragment
import com.jmanrique.loldataproject.databinding.FragmentChampionListBinding
import com.jmanrique.loldataproject.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChampionListFragment : BaseFragment<FragmentChampionListBinding>(),
    SearchView.OnQueryTextListener {

    val NUMBER_COLUMNS = 3

    val viewModel: ChampionListViewModel by viewModels()

    @Inject
    lateinit var adapterFactory: ChampionListAdapterFactory
    private val championListAdapter by lazy { adapterFactory.create(NUMBER_COLUMNS) }

    lateinit var layoutManager: GridLayoutManager

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
        initViews()
        initObservers()
        callAPI()
    }

    private fun initViews() {
        layoutManager = GridLayoutManager(context, NUMBER_COLUMNS)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (championListAdapter.data[position].isInfo) NUMBER_COLUMNS
                else 1
            }
        }
        binding.championList.layoutManager = layoutManager
        binding.championList.adapter = championListAdapter
        championListAdapter.numberColumns = NUMBER_COLUMNS
        championListAdapter.onItemClick = { position, champion ->
            if (!champion.showInfo) viewModel.getChampionDetail(champion.id.toString())
            championListAdapter.addInfoElement(position, champion)
            championListAdapter.notifyDataSetChanged()
        }
    }

    private fun initObservers() {
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

        viewModel.championDetail.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    championListAdapter.addChampionInfo(it.data!!)
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

    private fun callAPI() {
        viewModel.getChampionSummary()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

}