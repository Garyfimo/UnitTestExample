package com.garyfimo.unittestexample.domain.calculador

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.assertFailsWith

internal class OperadorTest {

    @Test
    fun `Lanza error cuando operador es no valido`() {
        val operadorNoValido = "&"
        assertFailsWith<IllegalArgumentException> {
            Operador(operadorNoValido).evaluarPrimero
        }
    }

    @Test
    fun `Verifica prioridad de los operadores`() {
        val operadores = arrayOf("*", "/", "+", "-")

        assertTrue(Operador(operadores[0]).evaluarPrimero)
       // assertTrue(Operador(operadores[1]).evaluarPrimero)
       // assertFalse(Operador(operadores[2]).evaluarPrimero)
       // assertFalse(Operador(operadores[3]).evaluarPrimero)

    }
}