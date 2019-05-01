package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class ItemsDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_EXTRA = "KEY_EXTRA";
    private static final String KEY_LIST_RESID = "KEY_LIST_RESID";
    private static final String KEY_LIST_STRGS = "KEY_LIST_STRGS";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getInt(KEY_TITLE));
        if (getArguments().containsKey(KEY_LIST_STRGS))
            builder.setItems(getArguments().getStringArray(KEY_LIST_STRGS), this);
        else
            builder.setItems(getArguments().getInt(KEY_LIST_RESID), this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        assert getActivity() != null;
        assert getArguments() != null;
        OnListDialogClickListener l = (OnListDialogClickListener) getParentFragment();
        if (l == null)
            l = (OnListDialogClickListener) getActivity();
        l.onClick(getArguments().getSerializable(KEY_EXTRA), which);
    }

    public interface OnListDialogClickListener {
        void onClick(Serializable extra, int which);
    }

    public class Builder {
        private Serializable extra;
        private int title;
        private int listResId;
        private String[] list;

        public ItemsDialogFragment build() {
            ItemsDialogFragment fragment = new ItemsDialogFragment();
            Bundle args = new Bundle();
            args.putInt(KEY_TITLE, title);
            args.putInt(KEY_LIST_RESID, listResId);
            if (list != null)
                args.putStringArray(KEY_LIST_STRGS, list);
            if (extra != null)
                args.putSerializable(KEY_EXTRA, extra);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withExtra(Serializable extra) {
            this.extra = extra;
            return this;
        }

        public Builder withTitle(int title) {
            this.title = title;
            return this;
        }

        public Builder withListResId(int listResId) {
            this.listResId = listResId;
            return this;
        }

        public Builder withList(String[] list) {
            this.list = list;
            return this;
        }
    }
}