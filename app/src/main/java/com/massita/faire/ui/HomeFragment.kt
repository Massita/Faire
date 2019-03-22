package com.massita.faire.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.massita.faire.R
import com.massita.faire.viewmodel.BrandViewModel
import com.massita.faire.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var brandAdapter: BrandAdapter

    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var brandViewModel: BrandViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategories()
        setupBrands()

    }

    private fun setupCategories() {
        categoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        categoryAdapter = CategoryAdapter()

        categoriesRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            setHasFixedSize(true)
            adapter = categoryAdapter
        }

        categoriesViewModel.getCategories().observe(this, Observer { categoryAdapter.setList(it) })
    }

    private fun setupBrands() {
        brandViewModel = ViewModelProviders.of(this).get(BrandViewModel::class.java)

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
