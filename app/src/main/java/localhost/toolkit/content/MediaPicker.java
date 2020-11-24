package localhost.toolkit.content;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.IOException;

public class MediaPicker {
    private Context context;
    private Uri uri;
    private MediaType mediaType;
    private int resultCode = Activity.RESULT_CANCELED;

    public MediaPicker(Context context) {
        this.context = context;
    }

    public Intent getIntent(MediaType mediaType) throws IOException {
        this.mediaType = mediaType;
        switch (mediaType) {
            case IMAGE:
                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".localhost.provider", File.createTempFile("image", ".jpg"));
                return Intent.createChooser(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, uri)});
            case VIDEO:
                return Intent.createChooser(new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(MediaStore.ACTION_VIDEO_CAPTURE)});
            case CONTENT:
                return new Intent(Intent.ACTION_GET_CONTENT).setType("*/*").addCategory(Intent.CATEGORY_OPENABLE);
            default:
                throw new IllegalStateException();
        }
    }

    public void onActivityResult(Intent data) {
        if (data != null && data.getData() != null) {
            uri = data.getData();
            resultCode = Activity.RESULT_OK;
        }
    }

    public int getResultCode() {
        return resultCode;
    }

    public Uri getUri() {
        return uri;
    }

    public String getMimeType() {
        return context.getContentResolver().getType(uri);
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Uri getThumbnailUri() {
        Cursor cursor = null;
        try {
            if (mediaType == MediaType.IMAGE)
                cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Thumbnails.DATA}, "kind = " + MediaStore.Images.Thumbnails.MINI_KIND, null, MediaStore.Images.Thumbnails.DEFAULT_SORT_ORDER);
            else if (mediaType == MediaType.VIDEO)
                cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Video.Thumbnails.DATA}, "kind = " + MediaStore.Video.Thumbnails.MINI_KIND, null, MediaStore.Video.Thumbnails.DEFAULT_SORT_ORDER);
            return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? Uri.parse(cursor.getString(0)) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public String getDisplayName() {
        Cursor cursor = context.getContentResolver().query(uri, new String[]{OpenableColumns.DISPLAY_NAME}, null, null, null);
        try {
            return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? cursor.getString(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public long getSize() {
        Cursor cursor = context.getContentResolver().query(uri, new String[]{OpenableColumns.SIZE}, null, null, null);
        try {
            return cursor != null && cursor.moveToFirst() && cursor.getColumnCount() != 0 ? cursor.getLong(0) : -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    public enum MediaType {IMAGE, VIDEO, CONTENT}
}