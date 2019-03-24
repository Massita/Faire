package com.massita.faire.ui


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
import com.massita.faire.viewmodel.BrandViewModel
import com.massita.faire.viewmodel.ProductsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_brand.*

class BrandFragment : Fragment() {
    private val args: BrandFragmentArgs by navArgs()
    private var brandViewModel: BrandViewModel? = null

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

        loadBrand()
        setupRecyclerView()
    }

    private fun loadBrand() {
        var brand = args.brand
        val brandToken = args.brandToken

        if(!brandToken.isNullOrEmpty()) {
            brand = Brand(brandToken, null, null, null, null, null, null)
        }

        brand?.let {
            brandViewModel = ViewModelProviders.of(this).get(BrandViewModel::class.java)

            brandViewModel?.getBrand()?.observe(this, Observer { setBrandData(it) })
            brandViewModel?.brand?.postValue(brand)

            if(it.description.isNullOrEmpty()) {
                brandViewModel?.loadBrand(it.token)
            }

            loadProducts(it)
        }
    }

    private fun loadProducts(brand: Brand) {
        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        productsViewModel.getProducts(brand.token).observe(this, Observer { productsAdapter.setList(it) })
    }

    private fun setBrandData(brand: Brand) {
        brandTitle.text = brand.name
        brandDescription.text = brand.description
        brandDescription.movementMethod = ScrollingMovementMethod()

        brand.squaredImage?.let {
            Picasso.get().load(it.url)
                .fit()
                .into(brandImage)
        }
    }

    private fun setupRecyclerView() {
        productsAdapter = ProductsAdapter()

        productsRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = mLayoutManager
            addItemDecoration(DividerItemDecoration(context, mLayoutManager.orientation))
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }


}
