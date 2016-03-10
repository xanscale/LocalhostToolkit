package localhost.toolkit.text;

import android.support.design.widget.TextInputLayout;
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
			if (editText1.getParent() != null && editText1.getParent() instanceof TextInputLayout)
				((TextInputLayout) editText1.getParent()).setError(errorMsg);
			else
				editText1.setError(errorMsg);
			if (editText2.getParent() != null && editText2.getParent() instanceof TextInputLayout)
				((TextInputLayout) editText2.getParent()).setError(errorMsg);
			else
				editText2.setError(errorMsg);
			return false;
		} else
			return true;
	}
}
