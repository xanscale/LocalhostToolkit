package localhost.toolkit.provider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
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

	public byte[] getByteArray() throws IOException {
		InputStream inputStream = context.getContentResolver().openInputStream(uri);
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) != -1)
			byteBuffer.write(buffer, 0, len);
		return byteBuffer.toByteArray();
	}

	public String getMimeType() {
		return context.getContentResolver().getType(uri);
	}

	public File getTempFile() throws IOException {
		String[] split = getMimeType().split("/");
		File file = File.createTempFile(split[0], "." + split[1]);
		InputStream inputStream = context.getContentResolver().openInputStream(uri);
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) != -1)
			outputStream.write(buffer, 0, len);
		inputStream.close();
		outputStream.close();
		return file;
	}

	public byte[] getThumbnailByteArray() {
		Bitmap thumbnail = getThumbnail();
		if (thumbnail != null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			thumbnail.compress(Bitmap.CompressFormat.PNG, 0, stream);
			byte[] byteArray = stream.toByteArray();
			try {
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return byteArray;
		} else
			return null;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public enum MediaType {PHOTO, VIDEO, GENERIC}
}