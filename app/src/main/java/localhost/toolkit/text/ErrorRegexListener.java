package localhost.toolkit.text;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ErrorRegexListener implements OnFocusChangeListener, ErrorListenerInterface {
	public static final Pattern PATTERN_EMAIL = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	public static final Pattern PATTERN_NOTEMPTY = Pattern.compile(".+");
	public static final Pattern PATTERN_DATE_DDMMYYYY = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
	public static final Pattern PATTERN_SSN_IT = Pattern.compile("^(?:(?:[B-DF-HJ-NP-TV-Z]|[AEIOU])[AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[1256LMRS][\\dLMNP-V])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");
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
		if (!pattern.matcher(getValue()).matches()) {
			try {
				((TextInputLayout) editText.getParent().getParent()).setError(errorMsg);
			} catch (Exception e) {
				editText.setError(errorMsg);
			}
			return false;
		} else
			return true;
	}

	@Override public String getValue() {
		return editText.getText().toString().trim();
	}
}
