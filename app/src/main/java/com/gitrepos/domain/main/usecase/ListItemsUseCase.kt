package com.gitrepos.domain.main.usecase

import com.gitrepos.data.main.repository.MainActivityRepo
import com.gitrepos.domain.main.entity.ListEntity
import com.gitrepos.utils.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class ListItemsUseCase @Inject constructor(private val repo: MainActivityRepo) :
    SingleUseCase<List<ListEntity>, String> {

    override fun execute(param: String): Single<List<ListEntity>> {
        return repo.callAPI(param)
    }

}