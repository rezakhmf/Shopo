package com.farahaniconsulting.shopo.ui.shoppingitems.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import com.farahaniconsulting.shopo.ui.shoppingitems.list.viewholder.ShoppingItemViewHolder

class ShoppingListAdapter(private val itemClickHandler: ((ShoppingItemDTO) -> Unit)) :
   ListAdapter<ShoppingItemDTO, ShoppingItemViewHolder>(ShoppingItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, itemClickHandler)
        }
    }
}