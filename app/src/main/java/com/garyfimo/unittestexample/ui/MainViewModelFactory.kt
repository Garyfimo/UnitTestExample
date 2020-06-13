package com.garyfimo.unittestexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garyfimo.unittestexample.domain.evaluador.EvaluadorUseCase

class MainViewModelFactory(
    private val evaluadorUseCase: EvaluadorUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(evaluadorUseCase) as T
    }
}