package com.massita.faire.ui


import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.massita.faire.R
import com.massita.faire.model.Category
import com.massita.faire.ui.adapter.BrandAdapter
import com.massita.faire.ui.adapter.CategoryAdapter
import com.massita.faire.viewmodel.BrandViewModel
import com.massita.faire.viewmodel.CategoriesViewModel
import com.massita.faire.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var subCategoryAdapter: CategoryAdapter
    private lateinit var brandAdapter: BrandAdapter

    private lateinit var categoriesViewModel: CategoriesViewModel

    private lateinit var selectedCategoryViewModel: CategoryViewModel
    private lateinit var brandViewModel: BrandViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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
                // TODO: get suggestions
                return true
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModels()
        setupCategories()
        setupSubCategory()
        setupBrands()

    }

    private fun setupViewModels() {
        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        selectedCategoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        brandViewModel = ViewModelProviders.of(this).get(BrandViewModel::class.java)
    }

    private fun setupCategories() {
        categoryAdapter = CategoryAdapter()
        categoryAdapter.setSelectedItem(selectedCategoryViewModel)

        categoriesRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            setHasFixedSize(true)
            adapter = categoryAdapter
        }

        categoriesViewModel.getCategories().observe(this, Observer { categoryAdapter.setList(it) })
        selectedCategoryViewModel.getCategory().observe(this, Observer { changeSelectedCategory(it) })
    }

    private fun setupSubCategory() {
        subCategoryAdapter = CategoryAdapter(true)
        subCategoryAdapter.setSelectedItem(selectedCategoryViewModel)

        subCategoriesRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            adapter = subCategoryAdapter
        }

        selectedCategoryViewModel.getSubCategory().observe(this, Observer { changeSelectedSubCategory(it) })
    }

    private fun changeSelectedCategory(selectedCategory: Category) {
        subCategoryAdapter.setList(selectedCategory.subCategories)
        brandViewModel.setCategory(selectedCategory.name)
    }

    private fun changeSelectedSubCategory(selectedCategory: Category) {
        brandViewModel.setCategory(selectedCategory.name)
    }

    private fun setupBrands() {
        brandAdapter = BrandAdapter()

        itemsRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            adapter = brandAdapter
        }

        brandViewModel.brandList.observe(this, Observer { brandAdapter.submitList(it) })
    }
}
