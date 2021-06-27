package com.farahaniconsulting.shopo.ui.shoppingitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.farahaniconsulting.shopo.di.modules.OBSERVER_ON
import com.farahaniconsulting.shopo.domain.shoppingITems.GetShoppingItemsUC
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
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

    private var shoppingItems: List<ShoppingItemDTO> = mutableListOf()

    var content: String? = null

    lateinit var totalPrice: String

    val viewState: LiveData<ShoppingItemsContract.ViewState>
        get() = mutableViewState

    fun fetchShoppingItems() {

        mutableViewState.value  = ShoppingItemsContract.ViewState(
            isLoading = true, errorState = null
        )
        getShoppingItemsUC.content = this.content
        addDisposable(
            getShoppingItemsUC.run(GetShoppingItemsUC.RequestValues())
                .observeOn(observerOn)
                .subscribeWith(
                    object : DisposableSingleObserver<GetShoppingItemsUC.ResponseValue>() {
                        override fun onSuccess(apiResponse: GetShoppingItemsUC.ResponseValue) {
                            Timber.d("Shopping Items = ${apiResponse.shoppingItems.size}")
                            totalPrice = totalCalculator(apiResponse.shoppingItems)

                            shoppingItems += apiResponse.shoppingItems

                            mutableViewState.value = ShoppingItemsContract.ViewState(
                                isLoading = false,
                                activityData = shoppingItems
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

    fun addNewShoppingItem(item: ShoppingItemDTO) {
        shoppingItems += item
        totalPrice = totalCalculator(shoppingItems)
        mutableViewState.value = ShoppingItemsContract.ViewState(
            isLoading = false,
            activityData = shoppingItems
        )
    }

    fun deleteShoppingItem(itemName: String) {
        shoppingItems = shoppingItems.filter { it.name != itemName }
        totalPrice = totalCalculator(shoppingItems)
        mutableViewState.value = ShoppingItemsContract.ViewState(
            isLoading = false,
            activityData = shoppingItems
        )
    }

    private fun handleLoadingError(errorState: PageErrorState) {
        mutableViewState.value = ShoppingItemsContract.ViewState(
            isLoading = false,
            errorState = errorState
        )
    }

    private fun totalCalculator(shoppingItems: List<ShoppingItemDTO>) =
        "$" + shoppingItems.map { it.price }.sumByDouble { it?.removePrefix("$")?.toDouble()!! }.toString()
}