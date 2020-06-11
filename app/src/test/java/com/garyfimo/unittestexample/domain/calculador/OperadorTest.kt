package com.garyfimo.unittestexample.domain.calculador

import org.junit.Test
import kotlin.test.assertFailsWith

internal class OperadorTest {

    @Test
    fun `Lanza error cuando operador es no valido`() {
        val operadorNoValido = "&"
        assertFailsWith<IllegalArgumentException> {
            Operador(operadorNoValido).evaluarPrimero
        }
    }
}