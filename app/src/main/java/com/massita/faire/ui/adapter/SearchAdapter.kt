package com.massita.faire.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.massita.faire.R
import com.massita.faire.model.Brand
import com.massita.faire.ui.SearchFragmentDirections
import kotlinx.android.synthetic.main.brand_search_item.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var brandList: List<Brand>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brand_search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = brandList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        brandList?.let {
            val brand = it[position]
            holder.bind(brand)
        }
    }

    fun setList(list: List<Brand>) {
        brandList = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(brand: Brand) {
            itemView.brandName.text = brand.name

            val action = SearchFragmentDirections.actionSelectBrand(null)
            action.brand = brand
            itemView.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_selectBrand, action.arguments)
            )
        }
    }
}