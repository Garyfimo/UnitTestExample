package com.garyfimo.unittestexample.ui.util

import java.math.BigDecimal

fun String.removerDecimalSiSonCeros(): String {
    return BigDecimal.valueOf(this.toDouble()).stripTrailingZeros().toPlainString()
}

fun String.removerUltimoCaracter(): String {
    return this.dropLast(1)
}