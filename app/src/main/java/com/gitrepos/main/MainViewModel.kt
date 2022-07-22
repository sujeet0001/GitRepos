package com.gitrepos.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrepos.domain.main.entity.ListEntity
import com.gitrepos.domain.main.usecase.ListItemsUseCase
import com.gitrepos.room.RepoDao
import com.gitrepos.utils.APIResult
import com.gitrepos.utils.applyIoToMainSchedulerOnSingle
import com.gitrepos.utils.subscribeToSingle
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val listItemsUseCase: ListItemsUseCase,
    private val repoDao: RepoDao
): ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _listEntity: MutableLiveData<APIResult<List<ListEntity>>> = MutableLiveData()
    val listEntity: MutableLiveData<APIResult<List<ListEntity>>>
        get() = _listEntity

    fun getRepos(name: String){
        _listEntity.value = APIResult.loading(true)
        compositeDisposable.add(
            listItemsUseCase.execute(name)
                .applyIoToMainSchedulerOnSingle()
                .subscribeToSingle(this::onSuccess, this::onErrorKYCData)
        )
        listItemsUseCase.execute(name)
    }

    private fun onSuccess(list: List<ListEntity>) {

        viewModelScope.launch {
            repoDao.addRepos(list)
        }

        _listEntity.value = APIResult.success(list)
        _listEntity.value = APIResult.loading(false)
    }

    private fun onErrorKYCData(e: Throwable) {
        _listEntity.value = APIResult.apiError(e)
        _listEntity.value = APIResult.loading(false)
    }

    override fun onCleared() {
        if (compositeDisposable.isDisposed.not())
            compositeDisposable.dispose()
        super.onCleared()
        compositeDisposable.clear()
    }

}