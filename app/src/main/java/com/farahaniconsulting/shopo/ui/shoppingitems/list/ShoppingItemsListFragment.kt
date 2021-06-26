package com.farahaniconsulting.shopo.ui.shoppingitems.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.farahaniconsulting.shopo.databinding.FragmentShoppingListBinding
import com.farahaniconsulting.shopo.di.modules.ViewModelFactory
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemsContract
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemsViewModel
import com.farahaniconsulting.shopo.ui.shoppingitems.list.adapter.ShoppingListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import java.lang.RuntimeException
import javax.inject.Inject

class ShoppingItemsListFragment : Fragment(), HasAndroidInjector {

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
        viewModel.fetchShoppingItems()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        shoppingListAdapter = ShoppingListAdapter {
            //call bottom navigation for delete the item
        }
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.shoppingListRV.adapter = shoppingListAdapter
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
        }
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun showListView(show: Boolean) {
        binding.shoppingListRV.visibility = if (show) View.VISIBLE else View.GONE
    }

    interface OnFragmentInteractionListener

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}