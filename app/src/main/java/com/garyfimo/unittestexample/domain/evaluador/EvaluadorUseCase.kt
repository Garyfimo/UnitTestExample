package com.garyfimo.unittestexample.domain.evaluador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import java.lang.Exception

interface EvaluadorUseCase {

    suspend fun evaluarExpresion(expresion: String): ResultadoEvaluacion<Exception, String>
}