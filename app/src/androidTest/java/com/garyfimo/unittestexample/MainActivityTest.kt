package com.garyfimo.unittestexample

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.garyfimo.unittestexample.ui.MainActivity
import com.garyfimo.unittestexample.ui.util.Numeros
import com.garyfimo.unittestexample.util.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

internal class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var testName = TestName()

    @get:Rule
    var screenshotWatcher = ScreenshotWatcher()

    // Numeros
    private val btnCero = viewWithId(R.id.btnCero)
    private val btnUno = viewWithId(R.id.btnUno)
    private val btnDos = viewWithId(R.id.btnDos)
    private val btnTres = viewWithId(R.id.btnTres)
    private val btnCuatro = viewWithId(R.id.btnCuatro)
    private val btnCinco = viewWithId(R.id.btnCinco)
    private val btnSeis = viewWithId(R.id.btnSeis)
    private val btnSiete = viewWithId(R.id.btnSiete)
    private val btnOcho = viewWithId(R.id.btnOcho)
    private val btnNueve = viewWithId(R.id.btnNueve)

    // Operadores
    private val btnPunto = viewWithId(R.id.btnPunto)
    private val btnIgual = viewWithId(R.id.btnIgual)
    private val btnResta = viewWithId(R.id.btnResta)
    private val btnSuma = viewWithId(R.id.btnSuma)
    private val btnMultiplicacion = viewWithId(R.id.btnMultiplicacion)
    private val btnDivision = viewWithId(R.id.btnDivision)

    // Utilitarios
    private val btnBorrar = viewWithId(R.id.btnBorrar)
    private val btnLimpiar = viewWithId(R.id.btnLimpiar)

    // Visualizador
    private val tvVisualizadorOperacion = viewWithId(R.id.tvVisualizadorOperacion)
    private val tvVisualizadorResultado = viewWithId(R.id.tvVisualizadorResultado)

    @Test
    fun verificarQueBorreUltimosCaracteres() {
        val valorEsperado = "1"
        btnUno.click()
        btnDivision.click()
        btnCero.click()
        btnBorrar.click()
        btnBorrar.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarQueLimpieExpresion() {
        val valorEsperado = ""
        btnUno.click()
        btnDivision.click()
        btnCero.click()
        btnLimpiar.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarVisualizacionErrorValidacion() {
        val valorEsperado = "La operacion no es valida"
        btnUno.click()
        btnPunto.click()
        btnPunto.click()
        btnDivision.click()
        btnCuatro.click()
        btnIgual.click()
        tvVisualizadorResultado.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarVisualizacionErrorComputacional() {
        val valorEsperado = "No se puede dividir por cero"
        btnUno.click()
        btnDivision.click()
        btnCero.click()
        btnIgual.click()
        tvVisualizadorResultado.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarVisualizacionDeResultadoDecimal() {
        val valorEsperado = "0.25"
        btnUno.click()
        btnDivision.click()
        btnCuatro.click()
        btnIgual.click()
        tvVisualizadorResultado.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarVisualizacionDeResultadoEntero() {
        val valorEsperado = "1"
        btnDos.click()
        btnSuma.click()
        btnTres.click()
        btnResta.click()
        btnSeis.click()
        btnDivision.click()
        btnTres.click()
        btnMultiplicacion.click()
        btnDos.click()
        btnIgual.click()
        tvVisualizadorResultado.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarVisualizacionDeOperacion() {
        val valorEsperado = "2+3-6/3*2"
        btnDos.click()
        btnSuma.click()
        btnTres.click()
        btnResta.click()
        btnSeis.click()
        btnDivision.click()
        btnTres.click()
        btnMultiplicacion.click()
        btnDos.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonCero() {
        val valorEsperado = Numeros.CERO.valor
        btnCero.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonUno() {
        val valorEsperado = Numeros.UNO.valor
        btnUno.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonDos() {
        val valorEsperado = Numeros.DOS.valor
        btnDos.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonTres() {
        val valorEsperado = Numeros.TRES.valor
        btnTres.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonCuatro() {
        val valorEsperado = Numeros.CUATRO.valor
        btnCuatro.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonCinco() {
        val valorEsperado = Numeros.CINCO.valor
        btnCinco.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonSeis() {
        val valorEsperado = Numeros.SEIS.valor
        btnSeis.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonSiete() {
        val valorEsperado = Numeros.SIETE.valor
        btnSiete.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonOcho() {
        val valorEsperado = Numeros.OCHO.valor
        btnOcho.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verificarClicBotonNueve() {
        val valorEsperado = Numeros.NUEVE.valor
        btnNueve.click()
        tvVisualizadorOperacion.checkMatches(ViewMatchers.withText(valorEsperado))
    }

    @Test
    fun verficiarQueLosNumerosSeVean() {
        btnCero.checkDisplayed()
        btnUno.checkDisplayed()
        btnDos.checkDisplayed()
        btnTres.checkDisplayed()
        btnCuatro.checkDisplayed()
        btnCinco.checkDisplayed()
        btnSeis.checkDisplayed()
        btnSiete.checkDisplayed()
        btnOcho.checkDisplayed()
        btnNueve.checkDisplayed()
    }

    @Test
    fun verficiarQueLosOperadoresSeVean() {
        btnResta.checkDisplayed()
        btnSuma.checkDisplayed()
        btnMultiplicacion.checkDisplayed()
        btnDivision.checkDisplayed()
    }

    @Test
    fun verficiarQueLosUtilitariosSeVean() {
        btnPunto.checkDisplayed()
        btnIgual.checkDisplayed()
        btnBorrar.checkDisplayed()
        btnLimpiar.checkDisplayed()
    }

    @Test
    fun verficiarQueLosVisualizadoresSeVean() {
        tvVisualizadorOperacion.checkDisplayed()
        tvVisualizadorResultado.checkDisplayed()
    }
}