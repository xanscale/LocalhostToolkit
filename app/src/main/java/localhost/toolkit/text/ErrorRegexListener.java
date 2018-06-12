package localhost.toolkit.text;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ErrorRegexListener implements OnFocusChangeListener, ErrorListenerInterface {
	public static final Pattern PATTERN_EMAIL = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	public static final Pattern PATTERN_NOTEMPTY = Pattern.compile(".+");
	public static final Pattern PATTERN_DATE_DDMMYYYY = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
	private EditText editText;
	private Pattern pattern;
	private String errorMsg;

	/**
	 * @param pattern Use embedded or android.util.Patterns
	 */
	public ErrorRegexListener(EditText editText, Pattern pattern, String errorMsg) {
		this.editText = editText;
		this.editText.setOnFocusChangeListener(this);
		this.pattern = pattern;
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
		if (!pattern.matcher(editText.getText().toString().trim()).matches()) {
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
