package localhost.toolkit.text

import android.widget.EditText

class ErrorSetListener(errorMsg: String, editText: EditText, private val stringSet: Set<String>) : AbstractErrorListener(errorMsg, editText) {
    override fun matches() =
        stringSet.contains(editText.text.toString())
}