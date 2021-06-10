package com.farahaniconsulting.shopo.domain.shoppingITems

import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.domain.base.UseCase
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import io.reactivex.Scheduler
import io.reactivex.Single

class GetShoppingItemsUC(
    private val repository: ShopoRepository,
    backgroundScheduler: Scheduler
) :
    UseCase<GetShoppingItemsUC.RequestValues, GetShoppingItemsUC.ResponseValue>(backgroundScheduler) {

    override fun executeUseCase(requestValues: RequestValues): Single<ResponseValue> =
         repository.getShoppingItems().map {
            val results = it.map { shoppingItem ->
                shoppingItem.toDTO().apply {

                }
            }.toList()
            ResponseValue(results)
        }



    class RequestValues : UseCase.RequestValues
    class ResponseValue(val shoppingItems: List<ShoppingItemDTO>) : UseCase.ResponseValue

}