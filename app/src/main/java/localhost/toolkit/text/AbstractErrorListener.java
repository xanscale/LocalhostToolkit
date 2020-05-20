package localhost.toolkit.text;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public abstract class AbstractErrorListener implements TextWatcher, View.OnFocusChangeListener {
    abstract boolean matches(boolean hidden);

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus)
            matches(false);
    }

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