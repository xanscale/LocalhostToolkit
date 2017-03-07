package localhost.toolkit.provider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ThumbnailManager {
	public static final Intent PICK_OR_CAPTURE_INTENT = Intent.createChooser(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), null).putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)});
	public static final String DATA = "data";
	private static final String BITMAP = "ThumbnailManager.BITMAP";
	private static final String BYTE_ARRAY = "ThumbnailManager.BYTEARRAY";
	private Context context;
	private Bitmap thumbnail;
	private byte[] byteArray;

	public ThumbnailManager(Context context) {
		this.context = context;
	}

	public void onActivityResult(Intent data) {
		if (data.getExtras() != null)
			thumbnail = (Bitmap) data.getExtras().get(DATA);
		if (thumbnail == null) {
			Cursor ca = context.getContentResolver().query(data.getData(), new String[]{MediaStore.MediaColumns._ID}, null, null, null);
			if (ca != null) {
				if (ca.moveToFirst())
					thumbnail = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), ca.getInt(ca.getColumnIndex(MediaStore.MediaColumns._ID)), MediaStore.Images.Thumbnails.MINI_KIND, null);
				ca.close();
			}
		}
		if (thumbnail != null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			thumbnail.compress(Bitmap.CompressFormat.PNG, 0, stream);
			byteArray = stream.toByteArray();
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP, thumbnail);
		outState.putByteArray(BYTE_ARRAY, byteArray);
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		thumbnail = savedInstanceState.getParcelable(BITMAP);
		byteArray = savedInstanceState.getByteArray(BYTE_ARRAY);
	}

	public Bitmap getThumbnail() {
		return thumbnail;
	}

	public byte[] getByteArray() {
		return byteArray;
	}
}
