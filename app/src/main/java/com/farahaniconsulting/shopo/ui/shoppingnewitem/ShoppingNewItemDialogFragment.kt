package com.farahaniconsulting.shopo.ui.shoppingnewitem

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.farahaniconsulting.shopo.databinding.FragmentShoppingNewItemBinding
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO


class ShoppingNewItemDialogFragment(private val callbackListener: CallbackListener): DialogFragment() {

    private var _binding: FragmentShoppingNewItemBinding? = null

    private val binding get() = _binding!!

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
        _binding = FragmentShoppingNewItemBinding.inflate(inflater, container, false)
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
                         qrUrl = "",
                         thumbnail = ""
                     )
                     callbackListener.onDataReceived(shoppingItem)
                 }
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface CallbackListener {
        fun onDataReceived(item: ShoppingItemDTO)
    }

    companion object {
        const val TAG = "ShoppingNewItemDialogFragment"

        fun newInstance(callbackListener: CallbackListener): ShoppingNewItemDialogFragment {
            return ShoppingNewItemDialogFragment(callbackListener)
        }
    }
}