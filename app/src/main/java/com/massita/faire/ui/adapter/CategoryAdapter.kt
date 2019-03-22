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

class CategoryAdapter(var isSubCategory: Boolean = false) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categoryList : List<Category>? = null
    private var categoryViewModel : CategoryViewModel? = null
    private var selectedItemPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categoryList?.let {
            val category = it[position]
            holder.bind(category, selectedItemPosition == position)
            holder.itemView.setOnClickListener {
                if(!isSubCategory) {
                    categoryViewModel?.setCategory(category)
                } else {
                    categoryViewModel?.setSubcategory(category)
                }

                if(selectedItemPosition != -1) {
                    notifyItemChanged(selectedItemPosition)
                }
                selectedItemPosition = position
                holder.setSelected()
            }
        }
    }

    fun setList(list: List<Category>) {
        this.categoryList = list
        selectedItemPosition = -1
        notifyDataSetChanged()
    }

    fun setSelectedItem(categoryViewModel: CategoryViewModel) {
        this.categoryViewModel = categoryViewModel
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category, selected: Boolean) {
            itemView.categoryName.text = category.name

            if(selected) {
                setSelected()
            } else {
                removeSelected()
            }
        }

        fun setSelected() {
            itemView.categoryName.setTypeface(null, Typeface.BOLD)
        }

        fun removeSelected() {
            itemView.categoryName.setTypeface(null, Typeface.NORMAL)
        }
    }
}