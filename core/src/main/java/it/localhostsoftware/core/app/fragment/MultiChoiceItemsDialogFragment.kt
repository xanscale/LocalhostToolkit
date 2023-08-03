package it.localhostsoftware.core.app.fragment

import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class MultiChoiceItemsDialogFragment(
    serializable: Serializable? = null,
    parcelable: Parcelable? = null,
    title: Int? = null,
    itemsId: Int? = null,
    checkedItems: BooleanArray? = null,
    items: Array<String>? = null,
    firstItemAsClickAll: Boolean? = null
) : DialogFragment(), OnMultiChoiceClickListener, DialogInterface.OnClickListener {
    companion object {
        private const val CHECKED_ITEMS = "CHECKED_ITEMS"
        private const val TITLE = "TITLE"
        private const val SERIALIZABLE = "SERIALIZABLE"
        private const val PARCELABLE = "PARCELABLE"
        private const val ITEMS_ID = "ITEMS_ID"
        private const val ITEMS = "ITEMS"
        private const val FIRST_ITEM_AS_CLICK_ALL = "firstItemAsClickAll"
    }

    private lateinit var checkedItems: BooleanArray
    private var performItemClick = true
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    init {
        Bundle().apply {
            serializable?.let { putSerializable(SERIALIZABLE, it) }
            parcelable?.let { putParcelable(PARCELABLE, it) }
            title?.let { putInt(TITLE, it) }
            itemsId?.let { putInt(ITEMS_ID, it) }
            items?.let { putStringArray(ITEMS, it) }
            checkedItems?.let { putBooleanArray(CHECKED_ITEMS, it) }
            firstItemAsClickAll?.let { putBoolean(FIRST_ITEM_AS_CLICK_ALL, it) }
        }.let {
            if (!it.isEmpty) arguments = it
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        MaterialAlertDialogBuilder(requireContext()).apply {
            setNegativeButton(android.R.string.cancel, null)
            setPositiveButton(android.R.string.ok, this@MultiChoiceItemsDialogFragment)
            if (requireArguments().containsKey(TITLE))
                setTitle(requireArguments().getInt(TITLE))
            var checkedItems = requireArguments().getBooleanArray(CHECKED_ITEMS)
            if (requireArguments().containsKey(ITEMS))
                setMultiChoiceItems(requireArguments().getStringArray(ITEMS)!!.also {
                    if (checkedItems == null) checkedItems = BooleanArray(it.size)
                }, checkedItems, this@MultiChoiceItemsDialogFragment)
            else
                setMultiChoiceItems(requireArguments().getInt(ITEMS_ID).also {
                    if (checkedItems == null) checkedItems = BooleanArray(resources.getTextArray(it).size)
                }, checkedItems, this@MultiChoiceItemsDialogFragment)
            this@MultiChoiceItemsDialogFragment.checkedItems = checkedItems!!
        }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onClick(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), checkedItems)
    }

    override fun onClick(dialog: DialogInterface, which: Int, isChecked: Boolean) {
        checkedItems[which] = isChecked
        if (requireArguments().getBoolean(FIRST_ITEM_AS_CLICK_ALL)) {
            val listView = (getDialog() as AlertDialog).listView
            if (which == 0) {
                if (performItemClick)
                    for (i in 1 until checkedItems.size)
                        if (isChecked != checkedItems[i]) {
                            performItemClick = false
                            listView.performItemClick(listView, i, 0)
                            performItemClick = true
                        }
            } else if (!isChecked && checkedItems[0]) {
                performItemClick = false
                listView.performItemClick(listView, 0, 0)
                performItemClick = true
            }
        }
    }

    interface OnClickListener {
        fun onClick(extra: Serializable?, parcelable: Parcelable?, checkedItems: BooleanArray?)
    }
}