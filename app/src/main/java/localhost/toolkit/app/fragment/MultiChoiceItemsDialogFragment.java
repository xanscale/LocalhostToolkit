package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.Serializable;

public class MultiChoiceItemsDialogFragment extends DialogFragment implements DialogInterface.OnMultiChoiceClickListener, DialogInterface.OnClickListener {
    private static final String CHECKED_ITEMS = "CHECKED_ITEMS";
    private static final String TITLE = "TITLE";
    private static final String EXTRA = "EXTRA";
    private static final String ITEMS_ID = "ITEMS_ID";
    private static final String ITEMS = "ITEMS";
    private boolean[] checkedItems;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        if (requireArguments().containsKey(TITLE))
            builder.setTitle(requireArguments().getInt(TITLE));
        checkedItems = requireArguments().getBooleanArray(CHECKED_ITEMS);
        if (requireArguments().containsKey(ITEMS)) {
            String[] items = requireArguments().getStringArray(ITEMS);
            if (checkedItems == null)
                checkedItems = new boolean[items.length];
            builder.setMultiChoiceItems(items, checkedItems, this);
        } else {
            int itemsId = requireArguments().getInt(ITEMS_ID);
            if (checkedItems == null)
                checkedItems = new boolean[getResources().getTextArray(itemsId).length];
            builder.setMultiChoiceItems(itemsId, checkedItems, this);
        }
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, this);
        return builder.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        OnMultiChoiceDialogClickListener l = (OnMultiChoiceDialogClickListener) getParentFragment();
        if (l == null)
            l = (OnMultiChoiceDialogClickListener) requireActivity();
        l.onClick(requireArguments().getSerializable(EXTRA), checkedItems);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        checkedItems[which] = isChecked;
    }

    public interface OnMultiChoiceDialogClickListener {
        void onClick(Serializable extra, boolean[] checkedItems);
    }

    public static class Builder {
        private Serializable extra;
        private Integer title;
        private Integer itemsId;
        private boolean[] checkedItems;
        private String[] items;

        public MultiChoiceItemsDialogFragment build() {
            MultiChoiceItemsDialogFragment fragment = new MultiChoiceItemsDialogFragment();
            Bundle args = new Bundle();
            if (extra != null) args.putSerializable(EXTRA, extra);
            if (title != null) args.putInt(TITLE, title);
            if (itemsId != null) args.putInt(ITEMS_ID, itemsId);
            if (items != null) args.putStringArray(ITEMS, items);
            if (checkedItems != null) args.putBooleanArray(CHECKED_ITEMS, checkedItems);
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

        public Builder withCheckedItems(boolean[] checkedItems) {
            this.checkedItems = checkedItems;
            return this;
        }

        public Builder withItems(String[] items) {
            this.items = items;
            return this;
        }
    }
}