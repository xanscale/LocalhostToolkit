package it.localhostsoftware.core.app.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class NumberPickerDialogFragment : DialogFragment(), DialogInterface.OnClickListener {
    companion object {
        fun newInstance(args: NumberPickerDialogFragmentArgs) = NumberPickerDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: NumberPickerDialogFragmentArgs by navArgs()
    private lateinit var numberPicker: NumberPicker
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        MaterialAlertDialogBuilder(requireActivity()).apply {
            setTitle(args.title)
            setView(NumberPicker(requireActivity()).apply {
                numberPicker = this
                minValue = args.min
                maxValue = args.max
                value = args.value
                args.displayedValues?.let { displayedValues = args.displayedValues }
            })
            setPositiveButton(android.R.string.ok, this@NumberPickerDialogFragment)
            setNegativeButton(android.R.string.cancel, null)
        }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onNumberPickerDialogClick(args.serializable, args.parcelable, numberPicker.value)
    }

    interface OnClickListener {
        fun onNumberPickerDialogClick(serializable: Serializable?, parcelable: Parcelable?, value: Int)
    }
}