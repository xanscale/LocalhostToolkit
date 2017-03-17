package localhost.toolkit.provider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageManager {
	private Uri uri;

	public Intent getIntent() {
		uri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg"));
		return Intent.createChooser(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, uri), null)
			.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)});
	}

	public Uri getUri(Intent data) {
		return data.getData() == null ? uri : data.getData();
	}

	public static Bitmap getThumbnail(Context context, Uri uri){
		Cursor ca = context.getContentResolver().query(uri, new String[]{MediaStore.MediaColumns._ID}, null, null, null);
		Bitmap thumbnail = null;
		if (ca != null) {
			if (ca.moveToFirst())
				thumbnail = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), ca.getInt(ca.getColumnIndex(MediaStore.MediaColumns._ID)), MediaStore.Images.Thumbnails.MINI_KIND, null);
			ca.close();
		}
		return thumbnail;
	}

	public static Bitmap getBitmap(Context context, Uri uri) {
		try {
			return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getByteArray(Context context, Uri uri) {
		Bitmap bitmap = getBitmap(context, uri);
		if (bitmap == null)
			return null;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
		byte[] byteArray = stream.toByteArray();
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArray;
	}
}