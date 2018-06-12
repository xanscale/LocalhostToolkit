package localhost.toolkit.text;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ErrorRegexListener implements OnFocusChangeListener, ErrorListenerInterface {
	public static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String REGEX_URL = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$";
	public static final String REGEX_NOTEMPTY = ".+";
	public static final String REGEX_DATE_DDMMYYYY = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
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
			try {
				((TextInputLayout) editText.getParent().getParent()).setError(errorMsg);
			} catch (Exception e) {
				editText.setError(errorMsg);
			}
			return false;
		} else
			return true;
	}
}
