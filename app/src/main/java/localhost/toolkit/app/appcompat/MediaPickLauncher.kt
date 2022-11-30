package localhost.toolkit.app.appcompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import localhost.toolkit.content.FileProvider;

public class MediaPickLauncher extends LiveData<MediaPickLauncher.Media> implements ActivityResultCallback<MediaPickLauncher.Media> {
    private final ActivityResultLauncher<ContractType> launcher;

    public MediaPickLauncher(Fragment fragment) {
        launcher = fragment.registerForActivityResult(new MediaPickContract(fragment), this);
    }

    public MediaPickLauncher(FragmentActivity activity) {
        launcher = activity.registerForActivityResult(new MediaPickContract(activity), this);
    }

    @Override
    public void onActivityResult(Media result) {
        setValue(result);
    }

    public void launch(ContractType contractType) {
        launcher.launch(contractType);
    }

    private static class MediaPickContract extends ActivityResultContract<ContractType, Media> {
        private final ViewModelStoreOwner owner;
        private MediaViewModel mediaViewModel;

        public MediaPickContract(ViewModelStoreOwner owner) {
            this.owner = owner;
        }

        @CallSuper
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, @NonNull ContractType input) {
            getViewModel().contractType = input;
            switch (input) {
                case IMAGE:
                    Intent intent = Intent.createChooser(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), null);
                    try {
                        getViewModel().uri = FileProvider.getUriForFile(context, context.getPackageName() + ".localhost.provider", File.createTempFile("image", ".jpg"));
                        return intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, getViewModel().uri)});
                    } catch (IOException e) {
                        e.printStackTrace();
                        return intent;
                    }
                case VIDEO:
                    return Intent.createChooser(new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI), null)
                            .putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(MediaStore.ACTION_VIDEO_CAPTURE)});
                default:
                    return new Intent(Intent.ACTION_GET_CONTENT).setType("*/*").addCategory(Intent.CATEGORY_OPENABLE);
            }
        }

        @Override
        public final Media parseResult(int resultCode, @Nullable Intent intent) {
            return resultCode != Activity.RESULT_OK ? null : new Media(getViewModel().contractType, intent != null && intent.getData() != null ? intent.getData() : getViewModel().uri);
        }

        private MediaViewModel getViewModel() {
            if (mediaViewModel == null)
                mediaViewModel = new ViewModelProvider(owner).get(MediaViewModel.class);
            return mediaViewModel;
        }
    }

    public static class MediaViewModel extends ViewModel {
        public ContractType contractType;
        public Uri uri;
    }

    public static class Media {
        private final ContractType contractType;
        private final Uri uri;

        public Media(ContractType contractType, Uri uri) {
            this.contractType = contractType;
            this.uri = uri;
        }

        public Uri getUri() {
            return uri;
        }

        public String getMimeType(Context context) {
            return context.getContentResolver().getType(uri);
        }

        public InputStream openInputStream(Context context) throws FileNotFoundException {
            return context.getContentResolver().openInputStream(uri);
        }

        public Bitmap getBitmap(Context context) throws IOException {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                return ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.getContentResolver(), uri));
            else {
                Bitmap bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                int rotation = getRotation(context);
                if (rotation != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(rotation);
                    bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
                }
                return bmp;
            }
        }

        public int getOrientation(Context context) throws IOException {
            if (contractType == ContractType.IMAGE)
                try (InputStream input = openInputStream(context)) {
                    return new ExifInterface(input).getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                }
            else throw new IllegalStateException("ContractType: " + contractType);
        }

        public int getRotation(Context context) throws IOException {
            if (contractType == ContractType.IMAGE)
                switch (getOrientation(context)) {
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;
                    default:
                        return 0;
                }
            else throw new IllegalStateException("ContractType: " + contractType);
        }

        public Uri getThumbnailUri(Context context) {
            if (contractType == ContractType.IMAGE)
                try (Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Thumbnails.DATA}, "kind = " + MediaStore.Images.Thumbnails.MINI_KIND, null, MediaStore.Images.Thumbnails.DEFAULT_SORT_ORDER)) {
                    return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? Uri.parse(cursor.getString(0)) : null;
                }
            else if (contractType == ContractType.VIDEO)
                try (Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Video.Thumbnails.DATA}, "kind = " + MediaStore.Video.Thumbnails.MINI_KIND, null, MediaStore.Video.Thumbnails.DEFAULT_SORT_ORDER)) {
                    return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? Uri.parse(cursor.getString(0)) : null;
                }
            else throw new IllegalStateException("ContractType: " + contractType);
        }

        public String getDisplayName(Context context) {
            try (Cursor cursor = context.getContentResolver().query(uri, new String[]{OpenableColumns.DISPLAY_NAME}, null, null, null)) {
                return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? cursor.getString(0) : null;
            }
        }

        public long getSize(Context context) {
            try (Cursor cursor = context.getContentResolver().query(uri, new String[]{OpenableColumns.SIZE}, null, null, null)) {
                return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? cursor.getLong(0) : -1;
            }
        }
    }

    public enum ContractType {IMAGE, VIDEO, CONTENT}
}
