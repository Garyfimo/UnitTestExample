package com.garyfimo.unittestexample.domain.calculador

import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import java.lang.IllegalArgumentException
import kotlin.test.assertFailsWith

@DisplayName("Calculador Unit Test")
internal class CalculadorUseCaseImplTest {

    private val expresionSimple = "4+3.2"

    private val expresionCompleja = "4+3.2*2+6.6/3-2"

    private val operandos = listOf(
        Operando(valor = "4"),
        Operando(valor = "3.2"),
        Operando(valor = "2"),
        Operando(valor = "6.6"),
        Operando(valor = "3"),
        Operando(valor = "2")
    )

    private val operadores = listOf(
        Operador(valor = "+"),
        Operador(valor = "*"),
        Operador(valor = "+"),
        Operador(valor = "/"),
        Operador(valor = "-")
    )

    private val calculadorUseCase by lazy {
        CalculadorUseCaseImpl()
    }

    @Test
    fun `Evalua expresion division con segundo operando cero`() = runBlocking {
        val expresionNoValida = "1/0"
        val resultadoObtenido = calculadorUseCase.evaluarExpresion(expresionNoValida)

        if (resultadoObtenido is ResultadoEvaluacion.Error)
            assertTrue(resultadoObtenido.error is ErrorEvaluacion.ErrorComputacional)
        else
            assertTrue(false)
    }

    @Test
    fun `Evalua expresion simple`() = runBlocking {
        val resultadoEsperado = "7.2"
        val resultadoObtenido = calculadorUseCase.evaluarExpresion(expresionSimple)

        if (resultadoObtenido is ResultadoEvaluacion.Valor)
            assertTrue(resultadoObtenido.valor == resultadoEsperado)
        else
            assertTrue(false)
    }

    @Test
    fun `Evalua expresion compleja`() = runBlocking {
        val resultadoEsperado = "10.6"
        val resultadoObtenido = calculadorUseCase.evaluarExpresion(expresionCompleja)

        if (resultadoObtenido is ResultadoEvaluacion.Valor)
            assertTrue(resultadoObtenido.valor == resultadoEsperado)
        else
            assertTrue(false)
    }

    @Test
    fun `Obtener lista de operandos`() {
        val resultado: List<Operando> = calculadorUseCase.getOperandos(expresionCompleja)
        assert(resultado == operandos)
    }

    @Test
    fun `Obtener lista de operadores`() {
        val resultado: List<Operador> = calculadorUseCase.getOperadores(expresionCompleja)
        assert(resultado == operadores)
    }

    @Test
    fun `Ejecuta operador suma`() {
        val operador = "+"
        val primerOperando = "1"
        val segundoOperando = "4"
        val resultadoEsperado = 5.0
        assertTrue(
            resultadoEsperado == calculadorUseCase.ejecutarOperacion(
                primerOperando,
                operador,
                segundoOperando
            )
        )
    }

    @Test
    fun `Ejecuta operador resta`() {
        val operador = "-"
        val primerOperando = "1"
        val segundoOperando = "4"
        val resultadoEsperado = -3.0
        assertTrue(
            resultadoEsperado == calculadorUseCase.ejecutarOperacion(
                primerOperando,
                operador,
                segundoOperando
            )
        )
    }

    @Test
    fun `Ejecuta operador mutiplicacion`() {
        val operador = "*"
        val primerOperando = "1"
        val segundoOperando = "4"
        val resultadoEsperado = 4.0
        assertTrue(
            resultadoEsperado == calculadorUseCase.ejecutarOperacion(
                primerOperando,
                operador,
                segundoOperando
            )
        )
    }

    @Test
    fun `Ejecuta operador division`() {
        val operador = "/"
        val primerOperando = "1"
        val segundoOperando = "4"
        val resultadoEsperado = 0.25
        assertTrue(
            resultadoEsperado == calculadorUseCase.ejecutarOperacion(
                primerOperando,
                operador,
                segundoOperando
            )
        )
    }

    @Test
    fun `Ejecuta operador no correcto`() {
        val operador = "&"
        val primerOperando = "1"
        val segundoOperando = "4"
        assertFailsWith<IllegalArgumentException> {
            calculadorUseCase.ejecutarOperacion(
                primerOperando,
                operador,
                segundoOperando
            )
        }
    }

    @Test
    fun `Realiza suma correctamente`() {
        val respuestaEsperada = 4.0
        val primerOperando = 1.0
        val segundoOperando = 3.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.sumar(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Realiza resta correctamente con resultado positivo`() {
        val respuestaEsperada = 4.0
        val primerOperando = 7.0
        val segundoOperando = 3.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.restar(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Realiza resta correctamente con resultado negativo`() {
        val respuestaEsperada = -4.0
        val primerOperando = 3.0
        val segundoOperando = 7.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.restar(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Realiza multiplicacion correctamente con resultado negativo`() {
        val respuestaEsperada = -4.0
        val primerOperando = 1.0
        val segundoOperando = -4.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.multiplicar(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Realiza multiplicacion correctamente con resultado positivo`() {
        val respuestaEsperada = 4.0
        val primerOperando = 1.0
        val segundoOperando = 4.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.multiplicar(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Realiza division correctamente con resultado positivo`() {
        val respuestaEsperada = 4.0
        val primerOperando = 16.0
        val segundoOperando = 4.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.dividir(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Realiza division correctamente con resultado negativo`() {
        val respuestaEsperada = -4.0
        val primerOperando = -16.0
        val segundoOperando = 4.0
        assertTrue(
            respuestaEsperada == calculadorUseCase.dividir(
                primerOperando,
                segundoOperando
            )
        )
    }

    @Test
    fun `Lanza error cuando segundo operando es cero`() {
        val primerOperando = 13.0
        val segundoOperando = 0.0
        assertFailsWith<ErrorEvaluacion.ErrorComputacional> {
            calculadorUseCase.dividir(primerOperando, segundoOperando)
        }
    }
}
