package localhost.toolkit.text;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJsonListener implements View.OnFocusChangeListener, ErrorListenerInterface {
	private EditText editText;
	private String errorMsg;

	public ErrorJsonListener(EditText editText, String errorMsg) {
		this.editText = editText;
		this.editText.setOnFocusChangeListener(this);
		this.errorMsg = errorMsg;
		new ClearErrorTextWatcher(editText);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (!hasFocus)
			matches();
	}

	@Override
	public boolean matches() {
		if (!isValid(editText.getText().toString())) {
			try {
				((TextInputLayout) editText.getParent().getParent()).setError(errorMsg);
			} catch (Exception e) {
				editText.setError(errorMsg);
			}
			return false;
		} else
			return true;
	}

	private boolean isValid(String json) {
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
