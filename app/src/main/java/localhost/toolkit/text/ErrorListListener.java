package localhost.toolkit.text;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import java.util.Set;

public class ErrorListListener implements OnFocusChangeListener, ErrorListenerInterface {
	private EditText editText;
	private Set<String> stringSet;
	private String errorMsg;

	public ErrorListListener(EditText editText, Set<String> stringSet, String errorMsg) {
		this.editText = editText;
		this.editText.setOnFocusChangeListener(this);
		this.stringSet = stringSet;
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
		if (!stringSet.contains(editText.getText().toString().trim())) {
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
