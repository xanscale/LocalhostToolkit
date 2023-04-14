package localhost.toolkit.text

import android.widget.EditText

class ErrorEqualsListener(errorMsg: String, subject: EditText, private val reference: EditText) : AbstractErrorListener(errorMsg, subject) {
    override fun matches() =
        editText.text.toString() == reference.text.toString()
}