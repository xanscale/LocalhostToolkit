package localhost.toolkit.text;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ErrorRegexListener extends AbstractErrorListener {
    public static final Pattern PATTERN_EMAIL = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public static final Pattern PATTERN_EMPTY = Pattern.compile("^\\s*$");
    public static final Pattern PATTERN_NOTEMPTY = Pattern.compile("(\\S+\\s*)+");
    public static final Pattern PATTERN_DATE_DDMMYYYY = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[/\\-](0?[1-9]|1[012])[/\\-]\\d{4}$");
    public static final Pattern PATTERN_SSN_IT = Pattern.compile("^(?:(?:[B-DF-HJ-NP-TV-Z]|[AEIOU])[AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[1256LMRS][\\dLMNP-V])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");
    private EditText editText;
    private Pattern pattern;
    private String errorMsg;

    /**
     * @param pattern Use embedded or android.util.Patterns
     */
    public ErrorRegexListener(EditText editText, String errorMsg, Pattern pattern) {
        this.editText = editText;
        this.pattern = pattern;
        this.errorMsg = errorMsg;
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(this);
    }

    @Override
    public boolean matches(boolean hidden) {
        boolean matches = pattern.matcher(editText.getText().toString()).matches();
        try {
            ((TextInputLayout) editText.getParent().getParent()).setError(matches || hidden ? null : errorMsg);
        } catch (Exception e) {
            editText.setError(matches ? null : errorMsg);
        }
        return matches;
    }
}
