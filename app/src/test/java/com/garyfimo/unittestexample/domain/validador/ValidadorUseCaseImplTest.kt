package com.garyfimo.unittestexample.domain.validador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import com.garyfimo.unittestexample.domain.util.expresionNoValida
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

internal class ValidadorUseCaseImplTest {

    private val expresionSimple = "4+3.2"
    private val expresionCompleja = "4+3.2*2+6.6/3-2"

    private val primeraExpresionNoValida = "9+"
    private val segundaExpresionNoValida = "-9"
    private val terceraExpresionNoValida = "9..0+1"
    private val cuartaExpresionNoValida = "3*/3"
    private val quintaExpresionNoValida = "."
    private val sextaExpresionNoValida = "2/.0"
    private val septimaExpresionNoValida = "2./0"

    private val validadorUseCase by lazy {
        ValidadorUseCaseImpl()
    }

    @Test
    fun `Validar expresion simple`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(expresionSimple)
        if (resultado is ResultadoEvaluacion.Valor)
            assertTrue(resultado.valor)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar expresion compleja`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(expresionCompleja)
        if (resultado is ResultadoEvaluacion.Valor)
            assertTrue(resultado.valor)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar primera expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(primeraExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }

    @Test
    fun `Validar segunda expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(segundaExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }

    @Test
    fun `Validar tercera expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(terceraExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }

    @Test
    fun `Validar cuarta expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(cuartaExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }

    @Test
    fun `Validar quinta expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(quintaExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }

    @Test
    fun `Validar sexta expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(sextaExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }

    @Test
    fun `Validar septima expresion no valida`() = runBlocking {
        val resultado = validadorUseCase.validarExpresion(septimaExpresionNoValida)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == expresionNoValida)
    }
}