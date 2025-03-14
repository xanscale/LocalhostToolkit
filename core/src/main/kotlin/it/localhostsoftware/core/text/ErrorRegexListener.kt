package it.localhostsoftware.core.text

import android.widget.EditText
import java.util.regex.Pattern

/**
 * @param pattern Use embedded or android.util.Patterns
 */
class ErrorRegexListener(errorMsg: String?, editText: EditText, private val pattern: Pattern) : AbstractErrorListener(errorMsg, editText) {
    companion object {
        val PATTERN_EMAIL: Pattern = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val PATTERN_EMPTY: Pattern = Pattern.compile("^\\s*$")
        val PATTERN_NOTEMPTY: Pattern = Pattern.compile("^.*\\S+.*$")
        val PATTERN_DATE_DDMMYYYY: Pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[/\\-](0?[1-9]|1[012])[/\\-]\\d{4}$")
        val PATTERN_SSN_IT: Pattern = Pattern.compile("^(?:(?:[B-DF-HJ-NP-TV-Z]|[AEIOU])[AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[1256LMRS][\\dLMNP-V])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$")
    }

    override fun matches() =
        pattern.matcher(editText.text.toString()).matches()
}