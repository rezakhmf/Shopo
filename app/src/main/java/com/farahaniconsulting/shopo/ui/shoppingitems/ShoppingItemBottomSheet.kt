package com.farahaniconsulting.shopo.ui.shoppingitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.farahaniconsulting.shopo.databinding.FragmentShoppingItemBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShoppingItemBottomSheet(val itemName: String, private val callbackListener: CallbackListener) : BottomSheetDialogFragment() {

    private var _binding: FragmentShoppingItemBottomsheetBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingItemBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            delete.setOnClickListener {
                dismiss()
                callbackListener.onDeleteClicked(itemName)
            }
            cancel.setOnClickListener { dismiss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface CallbackListener {
        fun onDeleteClicked(itemName: String)
    }

    companion object {
        const val TAG = "ShoppingItemBottomSheet"

        fun newInstance(itemName: String, callbackListener: CallbackListener): ShoppingItemBottomSheet {
            return ShoppingItemBottomSheet(itemName, callbackListener)
        }
    }

}