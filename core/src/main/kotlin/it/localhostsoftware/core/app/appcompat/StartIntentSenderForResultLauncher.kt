package it.localhostsoftware.core.app.appcompat

import android.app.PendingIntent
import android.content.IntentSender
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData

class StartIntentSenderForResultLauncher : LiveData<ActivityResult>, ActivityResultCallback<ActivityResult> {
    private val launcher: ActivityResultLauncher<IntentSenderRequest>

    constructor(fragment: Fragment) {
        launcher = fragment.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult(), this)
    }

    constructor(activity: FragmentActivity) {
        launcher = activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult(), this)
    }

    override fun onActivityResult(result: ActivityResult) {
        value = result
    }

    fun launch(intentSender: IntentSender) {
        launcher.launch(IntentSenderRequest.Builder(intentSender).build())
    }

    fun launch(pendingIntent: PendingIntent) {
        launcher.launch(IntentSenderRequest.Builder(pendingIntent).build())
    }
}