package localhost.toolkit.app.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

import localhost.toolkit.R;

public class EditTextDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    private static final String TEXT = "TEXT";
    private static final String HINT = "HINT";
    private static final String INPUT_TYPE = "INPUT_TYPE";
    private static final String TITLE = "TITLE";
    private static final String SERIALIZABLE = "SERIALIZABLE";
    private static final String PARCELABLE = "PARCELABLE";
    private EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle(requireArguments().getString(TITLE));
        builder.setPositiveButton(android.R.string.ok, this);
        builder.setNegativeButton(android.R.string.cancel, null);
        View v = View.inflate(requireContext(), R.layout.dialog_edittext, null);
        editText = v.findViewById(R.id.textInputEditText);
        TextInputLayout til = v.findViewById(R.id.textInputLayout);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | requireArguments().getInt(INPUT_TYPE, 0));
        editText.setText(requireArguments().getString(TEXT));
        til.setHint(requireArguments().getString(HINT));
        builder.setView(v);
        setCancelable(false);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        getOnEditTextListener().onEditTextDialogResult(requireArguments().getSerializable(SERIALIZABLE), requireArguments().getParcelable(PARCELABLE), editText.getText().toString());
    }

    private OnEditTextListener getOnEditTextListener() {
        OnEditTextListener l = (OnEditTextListener) getParentFragment();
        if (l == null)
            l = (OnEditTextListener) requireActivity();
        return l;
    }

    public interface OnEditTextListener {
        void onEditTextDialogResult(Serializable serializable, Parcelable parcelable, String value);
    }

    public static class Builder {
        private Serializable serializable;
        private Parcelable parcelable;
        private String title;
        private String text;
        private String hint;
        private Integer inputType;

        public EditTextDialogFragment build() {
            EditTextDialogFragment fragment = new EditTextDialogFragment();
            Bundle args = new Bundle();
            if (title != null) args.putString(TITLE, title);
            if (text != null) args.putString(TEXT, text);
            if (hint != null) args.putString(HINT, hint);
            if (inputType != null) args.putInt(INPUT_TYPE, inputType);
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