package com.jmanrique.loldataproject.app.ui.champion_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.jmanrique.loldataproject.R
import com.jmanrique.loldataproject.app.ui.base.BaseFragment
import com.jmanrique.loldataproject.app.ui.main.MainActivity
import com.jmanrique.loldataproject.databinding.FragmentChampionListBinding
import com.jmanrique.loldataproject.domain.entities.ChampionSummary
import com.jmanrique.loldataproject.utils.Status
import com.jmanrique.loldataproject.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChampionListFragment : BaseFragment<FragmentChampionListBinding>(),
    SearchView.OnQueryTextListener {

    private lateinit var searchView: SearchView
    var viewCreated = false
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
        if (!viewCreated) {
            viewCreated = true
            initViews()
            initListeners()
            initObservers()
            callAPI()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchView.setOnQueryTextListener(null)
        championListAdapter.unselectChampion()
        championListAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar?.title = ""
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
    }

    private fun initListeners() {
        championListAdapter.onItemClick = { position, champion ->
            if (!champion.showInfo) viewModel.getChampionDetail(champion.id.toString())
            championListAdapter.addInfoElement(position, champion)
            championListAdapter.notifyDataSetChanged()
            hideKeyboard()
        }
        championListAdapter.onReadMoreClick = {
            navigateToChampionDetail(it)
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
                    if (view?.isVisible == true)
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
                    if (view?.isVisible == true)
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
        val searchItem = menu.findItem(R.id.menuSearch)
        searchView = searchItem?.actionView as SearchView

        //Restore last query (for example when navigating back from champions detail fragment or orientation changes)
        val pendingQuery = viewModel.currentQuery()
        if (pendingQuery.isNotEmpty()) {
            searchItem.expandActionView()
            searchView.setQuery(pendingQuery, false)
            viewModel.filterList(pendingQuery)
            searchView.clearFocus()
        }

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

    private fun filterList(searchTerm: String) {
        viewModel.filterList(searchTerm)
    }

    private fun navigateToChampionDetail(champion: ChampionSummary) {
        val navController = view?.let { Navigation.findNavController(it) }
        val bundle = Bundle()
        bundle.putSerializable("champion", champion)
        val id = R.id.action_championListFragment_to_championDetailFragment
        navController?.popBackStack(id, true)
        navController?.navigate(id, bundle)
    }

}