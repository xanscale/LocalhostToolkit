package localhost.toolkit.text;

import com.google.android.material.textfield.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ClearErrorTextWatcher implements TextWatcher {
	private EditText[] editTexts;

	public ClearErrorTextWatcher(EditText... editTexts) {
		this.editTexts = editTexts;
		for (EditText editText : editTexts)
			editText.addTextChangedListener(this);
	}

	public void afterTextChanged(Editable s) {
		for (EditText editText : editTexts)
			try {
				((TextInputLayout) editText.getParent().getParent()).setError(null);
			} catch (Exception e) {
				editText.setError(null);
			}
	}

	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
}