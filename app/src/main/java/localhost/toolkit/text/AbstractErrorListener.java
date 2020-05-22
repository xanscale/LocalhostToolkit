package localhost.toolkit.text;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public abstract class AbstractErrorListener implements TextWatcher, View.OnFocusChangeListener {
    protected EditText[] editTexts;
    private String errorMsg;

    public AbstractErrorListener(String errorMsg, EditText... editTexts) {
        this.editTexts = editTexts;
        this.errorMsg = errorMsg;
        for (EditText editText : editTexts) {
            editText.addTextChangedListener(this);
            editText.setOnFocusChangeListener(this);
        }
    }

    abstract public boolean matches();

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus)
            matches(true);
    }

    public boolean matches(boolean showError) {
        boolean matches = matches();
        if (showError && !matches)
            for (EditText editText : editTexts)
                try {
                    ((TextInputLayout) editText.getParent().getParent()).setError(errorMsg);
                } catch (Exception e) {
                    editText.setError(errorMsg);
                }
        return matches;
    }

    @Override
    public void afterTextChanged(Editable s) {
        for (EditText editText : editTexts)
            try {
                ((TextInputLayout) editText.getParent().getParent()).setError(null);
            } catch (Exception e) {
                editText.setError(null);
            }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}