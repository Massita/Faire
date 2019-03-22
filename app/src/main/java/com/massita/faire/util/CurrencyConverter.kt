package com.massita.faire.util

import java.text.NumberFormat
import java.util.*

class CurrencyConverter {


    fun convertLongToCurrency(cents: Long?, locale: Locale) : String {
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format((cents ?: 0) / 100.0)
    }
}