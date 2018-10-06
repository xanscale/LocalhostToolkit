package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmDialogFragment extends DialogFragment {
	public static final String KEY_ICON = "icon";
	private static final String KEY_MSG = "message";
	private static final String KEY_TITLE = "title";
	private static final String KEY_EXTRA = "extra";
	private static final String KEY_NBT = "neutralButtonText";

	public static ConfirmDialogFragment newInstance(Serializable extra, String title, String msg) {
		ConfirmDialogFragment fragment = new ConfirmDialogFragment();
		fragment.setArguments(getBundle(extra, title, msg, null, null));
		return fragment;
	}

	public static ConfirmDialogFragment newInstance(Serializable extra, String title, String msg, Bitmap icon, String neutralButtonText) {
		ConfirmDialogFragment fragment = new ConfirmDialogFragment();
		fragment.setArguments(getBundle(extra, title, msg, icon, neutralButtonText));
		return fragment;
	}

	private static Bundle getBundle(Serializable extra, String title, String msg, Bitmap icon, String neutralButtonText) {
		Bundle args = new Bundle();
		args.putString(KEY_TITLE, title);
		args.putString(KEY_MSG, msg);
		args.putString(KEY_NBT, neutralButtonText);
		args.putSerializable(KEY_EXTRA, extra);
		args.putParcelable(KEY_ICON, icon);
		return args;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		String title = getArguments().getString(KEY_TITLE);
		String msg = getArguments().getString(KEY_MSG);
		String neutralButtonText = getArguments().getString(KEY_NBT);
		Bitmap icon = getArguments().getParcelable(KEY_ICON);
		builder.setTitle(title);
		builder.setMessage(msg);
		if (icon != null)
			builder.setIcon(new BitmapDrawable(getResources(), icon));
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_POSITIVE);
			}
		});
		builder.setNegativeButton(android.R.string.cancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEGATIVE);
			}
		});
		if (neutralButtonText != null)
			builder.setNeutralButton(neutralButtonText, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEUTRAL);
				}
			});
		setCancelable(false);
		builder.setCancelable(false);
		return builder.create();
	}

	private OnConfirmedListener getOnConfirmedListener() {
		OnConfirmedListener l = (OnConfirmedListener) getParentFragment();
		if (l == null)
			l = (OnConfirmedListener) getActivity();
		return l;
	}

	@Retention(RetentionPolicy.SOURCE) @IntDef({DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL}) @interface ConfirmDialogButton {
	}

	public interface OnConfirmedListener {
		void onConfirmation(Serializable extra, @ConfirmDialogButton int buttonClicked);
	}
}