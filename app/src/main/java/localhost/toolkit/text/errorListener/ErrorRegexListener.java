package localhost.toolkit.text.errorListener;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ErrorRegexListener implements OnFocusChangeListener, ErrorListenerInterface {
	private EditText editText;
	private String regex, errorMsg;

	public ErrorRegexListener(EditText editText, String regex, String errorMsg) {
		this.editText = editText;
		this.editText.setOnFocusChangeListener(this);
		this.regex = regex;
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
		if (!Pattern.matches(regex, editText.getText().toString().trim())) {
			if (editText.getParent() != null && editText.getParent() instanceof TextInputLayout)
				((TextInputLayout) editText.getParent()).setError(errorMsg);
			else
				editText.setError(errorMsg);
			return false;
		} else
			return true;
	}
}
