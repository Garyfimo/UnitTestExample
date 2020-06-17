package com.garyfimo.unittestexample.domain.calculador

data class Operador(val valor: String) {

    val evaluarPrimero = checkPrioridad(valor)

    private fun checkPrioridad(valor: String): Boolean {
        return when (valor) {
            in arrayOf("*", "/") -> true
            in arrayOf("+", "-") -> false
            else -> throw IllegalArgumentException("Operador no reconocido")
        }
    }
}