package com.jmanrique.loldataproject.app.ui.champion_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.jmanrique.loldataproject.R
import com.jmanrique.loldataproject.app.ui.base.BaseFragment
import com.jmanrique.loldataproject.app.ui.main.MainActivity
import com.jmanrique.loldataproject.databinding.FragmentChampionListBinding
import com.jmanrique.loldataproject.utils.Status
import com.jmanrique.loldataproject.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_champion_list.*
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
    private lateinit var searchTerm: String

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
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val search = menu.findItem(R.id.menuSearch)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        championListAdapter.unselectChampion()
        championListAdapter.notifyDataSetChanged()

        if (newText != null) searchTerm = newText
        else filterList("")

        if (searchTerm.isNotEmpty()) filterList(searchTerm)
        else filterList("")

        return true
    }

    private fun filterList(searchTerm: String){
        viewModel.filterList(searchTerm)
    }

}