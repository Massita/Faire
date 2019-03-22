package com.massita.faire.ui.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.massita.faire.R
import com.massita.faire.model.Category
import com.massita.faire.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categoryList : List<Category>? = null
    private var categoryViewModel : CategoryViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categoryList?.let {
            val category = it[position]
            holder.bind(category)
            holder.itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    categoryViewModel?.setCategory(category)
                }

            })
        }
    }

    fun setList(list: List<Category>) {
        this.categoryList = list
        notifyDataSetChanged()
    }

    fun setSelectedItem(categoryViewModel: CategoryViewModel) {
        this.categoryViewModel = categoryViewModel
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            itemView.categoryName.text = category.name
        }
    }
}