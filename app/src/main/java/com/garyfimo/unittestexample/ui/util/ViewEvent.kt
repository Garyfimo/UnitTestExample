package com.garyfimo.unittestexample.ui.util

sealed class ViewEvent<out N, out O, out U> {
    object ClickEvaluar : ViewEvent<Nothing, Nothing, Nothing>()
    object ClickBorrar : ViewEvent<Nothing, Nothing, Nothing>()
    object ClickLimpiar : ViewEvent<Nothing, Nothing, Nothing>()
    data class ClickNumero<out N>(val numero: Numeros) : ViewEvent<N, Nothing, Nothing>()
    data class ClickOperador<out O>(val operador: Operadores) : ViewEvent<Nothing, O, Nothing>()
    data class ClickUtilitario<out U>(val utilitario: Utilitarios) :
        ViewEvent<Nothing, Nothing, U>()
}