package com.massita.faire.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.massita.faire.R
import com.massita.faire.model.Brand
import com.massita.faire.ui.adapter.ProductsAdapter
import com.massita.faire.viewmodel.ProductsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_brand.*

class BrandFragment : Fragment() {
    private val args: BrandFragmentArgs by navArgs()
    private var brand: Brand? = null

    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var productsViewModel : ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        brand = args.brand!!

        brandTitle.text = brand?.name
        brandDescription.text = brand?.description

        brand?.squaredImage?.let {
            Picasso.get().load(it.url)
                .into(brandImage)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        productsAdapter = ProductsAdapter()
        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)

        productsRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            setHasFixedSize(true)
            adapter = productsAdapter
        }

        productsViewModel.getProducts(brand?.token).observe(this, Observer { productsAdapter.setList(it) })
    }


}
