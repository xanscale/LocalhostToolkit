package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class ItemsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TITLE = "TITLE";
    private static final String EXTRA = "EXTRA";
    private static final String ITEMS_ID = "ITEMS_ID";
    private static final String ITEMS = "ITEMS";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (getArguments().containsKey(TITLE))
            builder.setTitle(getArguments().getInt(TITLE));
        if (getArguments().containsKey(ITEMS))
            builder.setItems(getArguments().getStringArray(ITEMS), this);
        else
            builder.setItems(getArguments().getInt(ITEMS_ID), this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        assert getActivity() != null;
        assert getArguments() != null;
        OnListDialogClickListener l = (OnListDialogClickListener) getParentFragment();
        if (l == null)
            l = (OnListDialogClickListener) getActivity();
        l.onClick(getArguments().getSerializable(EXTRA), which);
    }

    public interface OnListDialogClickListener {
        void onClick(Serializable extra, int which);
    }

    public static class Builder {
        private Serializable extra;
        private Integer title;
        private Integer itemsId;
        private String[] items;

        public ItemsDialogFragment build() {
            ItemsDialogFragment fragment = new ItemsDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putInt(TITLE, title);
            if (itemsId != null) args.putInt(ITEMS_ID, itemsId);
            if (items != null) args.putStringArray(ITEMS, items);
            if (extra != null) args.putSerializable(EXTRA, extra);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withExtra(Serializable extra) {
            this.extra = extra;
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