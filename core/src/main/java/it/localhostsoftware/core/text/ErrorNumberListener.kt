package it.localhostsoftware.core.text

import android.widget.EditText

class ErrorNumberListener(errorMsg: String?, editText: EditText, private val min: Double, private val max: Double) : AbstractErrorListener(errorMsg, editText) {
    override fun matches() =
        try {
            editText.text.toString().toDouble() in min..max
        } catch (e: NumberFormatException) {
            false
        }
}