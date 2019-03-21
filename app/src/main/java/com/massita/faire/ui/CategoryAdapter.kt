package com.massita.faire.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.massita.faire.R
import com.massita.faire.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categoryList : List<Category>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categoryList?.let {
            val category = it[position]
            holder.bind(category)
        }
    }

    fun setList(list: List<Category>) {
        this.categoryList = list
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.categoryName.text = category.name
        }
    }
}