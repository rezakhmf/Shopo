package com.farahaniconsulting.shopo.ui.shoppingitems.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.farahaniconsulting.shopo.di.modules.ViewModelFactory
import com.farahaniconsulting.shopo.ui.shoppingitems.ShoppingItemsViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ShoppingItemsListFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private lateinit var viewModel: ShoppingItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactory).get(ShoppingItemsViewModel::class.java)
        viewModel.fetchShoppingItems()
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(fragmentBinding.root, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun showListView(show: Boolean) {
        fragmentBinding.launchEventRecyclerView.visibility = if (show) View.VISIBLE else View.GONE
    }

    interface OnFragmentInteractionListener

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}