package it.localhostsoftware.core.app.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class ItemsDialogFragment(
    serializable: Serializable? = null,
    parcelable: Parcelable? = null,
    title: Int? = null,
    itemsId: Int? = null,
    items: Array<String>? = null
) : DialogFragment(), DialogInterface.OnClickListener {
    companion object {
        private const val TITLE = "TITLE"
        private const val ITEMS_ID = "ITEMS_ID"
        private const val ITEMS = "ITEMS"
        private const val SERIALIZABLE = "SERIALIZABLE"
        private const val PARCELABLE = "PARCELABLE"
    }

    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    init {
        Bundle().apply {
            title?.let { putInt(TITLE, it) }
            itemsId?.let { putInt(ITEMS_ID, it) }
            items?.let { putStringArray(ITEMS, it) }
            serializable?.let { putSerializable(SERIALIZABLE, it) }
            parcelable?.let { putParcelable(PARCELABLE, it) }
        }.let {
            if (!it.isEmpty) arguments = it
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        MaterialAlertDialogBuilder(requireActivity()).apply {
            if (requireArguments().containsKey(TITLE)) setTitle(requireArguments().getInt(TITLE))
            if (requireArguments().containsKey(ITEMS)) setItems(requireArguments().getStringArray(ITEMS), this@ItemsDialogFragment)
            else setItems(requireArguments().getInt(ITEMS_ID), this@ItemsDialogFragment)
        }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onItemsDialogClick(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), which)
    }

    interface OnClickListener {
        fun onItemsDialogClick(serializable: Serializable?, parcelable: Parcelable?, which: Int)
    }
}