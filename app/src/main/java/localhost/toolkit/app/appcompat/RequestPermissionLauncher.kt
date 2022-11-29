package localhost.toolkit.app.appcompat

import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RequestPermissionLauncher : LiveData<RequestPermissionLauncher.PermissionResult>, ActivityResultCallback<Map<String, Boolean>> {
    private val launcher: ActivityResultLauncher<Array<String>>
    private var act: FragmentActivity? = null
    private var frg: Fragment? = null
    private val activity
        get() = if (act != null) act!! else frg!!.requireActivity()

    constructor(fragment: Fragment) {
        this.frg = fragment
        launcher = fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), this)
    }

    constructor(activity: FragmentActivity) {
        this.act = activity
        launcher = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), this)
    }

    override fun onActivityResult(results: Map<String, Boolean>) {
        val permissions = results.keys.toTypedArray()
        value = when {
            results.isEmpty() -> PermissionResult.DENIED
            checkSelfPermission(permissions) -> PermissionResult.GRANTED
            shouldShowRequestPermissionRationale(permissions) -> PermissionResult.DENIED
            else -> PermissionResult.PERMANENTLY_DENIED
        }
    }

    private fun checkSelfPermission(permissions: Array<String>) =
        permissions.all { ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED }

    private fun shouldShowRequestPermissionRationale(permissions: Array<String>) =
        permissions.any { ActivityCompat.shouldShowRequestPermissionRationale(activity, it) }

    fun launch(title: String?, rational: String?, permission: String) =
        launch(title, rational, arrayOf(permission))

    fun launch(title: String?, rational: String?, permissions: Array<String>) {
        if (checkSelfPermission(permissions)) value = PermissionResult.GRANTED
        else if (shouldShowRequestPermissionRationale(permissions)) {
            MaterialAlertDialogBuilder(activity).apply {
                if (title != null) setTitle(title)
                if (rational != null) setMessage(rational)
                setPositiveButton(android.R.string.ok) { _, _ -> launcher.launch(permissions) }
                setNegativeButton(android.R.string.cancel) { _, _ -> value = PermissionResult.DENIED }
            }.show()
        } else launcher.launch(permissions)
    }

    enum class PermissionResult {
        GRANTED, DENIED, PERMANENTLY_DENIED
    }
}