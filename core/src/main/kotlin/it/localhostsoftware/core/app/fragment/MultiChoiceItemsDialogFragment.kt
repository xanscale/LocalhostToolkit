package it.localhostsoftware.core.app.fragment

import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class MultiChoiceItemsDialogFragment : DialogFragment(), OnMultiChoiceClickListener, DialogInterface.OnClickListener {
    companion object {
        fun newInstance(args: MultiChoiceItemsDialogFragmentArgs) = MultiChoiceItemsDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: MultiChoiceItemsDialogFragmentArgs by navArgs()

    private lateinit var checkedItems: BooleanArray
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    override fun onCreateDialog(savedInstanceState: Bundle?) = MaterialAlertDialogBuilder(requireContext()).apply {
        setNegativeButton(android.R.string.cancel, null)
        setPositiveButton(android.R.string.ok, this@MultiChoiceItemsDialogFragment)
        setTitle(args.title)
        checkedItems = args.checkedItems ?: args.items?.let { BooleanArray(it.size) } ?: BooleanArray(resources.getTextArray(args.itemsId).size)
        if (args.items != null) setMultiChoiceItems(args.items, checkedItems, this@MultiChoiceItemsDialogFragment)
        else setMultiChoiceItems(args.itemsId, checkedItems, this@MultiChoiceItemsDialogFragment)
    }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onMultiChoiceItemsDialogClick(args.serializable, args.parcelable, checkedItems)
    }

    override fun onClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        checkedItems[which] = isChecked
    }

    interface OnClickListener {
        fun onMultiChoiceItemsDialogClick(extra: Serializable?, parcelable: Parcelable?, checkedItems: BooleanArray?)
    }
}