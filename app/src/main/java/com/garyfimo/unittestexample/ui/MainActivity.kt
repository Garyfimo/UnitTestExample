package com.garyfimo.unittestexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.garyfimo.unittestexample.R
import com.garyfimo.unittestexample.domain.calculador.CalculadorUseCase
import com.garyfimo.unittestexample.domain.calculador.CalculadorUseCaseImpl
import com.garyfimo.unittestexample.domain.evaluador.EvaluadorUseCase
import com.garyfimo.unittestexample.domain.evaluador.EvaluadorUseCaseImpl
import com.garyfimo.unittestexample.domain.validador.ValidadorUseCase
import com.garyfimo.unittestexample.domain.validador.ValidadorUseCaseImpl
import com.garyfimo.unittestexample.ui.util.Numeros
import com.garyfimo.unittestexample.ui.util.Operadores
import com.garyfimo.unittestexample.ui.util.Utilitarios
import com.garyfimo.unittestexample.ui.util.ViewEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // TODO("Cambiar por DI")
    private val calculadorUseCase: CalculadorUseCase by lazy {
        CalculadorUseCaseImpl()
    }
    private val validadorUseCase: ValidadorUseCase by lazy {
        ValidadorUseCaseImpl()
    }
    private val evaluadorUseCase: EvaluadorUseCase by lazy {
        EvaluadorUseCaseImpl(validadorUseCase, calculadorUseCase)
    }

    private val mainViewModelFactory: MainViewModelFactory = MainViewModelFactory(evaluadorUseCase)

    private val viewModel: MainViewModel by viewModels {
        mainViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observarTextOperacion()
        observarTextResultado()
        configurarClickListener()
    }

    private fun observarTextResultado() {
        viewModel.resultado.observe(this, Observer { resultado ->
            tvVisualizadorResultado?.text = resultado
        })
    }

    private fun observarTextOperacion() {
        viewModel.operacion.observe(this, Observer { expresionOperacion ->
            tvVisualizadorOperacion?.text = expresionOperacion
        })
    }

    private fun configurarClickListener() {
        btnCero?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.CERO)) }
        btnUno?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.UNO)) }
        btnDos?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.DOS)) }
        btnTres?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.TRES)) }
        btnCuatro?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.CUATRO)) }
        btnCinco?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.CINCO)) }
        btnSeis?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.SEIS)) }
        btnSiete?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.SIETE)) }
        btnOcho?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.OCHO)) }
        btnNueve?.setOnClickListener { viewModel.onClick(ViewEvent.ClickNumero(Numeros.NUEVE)) }

        btnSuma?.setOnClickListener { viewModel.onClick(ViewEvent.ClickOperador(Operadores.SUMA)) }
        btnMultiplicacion?.setOnClickListener { viewModel.onClick(ViewEvent.ClickOperador(Operadores.MULTIPLICACION)) }
        btnResta?.setOnClickListener { viewModel.onClick(ViewEvent.ClickOperador(Operadores.RESTA)) }
        btnDivision?.setOnClickListener { viewModel.onClick(ViewEvent.ClickOperador(Operadores.DIVISION)) }

        btnLimpiar?.setOnClickListener { viewModel.onClick(ViewEvent.ClickLimpiar) }
        btnBorrar?.setOnClickListener { viewModel.onClick(ViewEvent.ClickBorrar) }

        btnPunto?.setOnClickListener { viewModel.onClick(ViewEvent.ClickUtilitario(Utilitarios.PUNTO)) }

        btnIgual?.setOnClickListener { viewModel.onClick(ViewEvent.ClickEvaluar) }
    }
}