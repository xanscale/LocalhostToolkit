package localhost.toolkit.provider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class MediaPicker {
	private static final int SIZE = 1920;
	private Context context;
	private Uri uri;
	private MediaType mediaType;

	public MediaPicker(Context context) {
		this.context = context;
	}

	public Intent getIntent(MediaType mediaType) {
		this.mediaType = mediaType;
		if (mediaType == MediaType.PHOTO) {
			uri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), System.currentTimeMillis() + ".jpg"));
			return Intent.createChooser(new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, uri), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)});
		} else if (mediaType == MediaType.VIDEO) {
			return Intent.createChooser(new Intent(MediaStore.ACTION_VIDEO_CAPTURE), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)});
		} else
			return null;
	}

	public void onActivityResult(Intent data) {
		if (data != null && data.getData() != null)
			uri = data.getData();
	}

	public Uri getUri() {
		return uri;
	}

	public File getFile() {
		Cursor ca = context.getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DATA}, null, null, null);
		File file = null;
		if (ca != null) {
			if (ca.moveToFirst())
				file = new File(ca.getString(ca.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)));
			ca.close();
		}
		return file;
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

	public Bitmap getBitmap() {
		if (mediaType == MediaType.PHOTO)
			try {
				return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} catch (OutOfMemoryError oom) {
				oom.printStackTrace();
				try {
					BufferedInputStream is = new BufferedInputStream(context.getContentResolver().openInputStream(uri));
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inJustDecodeBounds = true;
					is.mark(is.available());
					BitmapFactory.decodeStream(is, null, options);
					options.inSampleSize = 1;
					if (options.outHeight > SIZE || options.outWidth > SIZE)
						while ((options.outHeight / 2 / options.inSampleSize) >= SIZE && (options.outWidth / 2 / options.inSampleSize) >= SIZE)
							options.inSampleSize *= 2;
					options.inJustDecodeBounds = false;
					is.reset();
					Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
					is.close();
					return bitmap;
				} catch (Exception e1) {
					e1.printStackTrace();
					return null;
				}
			}
		else
			throw new IllegalStateException();
	}

	public byte[] getBitmapByteArray(Bitmap.CompressFormat format, int quality) {
		if (mediaType == MediaType.PHOTO) {
			return getByteArray(getBitmap(), format, quality);
		} else
			throw new IllegalStateException();
	}

	public byte[] getThumbnailByteArray(Bitmap.CompressFormat format, int quality) {
		return getByteArray(getThumbnail(), format, quality);
	}

	private byte[] getByteArray(Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(format, quality, stream);
		byte[] byteArray = stream.toByteArray();
		try {
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return byteArray;
	}

	public enum MediaType {PHOTO, VIDEO}
}