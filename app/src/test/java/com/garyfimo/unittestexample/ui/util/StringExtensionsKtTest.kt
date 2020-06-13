package com.garyfimo.unittestexample.ui.util

import org.junit.Test
import kotlin.test.assertEquals

internal class StringExtensionsKtTest{


    @Test
    fun `Verificar que remueva ceros decimales`(){
        val valorEntrada = "1.0"
        val valorSalida = "1"
        assertEquals(valorEntrada.removerDecimalSiSonCeros(), valorSalida)
    }

    @Test
    fun `Verificar que no remueva decimales si no son ceros`(){
        val valorEntrada = "1.24"
        val valorSalida = "1.24"
        assertEquals(valorEntrada.removerDecimalSiSonCeros(), valorSalida)
    }

    @Test
    fun `Verificar que se elimine el ultimo caracter`(){
        val valorEntrada = "1.24"
        val valorSalida = "1.2"
        assertEquals(valorEntrada.removerUltimoCaracter(), valorSalida)
    }
}