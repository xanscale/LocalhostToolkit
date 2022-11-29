package localhost.toolkit.text;

import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public abstract class AbstractErrorListener implements View.OnFocusChangeListener {
    protected final EditText editText;
    private final String errorMsg;
    private View.OnFocusChangeListener onFocusChangeListener;

    public AbstractErrorListener(String errorMsg, EditText editText) {
        this.editText = editText;
        this.errorMsg = errorMsg;
        editText.setOnFocusChangeListener(this);
    }

    abstract public boolean matches();

    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            clearError();
        else
            matches(true);
        if (onFocusChangeListener != null)
            onFocusChangeListener.onFocusChange(v, hasFocus);
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

    private void clearError() {
        try {
            ((TextInputLayout) editText.getParent().getParent()).setError(null);
        } catch (Exception e) {
            editText.setError(null);
        }
    }

    public EditText getEditText() {
        return editText;
    }
}