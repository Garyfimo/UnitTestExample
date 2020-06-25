package com.garyfimo.unittestexample.domain.evaluador

import com.garyfimo.unittestexample.domain.calculador.CalculadorUseCase
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion
import com.garyfimo.unittestexample.domain.validador.ValidadorUseCase

class EvaluadorUseCaseImpl(
    private val validarUseCase: ValidadorUseCase,
    private val calculadorUseCase: CalculadorUseCase
) : EvaluadorUseCase {

    override suspend fun evaluarExpresion(expresion: String): ResultadoEvaluacion<Exception, String> {
        return when (val validarExpresion = validarUseCase.validarExpresion(expresion)) {
            is ResultadoEvaluacion.Valor -> evaluarValidacion(validarExpresion.valor, expresion)
            is ResultadoEvaluacion.Error -> validarExpresion
        }
    }

    private suspend fun evaluarValidacion(esValida: Boolean, expresion: String)
            : ResultadoEvaluacion<Exception, String> {
        if (esValida)
            return calculadorUseCase.evaluarExpresion(expresion)
        return ResultadoEvaluacion.build { throw ErrorEvaluacion.ErrorExpresionVacia() }
    }
}