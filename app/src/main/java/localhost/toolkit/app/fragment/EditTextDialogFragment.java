package localhost.toolkit.app.fragment;

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

import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

import localhost.toolkit.R;

public class EditTextDialogFragment extends DialogFragment {
    private static final String TEXT = "TEXT";
    private static final String HINT = "HINT";
    private static final String INPUT_TYPE = "INPUT_TYPE";
    private static final String TITLE = "TITLE";
    private static final String EXTRA = "EXTRA";
    private EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getActivity() != null;
        assert getArguments() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString(TITLE));
        builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                assert getArguments() != null;
                getOnEditTextListener().onEditTextDialogResult(getArguments().getSerializable(EXTRA), editText.getText().toString());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        View v = View.inflate(getActivity(), R.layout.dialog_edittext, null);
        editText = v.findViewById(R.id.textInputEditText);
        TextInputLayout til = v.findViewById(R.id.textInputLayout);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | getArguments().getInt(INPUT_TYPE, 0));
        editText.setText(getArguments().getString(TEXT));
        til.setHint(getArguments().getString(HINT));
        builder.setView(v);
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

    public static class Builder {
        private Serializable extra;
        private String title;
        private String text;
        private String hint;
        private Integer inputType;

        public EditTextDialogFragment build() {
            EditTextDialogFragment fragment = new EditTextDialogFragment();
            Bundle args = new Bundle();
            if (extra != null) args.putSerializable(EXTRA, extra);
            if (title != null) args.putString(TITLE, title);
            if (text != null) args.putString(TEXT, text);
            if (hint != null) args.putString(HINT, hint);
            if (inputType != null) args.putInt(INPUT_TYPE, inputType);
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

        public Builder withHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder withInputType(Integer inputType) {
            this.inputType = inputType;
            return this;
        }
    }
}