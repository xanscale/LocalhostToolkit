package localhost.toolkit.text

import android.widget.EditText
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ErrorJsonListener(errorMsg: String, editText: EditText) : AbstractErrorListener(errorMsg, editText) {
    override fun matches(): Boolean {
        try {
            JSONObject(editText.text.toString())
        } catch (ex: JSONException) {
            try {
                JSONArray(editText.text.toString())
            } catch (ex1: JSONException) {
                return false
            }
        }
        return true
    }
}