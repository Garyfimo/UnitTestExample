package com.garyfimo.unittestexample.domain.util

const val expresionNoValida = "Expresion No Valida."
const val errorComputacional = "Error computacional."

sealed class ErrorEvaluacion(mensaje: String? = null) : Exception(mensaje) {
    class ErrorComputacional : ErrorEvaluacion(errorComputacional)
    class ErrorValidacion : ErrorEvaluacion(expresionNoValida)
}