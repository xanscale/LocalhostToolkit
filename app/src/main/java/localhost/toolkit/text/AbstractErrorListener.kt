package localhost.toolkit.text

import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

abstract class AbstractErrorListener(private val errorMsg: String, val editText: EditText) : OnFocusChangeListener {
    var onFocusChangeListener: OnFocusChangeListener? = null

    init {
        editText.onFocusChangeListener = this
    }

    abstract fun matches(): Boolean
    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (hasFocus) setError(null)
        else matches(true)
        onFocusChangeListener?.onFocusChange(v, hasFocus)
    }

    fun matches(showError: Boolean) =
        matches().also {
            if (it) setError(null)
            else if (showError) setError(errorMsg)
        }

    private fun setError(error: String?) {
        getTextInputLayout().let { layout ->
            if (layout == null) editText.error = error
            else layout.error = error
        }
    }

    private fun getTextInputLayout(): TextInputLayout? {
        var parent = editText.parent
        while (parent is View) {
            if (parent is TextInputLayout) return parent
            parent = parent.getParent()
        }
        return null
    }
}