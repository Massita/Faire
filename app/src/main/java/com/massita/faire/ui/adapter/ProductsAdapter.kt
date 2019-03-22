package com.massita.faire.ui.adapter

import android.icu.util.Currency
import android.icu.util.ULocale
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.massita.faire.R
import com.massita.faire.model.Product
import com.massita.faire.util.CurrencyConverter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*
import java.util.*

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    var productList: List<Product>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = productList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        productList?.let {
            val product = it[position]
            holder.bind(product)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            val currencyConverter = CurrencyConverter()
            itemView.productTitle.text = product.name
            itemView.productRetailPrice.text = currencyConverter.convertLongToCurrency(product.retailPriceCents, Locale.US)

            // Loading just the first image for now
            product.images?.let {
                if(it.isNotEmpty()) {
                    Picasso.get().load(it[0].url)
                        .into(itemView.productImage)
                }
            }
        }
    }
}