package com.garyfimo.unittestexample.domain.evaluador

import com.garyfimo.unittestexample.domain.calculador.CalculadorUseCase
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion
import com.garyfimo.unittestexample.domain.validador.ValidadorUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class EvaluadorUseCaseImplTest {

    private val validarUseCase: ValidadorUseCase = mockk()
    private val calculadorUseCase: CalculadorUseCase = mockk()

    private val evaluadorUseCase by lazy {
        EvaluadorUseCaseImpl(validarUseCase, calculadorUseCase)
    }

    private val expresionCorrecta = "4+3.2*2+6.6/3-2"
    private val respuestaEsperada = "10.6"

    private val expresionNoCorrecta = "4+3..2*2+6.6/3-2"
    private val expresionNoCorrectaDivididoPorCero = "4+3.2*2+6.6/0-2"

    @Test
    fun `Evalua expresion correcta y devuelve resultado`() = runBlocking {
        coEvery { validarUseCase.validarExpresion(expresionCorrecta) } returns ResultadoEvaluacion.build { true }
        coEvery { calculadorUseCase.evaluarExpresion(expresionCorrecta) } returns ResultadoEvaluacion.build { respuestaEsperada }

        val resultado = evaluadorUseCase.evaluarExpresion(expresionCorrecta)

        coVerify { validarUseCase.validarExpresion(expresionCorrecta) }
        coVerify { calculadorUseCase.evaluarExpresion(expresionCorrecta) }

        if (resultado is ResultadoEvaluacion.Valor)
            assertEquals(resultado.valor, respuestaEsperada)
        else
            assertTrue(false)
    }

    @Test
    fun `Evalua expresion no correcta y lanza error de validacion`() = runBlocking {
        coEvery { validarUseCase.validarExpresion(expresionNoCorrecta) } returns ResultadoEvaluacion.build { throw ErrorEvaluacion.ErrorValidacion() }

        val resultado = evaluadorUseCase.evaluarExpresion(expresionNoCorrecta)

        coVerify { validarUseCase.validarExpresion(expresionNoCorrecta) }
        coVerify(exactly = 0) { calculadorUseCase.evaluarExpresion(expresionNoCorrecta) }

        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error is ErrorEvaluacion.ErrorValidacion)
        else
            assertTrue(false)
    }

    @Test
    fun `Evalua expresion correcta pero con divisor 0 y lanza error computacional`() = runBlocking {
        coEvery { validarUseCase.validarExpresion(expresionNoCorrectaDivididoPorCero) } returns ResultadoEvaluacion.build { true }
        coEvery { calculadorUseCase.evaluarExpresion(expresionNoCorrectaDivididoPorCero) } returns ResultadoEvaluacion.build { throw ErrorEvaluacion.ErrorComputacional() }

        val resultado = evaluadorUseCase.evaluarExpresion(expresionNoCorrectaDivididoPorCero)

        coVerify { validarUseCase.validarExpresion(expresionNoCorrectaDivididoPorCero) }
        coVerify { calculadorUseCase.evaluarExpresion(expresionNoCorrectaDivididoPorCero) }

        if (resultado is ResultadoEvaluacion.Error)
            assertTrue(resultado.error is ErrorEvaluacion.ErrorComputacional)
        else
            assertTrue(false)
    }
}