package com.massita.faire.ui


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.massita.faire.R
import com.massita.faire.model.Brand
import com.massita.faire.ui.adapter.SearchAdapter
import com.massita.faire.viewmodel.SearchResultViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchViewModel: SearchResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)

        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView?
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewModel.loadSuggestions(newText?:"")
                return true
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    fun setupRecyclerView() {
        searchViewModel = ViewModelProviders.of(this).get(SearchResultViewModel::class.java)
        searchAdapter = SearchAdapter()

        searchRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            adapter = searchAdapter
        }

        searchViewModel.getBrands().observe(this, Observer { changeSearchResult(it) })
    }

    fun changeSearchResult(result: List<Brand>) {
        searchAdapter.setList(result)
    }

}
