package com.farahaniconsulting.shopo.ui.shoppingitems.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO

class ShoppingItemDiffUtilCallback : DiffUtil.ItemCallback<ShoppingItemDTO>() {
    override fun areItemsTheSame(oldItem: ShoppingItemDTO, newItem: ShoppingItemDTO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingItemDTO, newItem: ShoppingItemDTO): Boolean {
        return oldItem.name == newItem.name
    }
}