package com.farahaniconsulting.shopo.ui.shoppingitems.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.farahaniconsulting.shopo.databinding.FragmentShoppingListBinding
import com.farahaniconsulting.shopo.di.modules.ViewModelFactory
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import com.farahaniconsulting.shopo.ui.getJsonDataFromAsset
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemBottomSheet
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemsContract
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemsViewModel
import com.farahaniconsulting.shopo.ui.shoppingitems.list.adapter.ShoppingListAdapter
import com.farahaniconsulting.shopo.ui.shoppingnewitem.ShoppingNewItemDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.RuntimeException
import javax.inject.Inject

class ShoppingItemsListFragment : Fragment(),
    HasAndroidInjector,
    ShoppingNewItemDialogFragment.CallbackListener,
    ShoppingItemBottomSheet.CallbackListener {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var viewModel: ShoppingItemsViewModel

    private lateinit var binding: FragmentShoppingListBinding

    private lateinit var shoppingListAdapter: ShoppingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactory).get(ShoppingItemsViewModel::class.java)
        viewModel.content = requireContext().getJsonDataFromAsset(SHOPPING_LIST_SAMPLE_DATA)
        viewModel.fetchShoppingItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        shoppingListAdapter = ShoppingListAdapter {
            it.name?.let {  name ->
                ShoppingItemBottomSheet.newInstance(name,this@ShoppingItemsListFragment)
                    .show(parentFragmentManager, ShoppingItemBottomSheet.TAG)
            }
        }
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.apply {
            shoppingListRV.adapter = shoppingListAdapter
            addOrderButton.setOnClickListener {
                ShoppingNewItemDialogFragment.newInstance(this@ShoppingItemsListFragment)
                    .show(parentFragmentManager, ShoppingNewItemDialogFragment.TAG)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            uiViewModel = viewModel
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            Timber.d("Shopping List: $it")
            it?.let { render(it) }
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun render(viewState: ShoppingItemsContract.ViewState) {
        when(viewState.isLoading) {
            true -> binding.pageLoadingIndicator.visibility = View.VISIBLE
            false -> binding.pageLoadingIndicator.visibility = View.GONE
        }
        if (viewState.errorState != null) {
            showError(viewState.errorState.message.getText(requireContext()).toString())
            showListView(false)
        }

        if (viewState.activityData.isNotEmpty()) {
            showListView(true)
            shoppingListAdapter.submitList(viewState.activityData)
            binding.totalPrice.text = viewModel.totalPrice
        }
    }

    override fun onDataReceived(item: ShoppingItemDTO) {
        viewModel.addNewShoppingItem(item)
        binding.totalPrice.text = viewModel.totalPrice

    }

    override fun onDeleteClicked(itemName: String) {
        viewModel.deleteShoppingItem(itemName)
        binding.totalPrice.text = viewModel.totalPrice
    }

    private fun showListView(show: Boolean) {
        binding.shoppingListRV.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(errorMessage: String) =
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()

    private val totalPriceUpdated : String
        get() = viewModel.totalPrice

    interface OnFragmentInteractionListener

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
        const val SHOPPING_LIST_SAMPLE_DATA = "shopping-list-sample-data.json"
    }
}