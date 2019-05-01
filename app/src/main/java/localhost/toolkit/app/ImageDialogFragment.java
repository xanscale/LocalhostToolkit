package localhost.toolkit.app;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment {
    private static final String KEY_ICON = "KEY_ICON";
    private static final String KEY_IMG = "KEY_IMG";
    private static final String KEY_TITLE = "KEY_TITLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(android.R.string.ok, null);
        ImageView image = new ImageView(getActivity());
        builder.setView(image);
        image.setImageResource(getArguments().getInt(KEY_IMG));
        builder.setTitle(getArguments().getInt(KEY_TITLE));
        builder.setIcon(getArguments().getInt(KEY_ICON));
        setCancelable(false);
        return builder.create();
    }

    public class Builder {
        private int icon;
        private int title;
        private int image;

        public ImageDialogFragment build() {
            ImageDialogFragment fragment = new ImageDialogFragment();
            Bundle args = new Bundle();
            args.putInt(KEY_ICON, icon);
            args.putInt(KEY_TITLE, title);
            args.putInt(KEY_IMG, image);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder withTitle(int title) {
            this.title = title;
            return this;
        }

        public Builder withImage(int image) {
            this.image = image;
            return this;
        }
    }
}