package localhost.toolkit.text;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJsonListener extends AbstractErrorListener {
    private EditText editText;
    private String errorMsg;

    public ErrorJsonListener(EditText editText, String errorMsg) {
        this.editText = editText;
        this.errorMsg = errorMsg;
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(this);
    }

    @Override
    public boolean matches(boolean hidden) {
        boolean matches = matches(editText.getText().toString());
        try {
            ((TextInputLayout) editText.getParent().getParent()).setError(matches || hidden ? null : errorMsg);
        } catch (Exception e) {
            editText.setError(matches ? null : errorMsg);
        }
        return matches;
    }

    private boolean matches(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
