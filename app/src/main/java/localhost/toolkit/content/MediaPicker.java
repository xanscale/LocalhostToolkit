package localhost.toolkit.content;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

public class MediaPicker {
    private Context context;
    private Uri uri;
    private MediaType mediaType;

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
        if (data != null && data.getData() != null)
            uri = data.getData();
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
        if (mediaType == MediaType.IMAGE)
            cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.Thumbnails.DATA}, "kind = " + MediaStore.Images.Thumbnails.MINI_KIND, null, MediaStore.Images.Thumbnails.DEFAULT_SORT_ORDER);
        else if (mediaType == MediaType.VIDEO)
            cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Video.Thumbnails.DATA}, "kind = " + MediaStore.Video.Thumbnails.MINI_KIND, null, MediaStore.Video.Thumbnails.DEFAULT_SORT_ORDER);
        if (cursor != null) {
            if (cursor.moveToFirst())
                return Uri.parse(cursor.getString(0));
            cursor.close();
        }
        return null;
    }

    public enum MediaType {IMAGE, VIDEO, CONTENT}
}