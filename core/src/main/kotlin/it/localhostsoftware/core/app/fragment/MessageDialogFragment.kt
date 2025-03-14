package it.localhostsoftware.core.app.fragment

import android.os.Bundle
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MessageDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(args: MessageDialogFragmentArgs) = MessageDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: MessageDialogFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = MaterialAlertDialogBuilder(requireContext()).apply {
        setTitle(args.title)
        setMessage(HtmlCompat.fromHtml(args.message, HtmlCompat.FROM_HTML_MODE_COMPACT))
        setPositiveButton(args.positiveButton ?: getString(android.R.string.ok)) { _, _ ->
            if (args.exit) requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }.create()
}