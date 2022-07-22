package com.gitrepos.main

import android.annotation.SuppressLint
import android.text.BoringLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrepos.domain.main.entity.ListEntity
import com.gitrepos.domain.main.usecase.ListItemsUseCase
import com.gitrepos.preferences.Preferences
import com.gitrepos.room.RepoDao
import com.gitrepos.utils.APIResult
import com.gitrepos.utils.applyIoToMainSchedulerOnSingle
import com.gitrepos.utils.subscribeToSingle
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val listItemsUseCase: ListItemsUseCase,
    private val repoDao: RepoDao,
    private val preferences: Preferences
): ViewModel() {

    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _listEntity: MutableLiveData<APIResult<List<ListEntity>>> = MutableLiveData()
    val listEntity: MutableLiveData<APIResult<List<ListEntity>>>
        get() = _listEntity


    init {

        if(isMoreThan2Hrs()){
            getRepos("octokit")
        } else {

            viewModelScope.launch {
                _listEntity.value = APIResult.success(repoDao.getRepos())
                _listEntity.value = APIResult.loading(false)

            }
        }

    }


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

        preferences.setString("date", convertDateToString(Date()))

        viewModelScope.launch {
            repoDao.clearRepoTable()
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

    @SuppressLint("SimpleDateFormat")
    fun convertDateToString(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            return dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringToDate(str: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            return format.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date()
    }

    private fun isMoreThan2Hrs(): Boolean {

        val diff: Long = Date().time - convertStringToDate(preferences.getString("date"))!!.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        if(hours > 2){
            return true
        }

        return false
    }

}