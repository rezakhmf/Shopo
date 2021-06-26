package com.farahaniconsulting.shopo.ui.shoppingitems.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farahaniconsulting.shopo.R
import com.farahaniconsulting.shopo.databinding.ItemOrderBinding
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO

class ShoppingItemViewHolder(private val viewBinding: ItemOrderBinding) :
   RecyclerView.ViewHolder(viewBinding.root)  {

       fun bind(shoppingItem: ShoppingItemDTO, callback: ((ShoppingItemDTO)) -> Unit) {
           viewBinding.shoppingItem = shoppingItem
           viewBinding.executePendingBindings()

           viewBinding.root.setOnClickListener {
               viewBinding.shoppingItem?.let { callback.invoke(it) }
           }
       }

    companion object {
        fun create(parent: ViewGroup) : ShoppingItemViewHolder {
            return ShoppingItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_order, parent, false
                )
            )
        }
    }
}