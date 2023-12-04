package it.localhostsoftware.core.app.appcompat

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData

class RequestPermissionLauncher : LiveData<RequestPermissionLauncher.PermissionResult>, ActivityResultCallback<Map<String, Boolean>> {
    private val launcher: ActivityResultLauncher<Array<String>>

    constructor(fragment: Fragment) {
        launcher = fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), this)
    }

    constructor(activity: FragmentActivity) {
        launcher = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), this)
    }

    private fun checkSelfPermission(context: Context, vararg permissions: String) =
        permissions.all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }

    private fun shouldShowRequestPermissionRationale(activity: Activity, vararg permissions: String) =
        permissions.any { ActivityCompat.shouldShowRequestPermissionRationale(activity, it) }

    override fun onActivityResult(result: Map<String, Boolean>) {
        value = if (result.values.all { it }) PermissionResult.PERMISSION_GRANTED else PermissionResult.PERMISSION_DENIED
    }

    fun launch(activity: Activity, vararg permissions: String) {
        if (checkSelfPermission(activity, *permissions)) value = PermissionResult.PERMISSION_GRANTED
        else if (shouldShowRequestPermissionRationale(activity, *permissions)) value = PermissionResult.PERMISSION_RATIONALE
        else launcher.launch(arrayOf(*permissions))
    }

    enum class PermissionResult { PERMISSION_GRANTED, PERMISSION_DENIED, PERMISSION_RATIONALE }
}