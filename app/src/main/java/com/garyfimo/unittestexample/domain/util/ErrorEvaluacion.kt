package com.garyfimo.unittestexample.domain.util

sealed class ErrorEvaluacion(mensaje: String? = null) : Exception(mensaje) {
    class ErrorComputacional : ErrorEvaluacion("Error computacional.")
    class ErrorValidacion : ErrorEvaluacion("Expresion No Valida.")
    class ErrorExpresionVacia : ErrorEvaluacion("Expresion vacia.")
}