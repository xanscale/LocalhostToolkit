package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;

public class ItemsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TITLE = "TITLE";
    private static final String ITEMS_ID = "ITEMS_ID";
    private static final String ITEMS = "ITEMS";
    private static final String SERIALIZABLE = "SERIALIZABLE";
    private static final String PARCELABLE = "PARCELABLE";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        if (requireArguments().containsKey(TITLE))
            builder.setTitle(requireArguments().getInt(TITLE));
        if (requireArguments().containsKey(ITEMS))
            builder.setItems(requireArguments().getStringArray(ITEMS), this);
        else
            builder.setItems(requireArguments().getInt(ITEMS_ID), this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        getOnListDialogClickListener().onClick(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), which);
    }

    private OnListDialogClickListener getOnListDialogClickListener() {
        OnListDialogClickListener l = (OnListDialogClickListener) getParentFragment();
        if (l == null)
            l = (OnListDialogClickListener) requireActivity();
        return l;
    }

    public interface OnListDialogClickListener {
        void onClick(Serializable serializable, Parcelable parcelable, int which);
    }

    public static class Builder {
        private Serializable serializable;
        private Parcelable parcelable;
        private Integer title;
        private Integer itemsId;
        private String[] items;

        public ItemsDialogFragment build() {
            ItemsDialogFragment fragment = new ItemsDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putInt(TITLE, title);
            if (itemsId != null) args.putInt(ITEMS_ID, itemsId);
            if (items != null) args.putStringArray(ITEMS, items);
            if (serializable != null) args.putSerializable(SERIALIZABLE, serializable);
            if (parcelable != null) args.putParcelable(PARCELABLE, parcelable);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withSerializable(Serializable serializable) {
            this.serializable = serializable;
            return this;
        }

        public Builder withParcelable(Parcelable parcelable) {
            this.parcelable = parcelable;
            return this;
        }

        public Builder withTitle(Integer title) {
            this.title = title;
            return this;
        }

        public Builder withItemsId(Integer itemsId) {
            this.itemsId = itemsId;
            return this;
        }

        public Builder withItems(String[] items) {
            this.items = items;
            return this;
        }
    }
}