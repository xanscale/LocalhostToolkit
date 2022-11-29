package localhost.toolkit.app.fragment

import android.os.Bundle
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MessageDialogFragment(
    title: String? = null,
    message: String? = null,
    exit: Boolean? = null,
    positiveButton: String? = null
) : DialogFragment() {
    companion object {
        private const val TITLE = "TITLE"
        private const val MESSAGE = "MESSAGE"
        private const val EXIT = "EXIT"
        private const val POSITIVE_BUTTON = "POSITIVE_BUTTON"
    }

    init {
        Bundle().apply {
            title?.let { putString(TITLE, it) }
            message?.let { putString(MESSAGE, it) }
            exit?.let { putBoolean(EXIT, it) }
            positiveButton?.let { putString(POSITIVE_BUTTON, it) }
        }.let {
            if (!it.isEmpty) arguments = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(requireArguments().getString(TITLE))
            if (requireArguments().containsKey(MESSAGE))
                setMessage(HtmlCompat.fromHtml(requireArguments().getString(MESSAGE)!!, HtmlCompat.FROM_HTML_MODE_COMPACT))
            setPositiveButton(requireArguments().getString(POSITIVE_BUTTON, getString(android.R.string.ok))) { _, _ ->
                if (requireArguments().getBoolean(EXIT)) requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }.create()
}