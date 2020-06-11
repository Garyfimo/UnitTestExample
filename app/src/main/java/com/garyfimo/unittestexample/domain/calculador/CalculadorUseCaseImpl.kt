package com.garyfimo.unittestexample.domain.calculador

import androidx.annotation.VisibleForTesting
import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion
import java.lang.IllegalArgumentException

class CalculadorUseCaseImpl : CalculadorUseCase {

    override suspend fun evaluarExpresion(expresion: String): ResultadoEvaluacion<Exception, String> {
        val operadores = getOperadores(expresion)
        val operandos = getOperandos(expresion)

        while (operandos.size > 1) {
            val primerOperando = operandos[0]
            val segundoOperando = operandos[1]
            val primerOperador = operadores[0]
            if (primerOperador.evaluarPrimero ||
                operadores.elementAtOrNull(1) == null ||
                !operadores[1].evaluarPrimero
            ) {
                val resultado = ejecutarOperacion(
                    primerOperando.valor,
                    primerOperador.valor,
                    segundoOperando.valor
                )

                operandos.remove(primerOperando)
                operandos.remove(segundoOperando)
                operadores.remove(primerOperador)

                operandos.add(0, Operando(resultado.toString()))
            } else {
                val tercerOperando = operandos[2]
                val segundoOperador = operadores[1]
                val resultado = ejecutarOperacion(
                    segundoOperando.valor,
                    segundoOperador.valor,
                    tercerOperando.valor
                )

                operandos.remove(segundoOperando)
                operandos.remove(tercerOperando)
                operadores.remove(segundoOperador)

                operandos.add(1, Operando(resultado.toString()))
            }
        }

        return ResultadoEvaluacion.build { operandos.first().valor }
    }

    @VisibleForTesting
    internal fun sumar(primerOperando: Double, segundoOperando: Double): Double {
        return primerOperando + segundoOperando
    }

    @VisibleForTesting
    internal fun restar(primerOperando: Double, segundoOperando: Double): Double {
        return primerOperando - segundoOperando
    }

    @VisibleForTesting
    internal fun multiplicar(primerOperando: Double, segundoOperando: Double): Double {
        return primerOperando * segundoOperando
    }

    @VisibleForTesting
    internal fun dividir(primerOperando: Double, segundoOperando: Double): Double {
        if (segundoOperando == 0.0)
            throw ErrorEvaluacion.ErrorComputacional()
        return primerOperando / segundoOperando
    }

    @VisibleForTesting
    internal fun getOperandos(expresion: String): MutableList<Operando> {
        return expresion.split("+", "-", "/", "*")
            .map { Operando(it) }
            .toMutableList()
    }

    @VisibleForTesting
    internal fun getOperadores(expresion: String): MutableList<Operador> {
        val expresionRegular = "\\d+(?:\\.\\d+)?".toRegex()
        return expresion.split(expresionRegular)
            .filterNot { it == "" }
            .map { Operador(it) }
            .toMutableList()
    }

    @VisibleForTesting
    internal fun ejecutarOperacion(
        primerOperando: String,
        operador: String,
        segundoOperando: String
    ): Double {
        return when (operador) {
            "*" -> multiplicar(primerOperando.toDouble(), segundoOperando.toDouble())
            "/" -> dividir(primerOperando.toDouble(), segundoOperando.toDouble())
            "+" -> sumar(primerOperando.toDouble(), segundoOperando.toDouble())
            "-" -> restar(primerOperando.toDouble(), segundoOperando.toDouble())
            else -> throw IllegalArgumentException("Operador no reconocido")
        }
    }
}