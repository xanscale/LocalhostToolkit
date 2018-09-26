package localhost.toolkit.text;

import com.google.android.material.textfield.TextInputLayout;
import android.widget.EditText;

public class ErrorEqualsListener implements ErrorListenerInterface {
	private EditText editText1, editText2;
	private String errorMsg;

	public ErrorEqualsListener(EditText editText1, EditText editText2, String errorMsg) {
		this.editText1 = editText1;
		this.editText2 = editText2;
		this.errorMsg = errorMsg;
		new ClearErrorTextWatcher(editText1, editText2);
	}

	@Override
	public boolean matches() {
		if (!editText1.getText().toString().equals(editText2.getText().toString())) {
			try {
				((TextInputLayout) editText1.getParent().getParent()).setError(errorMsg);
			} catch (Exception e) {
				editText1.setError(errorMsg);
			}
			try {
				((TextInputLayout) editText2.getParent().getParent()).setError(errorMsg);
			} catch (Exception e) {
				editText2.setError(errorMsg);
			}
			return false;
		} else
			return true;
	}
}
