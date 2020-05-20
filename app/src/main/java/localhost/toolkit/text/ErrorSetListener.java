package localhost.toolkit.text;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Set;

public class ErrorSetListener extends AbstractErrorListener {
    private EditText editText;
    private Set<String> stringSet;
    private String errorMsg;

    public ErrorSetListener(EditText editText, String errorMsg, Set<String> stringSet) {
        this.editText = editText;
        this.stringSet = stringSet;
        this.errorMsg = errorMsg;
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(this);
    }

    @Override
    public boolean matches(boolean hidden) {
        boolean matches = stringSet.contains(editText.getText().toString());
        try {
            ((TextInputLayout) editText.getParent().getParent()).setError(matches || hidden ? null : errorMsg);
        } catch (Exception e) {
            editText.setError(matches ? null : errorMsg);
        }
        return matches;
    }
}
