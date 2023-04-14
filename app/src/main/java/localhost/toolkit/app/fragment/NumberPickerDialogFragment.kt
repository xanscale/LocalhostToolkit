package localhost.toolkit.app.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class NumberPickerDialogFragment(
    serializable: Serializable? = null,
    parcelable: Parcelable? = null,
    title: String? = null,
    min: Int? = null,
    max: Int? = null,
    value: Int? = null,
    displayedValues: Array<String>? = null
) : DialogFragment(), DialogInterface.OnClickListener {
    companion object {
        private const val TITLE = "TITLE"
        private const val MIN = "MIN"
        private const val MAX = "MAX"
        private const val VALUE = "VALUE"
        private const val SERIALIZABLE = "SERIALIZABLE"
        private const val PARCELABLE = "PARCELABLE"
        private const val DISPLAYED_VALUES = "DISPLAYED_VALUES"
    }

    init {
        Bundle().apply {
            title?.let { putString(TITLE, it) }
            serializable?.let { putSerializable(SERIALIZABLE, it) }
            parcelable?.let { putParcelable(PARCELABLE, it) }
            min?.let { putInt(MIN, it) }
            max?.let { putInt(MAX, it) }
            value?.let { putInt(VALUE, it) }
            displayedValues?.let { putStringArray(DISPLAYED_VALUES, it) }
        }.let {
            if (!it.isEmpty) arguments = it
        }
    }

    private lateinit var numberPicker: NumberPicker
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        MaterialAlertDialogBuilder(requireActivity()).apply {
            setTitle(requireArguments().getString(TITLE))
            setView(NumberPicker(requireActivity()).apply {
                if (requireArguments().containsKey(MIN)) minValue = requireArguments().getInt(MIN)
                if (requireArguments().containsKey(MAX)) maxValue = requireArguments().getInt(MAX)
                if (requireArguments().containsKey(VALUE)) value = requireArguments().getInt(VALUE)
                if (requireArguments().containsKey(DISPLAYED_VALUES)) displayedValues = requireArguments().getStringArray(DISPLAYED_VALUES)
                numberPicker = this
            })
            setPositiveButton(android.R.string.ok, this@NumberPickerDialogFragment)
            setNegativeButton(android.R.string.cancel, null)
        }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onClick(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), numberPicker.value)
    }

    interface OnClickListener {
        fun onClick(serializable: Serializable?, parcelable: Parcelable?, value: Int)
    }
}