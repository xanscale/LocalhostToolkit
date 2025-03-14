package it.localhostsoftware.core.app.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import it.localhostsoftware.core.databinding.DialogEdittextBinding
import java.io.Serializable

class EditTextDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(args: EditTextDialogFragmentArgs) = EditTextDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: EditTextDialogFragmentArgs by navArgs()
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(args.title)
            setNegativeButton(android.R.string.cancel, null)
            DialogEdittextBinding.inflate(layoutInflater).apply {
                textInputEditText.inputType = args.inputType
                textInputEditText.setText(args.text)
                textInputLayout.hint = args.hint
            }.let {
                setView(it.root)
                setPositiveButton(android.R.string.ok) { _, _ ->
                    listener.onEditTextDialogClick(args.serializable, args.parcelable, it.textInputEditText.text.toString())
                }
            }
        }.create()
    }

    interface OnClickListener {
        fun onEditTextDialogClick(serializable: Serializable?, parcelable: Parcelable?, value: String)
    }
}