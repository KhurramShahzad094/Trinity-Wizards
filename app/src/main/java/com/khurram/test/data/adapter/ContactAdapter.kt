package com.khurram.test.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khurram.test.data.model.ContactModel
import com.khurram.test.databinding.ItemContactListBinding

class ContactAdapter(private var onItemClicked: ((model : ContactModel) -> Unit)) : ListAdapter<ContactModel, ContactAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ViewHolder(private val binding: ItemContactListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: ContactModel) {
            binding.apply {

                tvName.text ="${model.firstName}  ${model.lastName}"
                cvParent.setOnClickListener {
                    onItemClicked(model)
                }
            }
        }
    }

   companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ContactModel>() {
            override fun areItemsTheSame(
                oldItem: ContactModel,
                newItem: ContactModel
            ) =
                oldItem.email == newItem.email

            override fun areContentsTheSame(
                oldItem: ContactModel,
                newItem: ContactModel
            ) =
                oldItem == newItem
        }
    }
}