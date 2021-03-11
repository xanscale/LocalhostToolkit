package localhost.toolkit.app.appcompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        public Uri getThumbnailUri(Context context) {
            if (contractType == ContractType.IMAGE)
                try (Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Thumbnails.DATA}, "kind = " + MediaStore.Images.Thumbnails.MINI_KIND, null, MediaStore.Images.Thumbnails.DEFAULT_SORT_ORDER)) {
                    return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? Uri.parse(cursor.getString(0)) : null;
                }
            else if (contractType == ContractType.VIDEO)
                try (Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Video.Thumbnails.DATA}, "kind = " + MediaStore.Video.Thumbnails.MINI_KIND, null, MediaStore.Video.Thumbnails.DEFAULT_SORT_ORDER)) {
                    return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? Uri.parse(cursor.getString(0)) : null;
                }
            else return null;
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
