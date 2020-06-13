package com.garyfimo.unittestexample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.garyfimo.unittestexample.domain.evaluador.EvaluadorUseCase
import com.garyfimo.unittestexample.domain.evaluador.ResultadoEvaluacion
import com.garyfimo.unittestexample.domain.util.ErrorEvaluacion
import com.garyfimo.unittestexample.ui.util.Numeros
import com.garyfimo.unittestexample.ui.util.Operadores
import com.garyfimo.unittestexample.ui.util.Utilitarios
import com.garyfimo.unittestexample.ui.util.ViewEvent
import com.garyfimo.unittestexample.util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
internal class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private val evaluadorUseCase: EvaluadorUseCase = mockk()

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(evaluadorUseCase)
    }

    @Test
    fun `Verifiar que expresion simple evaluada de resultado correcto`() =
        coroutineRule.testDispatcher.runBlockingTest {
            val expresionSimple = "2+2"
            val resultado = "4"
            coEvery { evaluadorUseCase.evaluarExpresion(expresionSimple) } returns ResultadoEvaluacion.build { resultado }
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.SUMA))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
            mainViewModel.onClick(ViewEvent.ClickEvaluar)
            assertEquals(resultado, mainViewModel.resultado.value)
        }

    @Test
    fun `Verifiar que expresion compleja evaluada de resultado correcto`() =
        coroutineRule.testDispatcher.runBlockingTest {
            val expresionCompleja = "2*2-3+1/4"
            val resultado = "1.25"
            coEvery { evaluadorUseCase.evaluarExpresion(expresionCompleja) } returns ResultadoEvaluacion.build { resultado }
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.MULTIPLICACION))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.RESTA))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.TRES))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.SUMA))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.UNO))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.DIVISION))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.CUATRO))
            mainViewModel.onClick(ViewEvent.ClickEvaluar)
            assertEquals(resultado, mainViewModel.resultado.value)
        }

    @Test
    fun `Verificar que muestre error cuando se divide por cero`() =
        coroutineRule.testDispatcher.runBlockingTest {
            val expresionNoValida = "1/0"
            val errorEsperado = "No se puede dividir por cero"
            coEvery { evaluadorUseCase.evaluarExpresion(expresionNoValida) } returns ResultadoEvaluacion.build { throw ErrorEvaluacion.ErrorComputacional() }
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.UNO))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.DIVISION))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.CERO))
            mainViewModel.onClick(ViewEvent.ClickEvaluar)

            assertEquals(errorEsperado, mainViewModel.resultado.value)
        }

    @Test
    fun `Verificar que muestre error si la expresion es no valida`() =
        coroutineRule.testDispatcher.runBlockingTest {
            val expresionNoValida = "1..0/2"
            val errorEsperado = "La operacion no es valida"
            coEvery { evaluadorUseCase.evaluarExpresion(expresionNoValida) } returns ResultadoEvaluacion.build { throw ErrorEvaluacion.ErrorValidacion() }
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.UNO))
            mainViewModel.onClick(ViewEvent.ClickUtilitario(Utilitarios.PUNTO))
            mainViewModel.onClick(ViewEvent.ClickUtilitario(Utilitarios.PUNTO))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.CERO))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.DIVISION))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
            mainViewModel.onClick(ViewEvent.ClickEvaluar)

            assertEquals(errorEsperado, mainViewModel.resultado.value)
        }

    @Test
    fun `Verificar que limpie la expresion al dar click en limpiar`() {
        val expresionEsperada = ""
        mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.OCHO))
        mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.DIVISION))
        mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
        mainViewModel.onClick(ViewEvent.ClickLimpiar)

        assertEquals(expresionEsperada, mainViewModel.operacion.value)
    }


    @Test
    fun `Verificar que borre caracteres al dar click en borrar`() {
            val expresionEsperada = "1.0"
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.UNO))
            mainViewModel.onClick(ViewEvent.ClickUtilitario(Utilitarios.PUNTO))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.CERO))
            mainViewModel.onClick(ViewEvent.ClickOperador(Operadores.DIVISION))
            mainViewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS))
            mainViewModel.onClick(ViewEvent.ClickBorrar)
            mainViewModel.onClick(ViewEvent.ClickBorrar)

            assertEquals(expresionEsperada, mainViewModel.operacion.value)
        }
}