package com.massita.faire

import com.massita.faire.util.CurrencyConverter
import org.junit.Assert
import org.junit.Test
import java.util.*

class CurrencyConverterTest {

    @Test
    fun testCurrencyConverter() {
        val converter = CurrencyConverter()
        Assert.assertEquals("$100.00", converter.convertLongToCurrency(10000, Locale.US))
    }

    @Test
    fun testCurrencyNull() {
        val converter = CurrencyConverter()
        Assert.assertEquals("$0.00", converter.convertLongToCurrency(null, Locale.US))
    }

    @Test
    fun testCurrencyLowValue() {
        val converter = CurrencyConverter()
        Assert.assertEquals("$0.05", converter.convertLongToCurrency(5, Locale.US))
    }

    @Test
    fun testCurrencyZero() {
        val converter = CurrencyConverter()
        Assert.assertEquals("$0.00", converter.convertLongToCurrency(0, Locale.US))
    }
}