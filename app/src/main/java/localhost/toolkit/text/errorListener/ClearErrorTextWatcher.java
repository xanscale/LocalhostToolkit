package localhost.toolkit.text.errorListener;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public final class ClearErrorTextWatcher implements TextWatcher {
	private EditText[] editTexts;

	public ClearErrorTextWatcher(EditText... editTexts) {
		this.editTexts = editTexts;
		for (EditText editText : editTexts)
			editText.addTextChangedListener(this);
	}

	public void afterTextChanged(Editable s) {
		for (EditText editText : editTexts) {
			if (editText.getParent() != null && editText.getParent() instanceof TextInputLayout)
				((TextInputLayout) editText.getParent()).setError(null);
			else
				editText.setError(null);
		}
	}

	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
}