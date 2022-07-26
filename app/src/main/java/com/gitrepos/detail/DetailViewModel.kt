package com.gitrepos.detail

import androidx.lifecycle.ViewModel
import com.gitrepos.domain.main.entity.ListEntity
import javax.inject.Inject

class DetailViewModel @Inject constructor(): ViewModel() {

    lateinit var listEntity: ListEntity

}