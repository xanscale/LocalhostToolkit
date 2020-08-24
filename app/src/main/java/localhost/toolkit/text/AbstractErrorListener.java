package localhost.toolkit.text;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public abstract class AbstractErrorListener implements TextWatcher, View.OnFocusChangeListener {
    protected EditText editText;
    private String errorMsg;

    public AbstractErrorListener(String errorMsg, EditText editText) {
        this.editText = editText;
        this.errorMsg = errorMsg;
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(this);
    }

    abstract public boolean matches();

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            matches(true);
        }
    }

    public boolean matches(boolean showError) {
        boolean matches = matches();
        if (matches)
            clearError();
        else if (showError)
            try {
                ((TextInputLayout) editText.getParent().getParent()).setError(errorMsg);
            } catch (Exception e) {
                editText.setError(errorMsg);
            }
        return matches;
    }

    @Override
    public void afterTextChanged(Editable s) {
        clearError();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    private void clearError() {
        try {
            ((TextInputLayout) editText.getParent().getParent()).setError(null);
        } catch (Exception e) {
            editText.setError(null);
        }
    }
}