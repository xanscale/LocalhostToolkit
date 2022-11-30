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
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper
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
import java.io.IOException
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI as ImagesMediaEXTERNAL_CONTENT_URI
import android.provider.MediaStore.Images.Thumbnails.DATA as ImagesThumbsDATA
import android.provider.MediaStore.Images.Thumbnails.DEFAULT_SORT_ORDER as ImagesThumbsDEFAULT_SORT_ORDER
import android.provider.MediaStore.Images.Thumbnails.MINI_KIND as ImagesThumbsMINI_KIND
import android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI as VideoMediaEXTERNAL_CONTENT_URI
import android.provider.MediaStore.Video.Thumbnails.DATA as VideoThumbsDATA
import android.provider.MediaStore.Video.Thumbnails.DEFAULT_SORT_ORDER as VideoThumbsDEFAULT_SORT_ORDER
import android.provider.MediaStore.Video.Thumbnails.MINI_KIND as VideoThumbsMINI_KIND

class MediaPickLauncher : LiveData<MediaPickLauncher.Media?>, ActivityResultCallback<MediaPickLauncher.Media?> {
    private val launcher: ActivityResultLauncher<ContractType>

    constructor(fragment: Fragment) {
        launcher = fragment.registerForActivityResult(MediaPickContract(fragment), this)
    }

    constructor(activity: FragmentActivity) {
        launcher = activity.registerForActivityResult(MediaPickContract(activity), this)
    }

    override fun onActivityResult(result: Media?) {
        value = result
    }

    fun launch(contractType: ContractType) {
        launcher.launch(contractType)
    }

    private class MediaPickContract(private val owner: ViewModelStoreOwner) : ActivityResultContract<ContractType, Media?>() {
        private val viewModel by lazy { ViewModelProvider(owner)[MediaViewModel::class.java] }

        @CallSuper
        override fun createIntent(context: Context, input: ContractType): Intent = when (input) {
            ContractType.IMAGE -> {
                Intent.createChooser(Intent(Intent.ACTION_PICK, ImagesMediaEXTERNAL_CONTENT_URI), null).apply {
                    try {
                        viewModel.uri = FileProvider.getUriForFile(context, context.packageName + ".localhost.provider", File.createTempFile("image", ".jpg"))
                        putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, viewModel.uri)))
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            ContractType.VIDEO -> Intent.createChooser(Intent(Intent.ACTION_PICK, VideoMediaEXTERNAL_CONTENT_URI), null)
                .putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(Intent(MediaStore.ACTION_VIDEO_CAPTURE)))
            else -> Intent(Intent.ACTION_GET_CONTENT).setType("*/*").addCategory(Intent.CATEGORY_OPENABLE)
        }.also { viewModel.contractType = input }

        override fun parseResult(resultCode: Int, intent: Intent?) =
            if (resultCode != Activity.RESULT_OK) null
            else Media(viewModel.contractType!!, intent?.data ?: viewModel.uri!!)
    }

    class MediaViewModel : ViewModel() {
        var contractType: ContractType? = null
        var uri: Uri? = null
    }

    class Media(private val contractType: ContractType, val uri: Uri) {
        fun getMimeType(cr: ContentResolver) =
            cr.getType(uri)

        @Throws(IOException::class)
        fun openInputStream(cr: ContentResolver) =
            cr.openInputStream(uri) ?: throw IOException("inputStream null")

        @Throws(IOException::class)
        fun getOrientation(cr: ContentResolver) =
            if (contractType == ContractType.IMAGE)
                openInputStream(cr).use { input ->
                    ExifInterface(input).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
                }
            else throw IllegalStateException("ContractType $contractType not allowed")

        @Throws(IOException::class)
        fun getRotation(cr: ContentResolver) =
            if (contractType == ContractType.IMAGE) when (getOrientation(cr)) {
                ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                else -> 0f
            } else throw IllegalStateException("ContractType $contractType not allowed")

        @Suppress("DEPRECATION")
        @Throws(IOException::class)
        fun getBitmap(cr: ContentResolver): Bitmap =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) ImageDecoder.decodeBitmap(ImageDecoder.createSource(cr, uri))
            else {
                MediaStore.Images.Media.getBitmap(cr, uri).let { bmp ->
                    getRotation(cr).let { r ->
                        if (r != 0f) Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, Matrix().apply { postRotate(r) }, true) else bmp
                    }
                }
            }

        fun getThumbnailUri(cr: ContentResolver) =
            when (contractType) {
                ContractType.IMAGE ->
                    cr.query(uri, arrayOf(ImagesThumbsDATA), "kind = $ImagesThumbsMINI_KIND", null, ImagesThumbsDEFAULT_SORT_ORDER).use { c ->
                        if (c != null && c.moveToFirst() && c.columnCount != 0) Uri.parse(c.getString(0)) else null
                    }
                ContractType.VIDEO ->
                    cr.query(uri, arrayOf(VideoThumbsDATA), "kind = $VideoThumbsMINI_KIND", null, VideoThumbsDEFAULT_SORT_ORDER).use { c ->
                        if (c != null && c.moveToFirst() && c.columnCount != 0) Uri.parse(c.getString(0)) else null
                    }
                else -> throw IllegalStateException("ContractType $contractType not allowed")
            }

        @RequiresApi(Build.VERSION_CODES.Q)
        fun loadThumbnail(cr: ContentResolver) =
            cr.loadThumbnail(uri, Size(512, 384), null)

        fun getDisplayName(cr: ContentResolver) =
            cr.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null).use { c ->
                if (c != null && c.moveToFirst() && c.columnCount != 0) c.getString(0) else null
            }

        fun getSize(cr: ContentResolver) =
            cr.query(uri, arrayOf(OpenableColumns.SIZE), null, null, null).use { c ->
                if (c != null && c.moveToFirst() && c.columnCount != 0) c.getLong(0) else -1
            }
    }

    enum class ContractType {
        IMAGE, VIDEO, CONTENT
    }
}