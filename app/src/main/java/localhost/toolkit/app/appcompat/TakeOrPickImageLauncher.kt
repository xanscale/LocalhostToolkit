package localhost.toolkit.app.appcompat

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Size
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.io.File

class TakeOrPickImageLauncher : LiveData<Uri?>, ActivityResultCallback<Uri?> {
    private val launcher: ActivityResultLauncher<Unit>

    constructor(fragment: Fragment) {
        launcher = fragment.registerForActivityResult(TakeOrPickImageContract(fragment), this)
    }

    constructor(activity: FragmentActivity) {
        launcher = activity.registerForActivityResult(TakeOrPickImageContract(activity), this)
    }

    override fun onActivityResult(result: Uri?) {
        value = result
    }

    fun launch() {
        launcher.launch(Unit)
    }

    class TakeOrPickImageContract(private val owner: ViewModelStoreOwner) : ActivityResultContract<Unit, Uri?>() {
        private val viewModel by lazy { ViewModelProvider(owner)[MediaViewModel::class.java] }
        private val pickVisualMedia = ActivityResultContracts.PickVisualMedia()
        private val takePicture = ActivityResultContracts.TakePicture()

        override fun createIntent(context: Context, input: Unit): Intent {
            viewModel.uri = FileProvider.getUriForFile(context, context.packageName + ".localhost.provider", File.createTempFile("image", ".jpg"))
            val pickIntent = pickVisualMedia.createIntent(context, PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            val takeIntent = takePicture.createIntent(context, viewModel.uri!!)
            return Intent.createChooser(takeIntent, null).putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
        }

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if (resultCode != Activity.RESULT_OK) null
            else pickVisualMedia.parseResult(resultCode, intent) ?: viewModel.uri
    }

    class MediaViewModel : ViewModel() {
        var uri: Uri? = null
    }


    companion object {
        fun ContentResolver.getOrientation(uri: Uri) =
            openInputStream(uri)!!.use { input ->
                ExifInterface(input).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            }

        fun ContentResolver.getRotation(uri: Uri) =
            when (getOrientation(uri)) {
                ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                else -> 0f
            }

        @Suppress("DEPRECATION")
        fun ContentResolver.getBitmap(uri: Uri): Bitmap =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) ImageDecoder.decodeBitmap(ImageDecoder.createSource(this, uri))
            else {
                MediaStore.Images.Media.getBitmap(this, uri).let { bmp ->
                    getRotation(uri).let { r ->
                        if (r != 0f) Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, Matrix().apply { postRotate(r) }, true) else bmp
                    }
                }
            }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun ContentResolver.loadThumbnail(uri: Uri, width: Int = 512, height: Int = 384) =
            loadThumbnail(uri, Size(width, height), null)

        fun ContentResolver.getDisplayName(uri: Uri) =
            query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null).use { c ->
                if (c != null && c.moveToFirst() && c.columnCount != 0) c.getString(0) else null
            }

        fun ContentResolver.getSize(uri: Uri) =
            query(uri, arrayOf(OpenableColumns.SIZE), null, null, null).use { c ->
                if (c != null && c.moveToFirst() && c.columnCount != 0) c.getLong(0) else -1
            }
    }
}