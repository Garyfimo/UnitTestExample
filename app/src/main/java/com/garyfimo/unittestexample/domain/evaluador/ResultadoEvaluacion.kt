package com.garyfimo.unittestexample.domain.evaluador

sealed class ResultadoEvaluacion<out E, out V> {

    data class Valor<out V>(val valor: V) : ResultadoEvaluacion<Nothing, V>()

    data class Error<out E>(val error: E) : ResultadoEvaluacion<E, Nothing>()

    companion object {
        inline fun <V> build(function: () -> V): ResultadoEvaluacion<Exception, V> =
            try {
                Valor(
                    function.invoke()
                )
            } catch (ex: Exception) {
                Error(
                    ex
                )
            }
    }
}