package com.garyfimo.unittestexample.domain.evaluador

interface EvaluadorUseCase {

    suspend fun evaluarExpresion(expresion: String): ResultadoEvaluacion<Exception, String>
}