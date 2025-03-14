package it.localhostsoftware.core.text

import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

fun EditText.addAutoFocusRightTextWatcher(maxLength: Int) {
    doAfterTextChanged {
        if (it?.length == maxLength) focusSearch(View.FOCUS_RIGHT).requestFocus()
    }
}