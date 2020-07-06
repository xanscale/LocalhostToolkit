package localhost.toolkit.content;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MediaPicker {
    private static final int SIZE = 1920;
    private Context context;
    private Uri uri;
    private MediaType mediaType;

    public MediaPicker(Context context) {
        this.context = context;
    }

    public Intent getIntent(MediaType mediaType) throws IOException {
        this.mediaType = mediaType;
        switch (mediaType) {
            case PHOTO:
                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".localhost.provider", File.createTempFile("image", ".jpg"));
                return Intent.createChooser(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, uri)});
            case VIDEO:
                return Intent.createChooser(new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(MediaStore.ACTION_VIDEO_CAPTURE)});
            case GENERIC:
                return new Intent(Intent.ACTION_GET_CONTENT).setType("*/*");
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

    public Bitmap getThumbnail() {
        Cursor ca = context.getContentResolver().query(uri, new String[]{MediaStore.MediaColumns._ID}, null, null, null);
        Bitmap thumbnail = null;
        if (ca != null) {
            if (ca.moveToFirst())
                if (mediaType == MediaType.PHOTO)
                    thumbnail = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), ca.getInt(ca.getColumnIndex(MediaStore.MediaColumns._ID)), MediaStore.Images.Thumbnails.MINI_KIND, null);
                else if (mediaType == MediaType.VIDEO)
                    thumbnail = MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(), ca.getInt(ca.getColumnIndex(MediaStore.MediaColumns._ID)), MediaStore.Video.Thumbnails.MINI_KIND, null);
            ca.close();
        }
        return thumbnail;
    }

    public byte[] getByteArray() throws IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) != -1)
            output.write(buffer, 0, len);
        byte[] byteArray = output.toByteArray();
        try {
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public String getBase64() throws IOException {
        return Base64.encodeToString(getByteArray(), Base64.DEFAULT);
    }

    public File createTempFile() throws IOException {
        String[] split = getMimeType().split("/");
        File file = File.createTempFile(split[0], "." + split[1]);
        InputStream input = context.getContentResolver().openInputStream(uri);
        OutputStream output = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) != -1)
            output.write(buffer, 0, len);
        try {
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public byte[] getThumbnailByteArray() {
        Bitmap thumbnail = getThumbnail();
        if (thumbnail != null) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.PNG, 0, output);
            byte[] byteArray = output.toByteArray();
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return byteArray;
        } else
            return null;
    }

    public enum MediaType {PHOTO, VIDEO, GENERIC}
}