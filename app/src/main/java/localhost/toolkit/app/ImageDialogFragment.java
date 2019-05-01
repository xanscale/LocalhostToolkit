package localhost.toolkit.app;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ImageDialogFragment extends DialogFragment {
    private static final String ICON = "ICON";
    private static final String IMAGE_RESOURCE = "IMAGE_RESOURCE";
    private static final String TITLE = "TITLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(android.R.string.ok, null);
        ImageView image = new ImageView(getActivity());
        builder.setView(image);
        image.setImageResource(getArguments().getInt(IMAGE_RESOURCE));
        builder.setTitle(getArguments().getInt(TITLE));
        builder.setIcon(getArguments().getInt(ICON));
        setCancelable(false);
        return builder.create();
    }

    public class Builder {
        private int icon;
        private int title;
        private int imageResource;

        public ImageDialogFragment build() {
            ImageDialogFragment fragment = new ImageDialogFragment();
            Bundle args = new Bundle();
            args.putInt(ICON, icon);
            args.putInt(TITLE, title);
            args.putInt(IMAGE_RESOURCE, imageResource);
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

        public Builder withImageResource(int imageResource) {
            this.imageResource = imageResource;
            return this;
        }
    }
}