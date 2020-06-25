package com.garyfimo.unittestexample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.garyfimo.unittestexample.domain.evaluador.EvaluadorUseCase
import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion
import com.garyfimo.unittestexample.ui.util.*
import kotlinx.coroutines.launch

class MainViewModel(private val evaluadorUseCase: EvaluadorUseCase) : ViewModel() {

    private var expresionOperacion = ""

    private val _operacion = MutableLiveData<String>()

    val operacion: LiveData<String> = _operacion

    private val _resultado = MutableLiveData<String>()

    val resultado: LiveData<String> = _resultado

    fun onClick(viewEvent: ViewEvent<Numeros, Operadores, Utilitarios>) {
        when (viewEvent) {
            is ViewEvent.ClickNumero -> agregarNumero(viewEvent.numero.valor)
            is ViewEvent.ClickOperador -> agregarOperador(viewEvent.operador.valor)
            is ViewEvent.ClickUtilitario -> agregarUtilitario(viewEvent.utilitario.valor)
            ViewEvent.ClickBorrar -> borrarUltimoCaracter()
            ViewEvent.ClickEvaluar -> evaluarExpresion()
            ViewEvent.ClickLimpiar -> limpiarExpresion()
        }
        _operacion.value = expresionOperacion
    }

    private fun borrarUltimoCaracter() {
        expresionOperacion = expresionOperacion.removerUltimoCaracter()
    }

    private fun limpiarExpresion() {
        expresionOperacion = ""
    }

    private fun evaluarExpresion() = viewModelScope.launch {
        when (val resultado = evaluadorUseCase.evaluarExpresion(expresionOperacion)) {
            is ResultadoEvaluacion.Valor -> mostrarResultado(resultado.valor)
            is ResultadoEvaluacion.Error -> controlarError(resultado.error)
        }
    }

    private fun controlarError(error: Exception) {
        if (error is ErrorEvaluacion.ErrorExpresionVacia)
            actualizarResultado("")

        if (error is ErrorEvaluacion.ErrorComputacional)
            actualizarResultado("No se puede dividir por cero")

        if (error is ErrorEvaluacion.ErrorValidacion)
            actualizarResultado("La operacion no es valida")
    }

    private fun mostrarResultado(resultado: String) {
        actualizarResultado(resultado.removerDecimalSiSonCeros())
    }

    private fun agregarOperador(operador: String) {
        expresionOperacion += operador
    }

    private fun agregarNumero(numero: String) {
        expresionOperacion += numero
    }

    private fun agregarUtilitario(utilitario: String) {
        expresionOperacion += utilitario
    }

    private fun actualizarResultado(resultado: String) {
        _resultado.value = resultado
    }
}