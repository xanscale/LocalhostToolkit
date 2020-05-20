package localhost.toolkit.text;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class AbstractErrorListener implements TextWatcher {
    abstract boolean matches(boolean hidden);

    @Override
    public void afterTextChanged(Editable s) {
        matches(true);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}