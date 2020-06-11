package com.garyfimo.unittestexample.domain.validador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion

interface ValidadorUseCase {

    suspend fun validarExpresion(expresion: String): ResultadoEvaluacion<Exception, Boolean>
}