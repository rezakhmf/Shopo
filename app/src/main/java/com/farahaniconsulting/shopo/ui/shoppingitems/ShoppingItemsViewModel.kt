package com.farahaniconsulting.shopo.ui.shoppingitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farahaniconsulting.shopo.di.modules.OBSERVER_ON
import com.farahaniconsulting.shopo.domain.shoppingITems.GetShoppingItemsUC
import com.farahaniconsulting.shopo.ui.base.BaseViewModel
import com.farahaniconsulting.shopo.ui.data.PageErrorState
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Named

class ShoppingItemsViewModel(
    private val getShoppingItemsUC: GetShoppingItemsUC,
@Named(OBSERVER_ON) private val observerOn: Scheduler
) : BaseViewModel() {

    private val mutableViewState = MutableLiveData<ShoppingItemsContract.ViewState>()

    var content: String? = null

    val viewState: LiveData<ShoppingItemsContract.ViewState>
        get() = mutableViewState

    fun fetchShoppingItems() {

        mutableViewState.value  = ShoppingItemsContract.ViewState(isLoading = true, errorState = null)
        getShoppingItemsUC.content = this.content
        addDisposable(
            getShoppingItemsUC.run(GetShoppingItemsUC.RequestValues())
                .observeOn(observerOn)
                .subscribeWith(
                    object : DisposableSingleObserver<GetShoppingItemsUC.ResponseValue>() {
                        override fun onSuccess(apiResponse: GetShoppingItemsUC.ResponseValue) {
                            Timber.d("Shopping Items = ${apiResponse.shoppingItems.size}")
                            mutableViewState.value = ShoppingItemsContract.ViewState(
                                isLoading = false,
                                activityData = apiResponse.shoppingItems
                            )
                        }

                        override fun onError(error: Throwable) {
                            if (error is IOException) {
                                handleLoadingError(PageErrorState.NO_NETWORK)
                            } else if (error is HttpException) {
                                handleLoadingError(PageErrorState.SERVER_ERROR)
                            }
                            Timber.e(error)
                        }
                    }
                )
        )
    }

    private fun handleLoadingError(errorState: PageErrorState) {
        mutableViewState.value = ShoppingItemsContract.ViewState(
            isLoading = false,
            errorState = errorState
        )
    }
}