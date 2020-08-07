package localhost.toolkit.text;

import android.widget.EditText;

public class ErrorEqualsListener extends AbstractErrorListener {
    private EditText reference;

    public ErrorEqualsListener(String errorMsg, EditText subject, EditText reference) {
        super(errorMsg, subject);
        this.reference = reference;
    }

    @Override
    public boolean matches() {
        return editText.getText().toString().equals(reference.getText().toString());
    }
}
