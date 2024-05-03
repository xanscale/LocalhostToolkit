package it.localhostsoftware.core.app.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class ItemsDialogFragment : DialogFragment(), DialogInterface.OnClickListener {
    companion object {
        fun newInstance(args: ItemsDialogFragmentArgs) = ItemsDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: ItemsDialogFragmentArgs by navArgs()
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener


    override fun onCreateDialog(savedInstanceState: Bundle?) =
        MaterialAlertDialogBuilder(requireActivity()).apply {
            setTitle(args.title)
            if (args.items != null) setItems(args.items, this@ItemsDialogFragment)
            else setItems(args.itemsId, this@ItemsDialogFragment)
        }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onItemsDialogClick(args.serializable, args.parcelable, which)
    }

    interface OnClickListener {
        fun onItemsDialogClick(serializable: Serializable?, parcelable: Parcelable?, which: Int)
    }
}