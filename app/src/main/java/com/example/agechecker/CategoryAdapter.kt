package com.example.agechecker

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agechecker.databinding.OtherViewBinding

class CategoryAdapter(private val categories: List<Category>, private val context: Context, private val onQuestionClicked: () -> Unit):
RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: OtherViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {
                image.setImageResource(category.icon)
                question.text = category.question
                card.setCardBackgroundColor(context.resources.getColor(category.color, null))

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val linearInflater = LayoutInflater.from(parent.context)
        val binding = OtherViewBinding.inflate(linearInflater, parent, false)
        val categoryViewHolder = CategoryViewHolder(binding)
        return categoryViewHolder
    }

    override fun getItemCount(): Int {
        return categories.size
    }


    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.bind(categories[position])

    }

}


