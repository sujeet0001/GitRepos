package com.gitrepos.main

import androidx.lifecycle.ViewModel
import com.gitrepos.domain.main.usecase.ListItemsUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val useCase: ListItemsUseCase,
): ViewModel() {



}