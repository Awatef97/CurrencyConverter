package com.example.currencyconverter.core.utils

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toInt()
            if (isInRange(min, max, input)) {
                return null
            }
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    private fun isInRange(min: Int, max: Int, c: Int): Boolean {
        return max > min && c in min..max
    }
}