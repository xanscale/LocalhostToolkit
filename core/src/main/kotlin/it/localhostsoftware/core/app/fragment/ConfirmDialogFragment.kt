package it.localhostsoftware.core.app.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IntDef
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class ConfirmDialogFragment : DialogFragment(), DialogInterface.OnClickListener {
    companion object {
        fun newInstance(args: ConfirmDialogFragmentArgs) = ConfirmDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: ConfirmDialogFragmentArgs by navArgs()
    private val listener: OnClickListener
        get() = parentFragment as? OnClickListener ?: requireActivity() as OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = MaterialAlertDialogBuilder(requireContext()).apply {
        setTitle(args.title)
        setMessage(HtmlCompat.fromHtml(args.message, HtmlCompat.FROM_HTML_MODE_COMPACT))
        setPositiveButton(args.positiveButton ?: getString(android.R.string.ok), this@ConfirmDialogFragment)
        setNegativeButton(args.negativeButton ?: getString(android.R.string.cancel), this@ConfirmDialogFragment)
        args.neutralButton?.let { setNeutralButton(it, this@ConfirmDialogFragment) }
    }.create()

    override fun onClick(dialog: DialogInterface, which: Int) {
        listener.onConfirmDialogClick(args.serializable, args.parcelable, which)
    }

    interface OnClickListener {
        fun onConfirmDialogClick(serializable: Serializable?, parcelable: Parcelable?, @ConfirmDialogButton buttonClicked: Int)
    }
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL)
internal annotation class ConfirmDialogButton