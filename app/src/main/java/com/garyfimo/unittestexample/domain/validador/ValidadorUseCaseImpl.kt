package com.garyfimo.unittestexample.domain.validador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion

class ValidadorUseCaseImpl : ValidadorUseCase {

    override suspend fun validarExpresion(expresion: String): ResultadoEvaluacion<Exception, Boolean> {

        if (comienzoNoValido(expresion))
            return ResultadoEvaluacion.build { throw  ErrorEvaluacion.ErrorValidacion() }

        if (finNoValido(expresion))
            return ResultadoEvaluacion.build { throw  ErrorEvaluacion.ErrorValidacion() }

        if (tieneOperadoresContinuos(expresion))
            return ResultadoEvaluacion.build { throw  ErrorEvaluacion.ErrorValidacion() }

        if (tieneDosPuntosDecimalesContinuos(expresion))
            return ResultadoEvaluacion.build { throw  ErrorEvaluacion.ErrorValidacion() }

        if (tienePuntoDecimalDespuesDeOperador(expresion))
            return ResultadoEvaluacion.build { throw  ErrorEvaluacion.ErrorValidacion() }

        if (tienePuntoDecimalAntesDeOperador(expresion))
            return ResultadoEvaluacion.build { throw  ErrorEvaluacion.ErrorValidacion() }

        return ResultadoEvaluacion.build { true }
    }

    private fun tienePuntoDecimalAntesDeOperador(expresion: String): Boolean {
        expresion.indices.forEach {
            if (it < expresion.lastIndex &&
                esPuntoDecimalAntesDeOperador(expresion[it], expresion[it + 1])
            )
                return true
        }
        return false
    }

    private fun esPuntoDecimalAntesDeOperador(actual: Char, siguiente: Char): Boolean {
        return esPuntoDecimal(actual) and esOperador(siguiente)
    }

    private fun tienePuntoDecimalDespuesDeOperador(expresion: String): Boolean {
        expresion.indices.forEach {
            if (it < expresion.lastIndex &&
                esPuntoDecimalDespuesDeOperador(expresion[it], expresion[it + 1])
            )
                return true
        }
        return false
    }

    private fun esPuntoDecimalDespuesDeOperador(actual: Char, siguiente: Char): Boolean {
        return esOperador(actual) and esPuntoDecimal(siguiente)
    }

    private fun tieneDosPuntosDecimalesContinuos(expresion: String): Boolean {
        expresion.indices.forEach {
            if (it < expresion.lastIndex &&
                esPuntoDecimalContinuo(expresion[it], expresion[it + 1])
            )
                return true
        }
        return false
    }

    private fun esPuntoDecimalContinuo(actual: Char, siguiente: Char): Boolean {
        return esPuntoDecimal(actual) and esPuntoDecimal(siguiente)
    }

    private fun esPuntoDecimal(actual: Char): Boolean {
        return actual == '.'
    }

    private fun tieneOperadoresContinuos(expresion: String): Boolean {
        expresion.indices.forEach {
            if (it < expresion.lastIndex &&
                esOperadorContinuo(expresion[it], expresion[it + 1])
            )
                return true
        }
        return false
    }

    private fun esOperadorContinuo(actual: Char, siguiente: Char): Boolean {
        return esOperador(actual) and esOperador(siguiente)
    }

    private fun esOperador(operador: Char): Boolean {
        return operador in arrayOf('*', '/', '+', '-')
    }

    private fun finNoValido(expresion: String): Boolean {
        return expresion.last() in arrayOf('*', '/', '+', '-', '.')
    }

    private fun comienzoNoValido(expresion: String): Boolean {
        return expresion.first() in arrayOf('*', '/', '+', '-', '.')
    }
}