package localhost.toolkit.text;

import android.widget.EditText;

public class ErrorEqualsListener extends AbstractErrorListener {
    public ErrorEqualsListener(String errorMsg, EditText... editTexts) {
        super(errorMsg, editTexts);
    }

    @Override
    public boolean matches() {
        return editTexts[0].getText().toString().equals(editTexts[1].getText().toString());
    }
}
