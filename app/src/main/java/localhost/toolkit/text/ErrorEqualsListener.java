package localhost.toolkit.text;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ErrorEqualsListener extends AbstractErrorListener {
    private EditText editText1, editText2;
    private String errorMsg;

    public ErrorEqualsListener(EditText editText1, EditText editText2, String errorMsg) {
        this.editText1 = editText1;
        this.editText2 = editText2;
        this.errorMsg = errorMsg;
        editText1.addTextChangedListener(this);
        editText2.addTextChangedListener(this);
    }

    @Override
    public boolean matches(boolean hidden) {
        boolean matches = editText1.getText().toString().equals(editText2.getText().toString());
        try {
            ((TextInputLayout) editText1.getParent().getParent()).setError(matches || hidden ? null : errorMsg);
        } catch (Exception e) {
            editText1.setError(matches ? null : errorMsg);
        }
        try {
            ((TextInputLayout) editText2.getParent().getParent()).setError(matches || hidden ? null : errorMsg);
        } catch (Exception e) {
            editText2.setError(matches ? null : errorMsg);
        }
        return matches;
    }
}
