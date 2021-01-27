package localhost.toolkit.text;

import android.widget.EditText;

import java.util.Set;

public class ErrorSetListener extends AbstractErrorListener {
    private final Set<String> stringSet;

    public ErrorSetListener(String errorMsg, EditText editText, Set<String> stringSet) {
        super(errorMsg, editText);
        this.stringSet = stringSet;
    }

    @Override
    public boolean matches() {
        return stringSet.contains(editText.getText().toString());
    }
}
