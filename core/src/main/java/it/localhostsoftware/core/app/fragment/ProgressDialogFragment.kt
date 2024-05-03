package it.localhostsoftware.core.app.fragment

import android.os.Bundle
import android.view.WindowManager
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import it.localhostsoftware.core.R

class ProgressDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(args: ProgressDialogFragmentArgs) = ProgressDialogFragment().apply {
            arguments = args.toBundle()
        }
    }

    private val args: ProgressDialogFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = args.cancelable
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_MaterialComponents_Dialog_Alert_App).setView(ProgressBar(requireContext())).create()

    override fun onDestroy() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onDestroy()
    }
}