package localhost.toolkit.app.fragment;

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
        if (getArguments().containsKey(TITLE))
            builder.setTitle(getArguments().getInt(TITLE));
        if (getArguments().containsKey(ICON))
            builder.setIcon(getArguments().getInt(ICON));
        ImageView image = new ImageView(getActivity());
        builder.setView(image);
        if (getArguments().containsKey(IMAGE_RESOURCE))
            image.setImageResource(getArguments().getInt(IMAGE_RESOURCE));
        setCancelable(false);
        return builder.create();
    }

    public static class Builder {
        private Integer icon;
        private Integer title;
        private Integer imageResource;

        public ImageDialogFragment build() {
            ImageDialogFragment fragment = new ImageDialogFragment();
            Bundle args = new Bundle();
            if (icon != null) args.putInt(ICON, icon);
            if (title != null) args.putInt(TITLE, title);
            if (imageResource != null) args.putInt(IMAGE_RESOURCE, imageResource);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withIcon(Integer icon) {
            this.icon = icon;
            return this;
        }

        public Builder withTitle(Integer title) {
            this.title = title;
            return this;
        }

        public Builder withImageResource(Integer imageResource) {
            this.imageResource = imageResource;
            return this;
        }
    }
}