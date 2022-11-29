package localhost.toolkit.app.fragment

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ImageDialogFragment(
    icon: Int? = null,
    title: Int? = null,
    imageResource: Int? = null
) : DialogFragment() {
    companion object {
        private const val ICON = "ICON"
        private const val IMAGE_RESOURCE = "IMAGE_RESOURCE"
        private const val TITLE = "TITLE"
    }

    init {
        Bundle().apply {
            icon?.let { putInt(ICON, it) }
            title?.let { putInt(TITLE, it) }
            imageResource?.let { putInt(IMAGE_RESOURCE, it) }
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
            setPositiveButton(android.R.string.ok, null)
            if (requireArguments().containsKey(TITLE)) setTitle(requireArguments().getInt(TITLE))
            if (requireArguments().containsKey(ICON)) setIcon(requireArguments().getInt(ICON))
            if (requireArguments().containsKey(IMAGE_RESOURCE))
                setView(ImageView(requireActivity()).apply {
                    setImageResource(requireArguments().getInt(IMAGE_RESOURCE))
                })
        }.create()
}