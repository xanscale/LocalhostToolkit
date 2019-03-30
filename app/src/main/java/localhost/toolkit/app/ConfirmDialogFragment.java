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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ConfirmDialogFragment extends DialogFragment {
	public static final String KEY_ICON = "icon";
	private static final String KEY_MSG = "message";
	private static final String KEY_TITLE = "title";
	private static final String KEY_EXTRA = "extra";
	private static final String KEY_BT_POS = "positiveButtonText";
	private static final String KEY_BT_NEG = "negativeButtonText";
	private static final String KEY_BT_NEU = "neutralButtonText";

	public static ConfirmDialogFragment newInstance(Serializable extra, String title, String msg) {
		return newInstance(extra, title, msg, null, null, null, null);
	}

	public static ConfirmDialogFragment newInstance(Serializable extra, String title, String msg, Bitmap icon, String positiveButtonText, String negativeButtonText, String neutralButtonText) {
		ConfirmDialogFragment fragment = new ConfirmDialogFragment();
		Bundle args = new Bundle();
		args.putString(KEY_TITLE, title);
		args.putString(KEY_MSG, msg);
		args.putString(KEY_BT_POS, positiveButtonText);
		args.putString(KEY_BT_NEG, negativeButtonText);
		args.putString(KEY_BT_NEU, neutralButtonText);
		args.putSerializable(KEY_EXTRA, extra);
		args.putParcelable(KEY_ICON, icon);
		fragment.setArguments(args);
		return fragment;
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		assert getActivity() != null;
		assert getArguments() != null;
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		String title = getArguments().getString(KEY_TITLE);
		String msg = getArguments().getString(KEY_MSG);
		String positiveButtonText = getArguments().getString(KEY_BT_POS);
		String negativeButtonText = getArguments().getString(KEY_BT_NEG);
		String neutralButtonText = getArguments().getString(KEY_BT_NEU);
		Bitmap icon = getArguments().getParcelable(KEY_ICON);
		builder.setTitle(title);
		builder.setMessage(msg);
		if (icon != null)
			builder.setIcon(new BitmapDrawable(getResources(), icon));
		builder.setPositiveButton(positiveButtonText == null ? getString(android.R.string.ok) : positiveButtonText, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				assert getArguments() != null;
				getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_POSITIVE);
			}
		});
		builder.setNegativeButton(negativeButtonText == null ? getString(android.R.string.cancel) : negativeButtonText, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				assert getArguments() != null;
				getOnConfirmedListener().onConfirmation(getArguments().getSerializable(KEY_EXTRA), DialogInterface.BUTTON_NEGATIVE);
			}
		});
		if (neutralButtonText != null)
			builder.setNeutralButton(neutralButtonText, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					assert getArguments() != null;
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