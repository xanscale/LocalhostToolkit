package localhost.toolkit.app.fragment

import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import localhost.toolkit.R

class ProgressDialogFragment(cancelable: Boolean? = null) : DialogFragment() {
    companion object {
        private const val CANCELABLE = "CANCELABLE"
    }

    init {
        Bundle().apply {
            cancelable?.let { putBoolean(CANCELABLE, it) }
        }.let {
            if (!it.isEmpty) arguments = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isCancelable = it.getBoolean(CANCELABLE, true)
        }
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_MaterialComponents_Dialog_Alert_App).setView(ProgressBar(requireContext())).create()

    override fun onDestroy() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onDestroy()
    }
}