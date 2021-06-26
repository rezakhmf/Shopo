package com.farahaniconsulting.shopo.domain.shoppingITems

import com.farahaniconsulting.shopo.data.repository.ShopoRepository
import com.farahaniconsulting.shopo.domain.base.UseCase
import com.farahaniconsulting.shopo.dto.ShoppingItemDTO
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Named

class GetShoppingItemsUC(
    private val repository: ShopoRepository,
    backgroundScheduler: Scheduler
) :
    UseCase<GetShoppingItemsUC.RequestValues, GetShoppingItemsUC.ResponseValue>(backgroundScheduler) {

    var content: String? = null

    override fun executeUseCase(requestValues: RequestValues): Single<ResponseValue> =
         repository.getShoppingItems(content).map {
            val results = it.map { shoppingItem ->
                shoppingItem.toDTO().apply {

                }
            }.toList()
            ResponseValue(results)
        }



    class RequestValues : UseCase.RequestValues
    class ResponseValue(val shoppingItems: List<ShoppingItemDTO>) : UseCase.ResponseValue

}