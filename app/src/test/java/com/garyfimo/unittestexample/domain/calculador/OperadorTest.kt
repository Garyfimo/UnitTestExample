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
    fun `Verifica que multiplicacion sea prioritario`() {
        val operadorMultiplicacion = "*"

        assertTrue(Operador(operadorMultiplicacion).evaluarPrimero)
    }

    @Test
    fun `Verifica que division sea prioritario`() {
        val operadorDivision = "/"

        assertTrue(Operador(operadorDivision).evaluarPrimero)
    }

    @Test
    fun `Verifica que suma no sea prioritario`() {
        val operadorSuma = "+"

        assertFalse(Operador(operadorSuma).evaluarPrimero)
    }

    @Test
    fun `Verifica que resta no sea prioritario`() {
        val operadorResta = "-"

        assertFalse(Operador(operadorResta).evaluarPrimero)
    }
}