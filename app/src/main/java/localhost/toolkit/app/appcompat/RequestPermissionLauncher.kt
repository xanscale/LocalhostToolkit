package localhost.toolkit.app.appcompat

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData

class RequestPermissionLauncher : LiveData<Boolean>, ActivityResultCallback<Map<String, Boolean>> {
    private val launcher: ActivityResultLauncher<Array<String>>

    constructor(fragment: Fragment) {
        launcher = fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), this)
    }

    constructor(activity: FragmentActivity) {
        launcher = activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), this)
    }

    override fun onActivityResult(result: Map<String, Boolean>) {
        value = result.values.all { it }
    }

    fun launch(vararg permissions: String) {
        launcher.launch(arrayOf(*permissions))
    }
}