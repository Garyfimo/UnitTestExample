package com.garyfimo.unittestexample.domain.validador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

internal class ValidadorUseCaseImplTest {

    private val mensajeExpresionNoValida = "Expresion No Valida."

    private val expresionSimple = "4+3.2"
    private val expresionCompleja = "4+3.2*2+6.6/3-2"

    private val validadorUseCase by lazy {
        ValidadorUseCaseImpl()
    }

    @Test
    fun `Validar que expresion vacia no sea valida`() = runBlocking{
        val expresionVacia = ""
        val resultado = validadorUseCase.validarExpresion(expresionVacia)
        if(resultado is ResultadoEvaluacion.Valor)
            assertFalse(resultado.valor)
        else
            assertFalse(true)
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
    fun `Validar que expresion no empiece con operador`() = runBlocking {
        val expresionMultiplicacion = "*9"
        val resultadoMultiplicacion = validadorUseCase.validarExpresion(expresionMultiplicacion)
        if (resultadoMultiplicacion is ResultadoEvaluacion.Error)
            assertTrue(resultadoMultiplicacion.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)

        val expresionDivision = "/9"
        val resultadoDivision = validadorUseCase.validarExpresion(expresionDivision)
        if (resultadoDivision is ResultadoEvaluacion.Error)
            assertTrue(resultadoDivision.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)

        val expresionSuma = "+9"
        val resultadoSuma = validadorUseCase.validarExpresion(expresionSuma)
        if (resultadoSuma is ResultadoEvaluacion.Error)
            assertTrue(resultadoSuma.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)

        val expresionResta = "-9"
        val resultadoResta = validadorUseCase.validarExpresion(expresionResta)
        if (resultadoResta is ResultadoEvaluacion.Error)
            assertTrue(resultadoResta.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no termine con operador`() = runBlocking {
        val expresionMultiplicacion = "9*"
        val resultadoMultiplicacion = validadorUseCase.validarExpresion(expresionMultiplicacion)
        if (resultadoMultiplicacion is ResultadoEvaluacion.Error)
            assertTrue(resultadoMultiplicacion.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)

        val expresionDivision = "9/"
        val resultadoDivision = validadorUseCase.validarExpresion(expresionDivision)
        if (resultadoDivision is ResultadoEvaluacion.Error)
            assertTrue(resultadoDivision.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)

        val expresionSuma = "9+"
        val resultadoSuma = validadorUseCase.validarExpresion(expresionSuma)
        if (resultadoSuma is ResultadoEvaluacion.Error)
            assertTrue(resultadoSuma.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)

        val expresionResta = "9-"
        val resultadoResta = validadorUseCase.validarExpresion(expresionResta)
        if (resultadoResta is ResultadoEvaluacion.Error)
            assertTrue(resultadoResta.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no tenga dos puntos continuos`() = runBlocking {
        val expresionConDosPuntosContinuos = "0..9"
        val resultado = validadorUseCase.validarExpresion(expresionConDosPuntosContinuos)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no tenga dos operadores continuos`() = runBlocking {
        val expresionConDosOperadoresContinuos = "3*/3"
        val resultado = validadorUseCase.validarExpresion(expresionConDosOperadoresContinuos)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no empiece con punto`() = runBlocking {
        val expresionEmpiezaConPunto = ".9"
        val resultado = validadorUseCase.validarExpresion(expresionEmpiezaConPunto)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no termine con punto`() = runBlocking {
        val expresionTerminaConPunto = "9."
        val resultado = validadorUseCase.validarExpresion(expresionTerminaConPunto)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no tenga punto antes de operador`() = runBlocking {
        val expresionConPuntoAntesDeOperador = "2./0"
        val resultado = validadorUseCase.validarExpresion(expresionConPuntoAntesDeOperador)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }

    @Test
    fun `Validar que expresion no tenga punto despues de operador`() = runBlocking {
        val expresionConPuntoDespuesDeOperador = "2/.0"
        val resultado = validadorUseCase.validarExpresion(expresionConPuntoDespuesDeOperador)
        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error.message == mensajeExpresionNoValida)
        else
            assertTrue(false)
    }
}