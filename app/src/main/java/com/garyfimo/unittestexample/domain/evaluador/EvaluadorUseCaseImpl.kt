package com.garyfimo.unittestexample.domain.evaluador

import com.garyfimo.unittestexample.domain.calculador.CalculadorUseCase
import com.garyfimo.unittestexample.domain.validador.ValidadorUseCase

class EvaluadorUseCaseImpl(
    private val validarUseCase: ValidadorUseCase,
    private val calculadorUseCase: CalculadorUseCase
) : EvaluadorUseCase {

    override suspend fun evaluarExpresion(expresion: String): ResultadoEvaluacion<Exception, String> {

        return when (val validarExpresion = validarUseCase.validarExpresion(expresion)) {
            is ResultadoEvaluacion.Valor -> calculadorUseCase.evaluarExpresion(expresion)
            is ResultadoEvaluacion.Error -> validarExpresion
        }
    }
}