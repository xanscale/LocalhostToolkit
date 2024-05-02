package it.localhostsoftware.core.app.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.core.os.BundleCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import it.localhostsoftware.core.R
import java.io.Serializable

class EditTextDialogFragment(
    serializable: Serializable? = null, parcelable: Parcelable? = null, title: String? = null, text: String? = null, hint: String? = null, inputType: Int? = null
) : DialogFragment() {
    companion object {
        private const val TEXT = "TEXT"
        private const val HINT = "HINT"
        private const val INPUT_TYPE = "INPUT_TYPE"
        private const val TITLE = "TITLE"
        private const val SERIALIZABLE = "SERIALIZABLE"
        private const val PARCELABLE = "PARCELABLE"
    }

    private lateinit var editText: EditText
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    init {
        Bundle().apply {
            title?.let { putString(TITLE, it) }
            text?.let { putString(TEXT, it) }
            hint?.let { putString(HINT, it) }
            inputType?.let { putInt(INPUT_TYPE, it) }
            serializable?.let { putSerializable(SERIALIZABLE, it) }
            parcelable?.let { putParcelable(PARCELABLE, it) }
        }.let {
            if (!it.isEmpty) arguments = it
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(requireArguments().getString(TITLE))
            setPositiveButton(android.R.string.ok) { _, _ ->
                listener.onEditTextDialogClick(BundleCompat.getSerializable(requireArguments(), SERIALIZABLE, Serializable::class.java), BundleCompat.getParcelable(requireArguments(), PARCELABLE, Parcelable::class.java), editText.text.toString())
            }
            setNegativeButton(android.R.string.cancel, null)
            setView(View.inflate(requireContext(), R.layout.dialog_edittext, null).apply {
                editText = findViewById<EditText?>(R.id.textInputEditText).apply {
                    inputType = requireArguments().getInt(INPUT_TYPE, InputType.TYPE_CLASS_TEXT)
                    setText(requireArguments().getString(TEXT))
                }
                findViewById<TextInputLayout>(R.id.textInputLayout).hint = requireArguments().getString(HINT)
            })
        }.create()
    }

    interface OnClickListener {
        fun onEditTextDialogClick(serializable: Serializable?, parcelable: Parcelable?, value: String)
    }
}