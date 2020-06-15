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
            if (it < expresion.lastIndex) {
                if (esPuntoDecimalAntesDeOperador(expresion[it], expresion[it + 1]))
                    return true
            }
        }
        return false
    }

    private fun esPuntoDecimalAntesDeOperador(actual: Char, siguiente: Char): Boolean {
        return esPuntoDecimal(actual) and esOperador(siguiente)
    }

    private fun tienePuntoDecimalDespuesDeOperador(expresion: String): Boolean {
        expresion.indices.forEach {
            if (it < expresion.lastIndex) {
                if (esPuntoDecimalDespuesDeOperador(expresion[it], expresion[it + 1]))
                    return true
            }
        }
        return false
    }

    private fun esPuntoDecimalDespuesDeOperador(actual: Char, siguiente: Char): Boolean {
        return esOperador(actual) and esPuntoDecimal(siguiente)
    }

    private fun tieneDosPuntosDecimalesContinuos(expresion: String): Boolean {
        expresion.indices.forEach {
            if (it < expresion.lastIndex) {
                if (esPuntoDecimalContinuo(expresion[it], expresion[it + 1]))
                    return true
            }
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
            if (it < expresion.lastIndex) {
                if (esOperadorContinuo(expresion[it], expresion[it + 1]))
                    return true
            }
        }
        return false
    }

    private fun esOperadorContinuo(actual: Char, siguiente: Char): Boolean {
        return esOperador(actual) and esOperador(siguiente)
    }

    private fun esOperador(operador: Char): Boolean {
        return when (operador) {
            '*' -> true
            '/' -> true
            '+' -> true
            '-' -> true
            else -> false

        }
    }

    private fun finNoValido(expresion: String): Boolean {
        return when {
            expresion.endsWith("*") -> true
            expresion.endsWith("/") -> true
            expresion.endsWith("+") -> true
            expresion.endsWith("-") -> true
            expresion.endsWith(".") -> true
            else -> false
        }
    }

    private fun comienzoNoValido(expresion: String): Boolean {
        return when {
            expresion.startsWith("*") -> true
            expresion.startsWith("/") -> true
            expresion.startsWith("+") -> true
            expresion.startsWith("-") -> true
            expresion.startsWith(".") -> true
            else -> false
        }
    }
}