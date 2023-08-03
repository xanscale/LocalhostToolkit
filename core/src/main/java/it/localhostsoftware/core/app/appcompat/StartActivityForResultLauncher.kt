package it.localhostsoftware.core.app.appcompat

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData

class StartActivityForResultLauncher : LiveData<ActivityResult>, ActivityResultCallback<ActivityResult> {
    private val launcher: ActivityResultLauncher<Intent>

    constructor(fragment: Fragment) {
        launcher = fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult(), this)
    }

    constructor(activity: FragmentActivity) {
        launcher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult(), this)
    }

    override fun onActivityResult(result: ActivityResult) {
        value = result
    }

    fun launch(intent: Intent) {
        launcher.launch(intent)
    }
}