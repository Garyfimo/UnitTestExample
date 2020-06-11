package com.garyfimo.unittestexample.domain.calculador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion

interface CalculadorUseCase {

    suspend fun evaluarExpresion(expresion: String): ResultadoEvaluacion<Exception, String>
}