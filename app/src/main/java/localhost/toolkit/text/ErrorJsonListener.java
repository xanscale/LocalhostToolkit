package localhost.toolkit.text;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJsonListener extends AbstractErrorListener {
    public ErrorJsonListener(String errorMsg, EditText editText) {
        super(errorMsg, editText);
    }

    @Override
    public boolean matches() {
        return matches(editText.getText().toString());
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
