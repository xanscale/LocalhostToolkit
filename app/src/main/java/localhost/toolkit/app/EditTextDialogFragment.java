package localhost.toolkit.app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

import localhost.toolkit.R;

public class EditTextDialogFragment extends DialogFragment {
    private static final String TEXT = "text";
    private static final String INPUT_TYPE = "inputType";
    private static final String TITLE = "title";
    private static final String EXTRA = "extra";
    private EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                assert getArguments() != null;
                if (editText.length() != 0)
                    getOnEditTextListener().onEditTextDialogResult(getArguments().getSerializable(EXTRA), editText.getText().toString());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        View v = View.inflate(getActivity(), R.layout.edittext, null);
        editText = v.findViewById(R.id.editText);
        if (getArguments().containsKey(TEXT))
            editText.setText(getArguments().getString(TEXT));
        editText.setInputType(InputType.TYPE_CLASS_TEXT | getArguments().getInt(INPUT_TYPE));
        builder.setView(v);
        if (getArguments().containsKey(TITLE))
            builder.setTitle(getArguments().getString(TITLE));
        setCancelable(false);
        return builder.create();
    }

    private OnEditTextListener getOnEditTextListener() {
        OnEditTextListener l = (OnEditTextListener) getParentFragment();
        if (l == null)
            l = (OnEditTextListener) getActivity();
        return l;
    }

    public interface OnEditTextListener {
        void onEditTextDialogResult(Serializable extra, String value);
    }

    public class Builder {
        private Serializable extra;
        private String title;
        private String text;
        private int inputType;

        public EditTextDialogFragment build() {
            EditTextDialogFragment fragment = new EditTextDialogFragment();
            Bundle args = new Bundle();
            if (extra != null)
                args.putSerializable(EXTRA, extra);
            if (title != null)
                args.putString(TITLE, title);
            if (text != null)
                args.putString(TEXT, text);
            args.putInt(INPUT_TYPE, inputType);
            fragment.setArguments(args);
            return fragment;
        }

        public Builder withExtra(Serializable extra) {
            this.extra = extra;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withInputType(int inputType) {
            this.inputType = inputType;
            return this;
        }
    }
}