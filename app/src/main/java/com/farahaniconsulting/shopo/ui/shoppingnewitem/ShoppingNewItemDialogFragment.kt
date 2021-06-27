package com.farahaniconsulting.shopo.ui.shoppingnewitem

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.farahaniconsulting.shopo.databinding.FragmentShoppingNewItemBinding
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_shopping_new_item.*

class ShoppingNewItemDialogFragment(private val callbackListener: CallbackListener): DialogFragment() {

    private lateinit var binding: FragmentShoppingNewItemBinding

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingNewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
             toolbar.setNavigationOnClickListener {
                 val itemName = itemNameValue.text.toString()
                 val itemPrice = itemPriceValue.text.toString()
                 if (itemName.isNotBlank() && itemPrice.isNotBlank()) {
                     val shoppingItem = ShoppingItemDTO(
                         id = null,
                         name = itemName,
                         price = "$$itemPrice",
                         qrUrl = null,
                         thumbnail = null
                     )
                     callbackListener.onDataReceived(shoppingItem)
                 }
                dismiss()
            }
        }
    }

    interface CallbackListener {
        fun onDataReceived(item: ShoppingItemDTO)
    }

    companion object {
        const val TAG = "ShoppingNewItemDialogFragment"
    }
}