package com.massita.faire.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.massita.faire.R
import com.massita.faire.model.Brand
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.brand_item.view.*

class BrandAdapter : PagedListAdapter<Brand, BrandAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Brand>() {
            override fun areItemsTheSame(oldItem: Brand, newItem: Brand): Boolean =
                oldItem.token == newItem.token

            override fun areContentsTheSame(oldItem: Brand, newItem: Brand): Boolean =
                    oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brand = getItem(position)
        brand?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(brand: Brand) {
            itemView.brandTitle.text = brand.name
            itemView.brandDescription.text = brand.shortDescription

            brand.squaredImage?.let {
                Picasso.get().load(it.url)
                    .into(itemView.brandImage)
            }
        }

    }
}