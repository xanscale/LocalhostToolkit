package localhost.toolkit.text;

import android.widget.EditText;

import java.util.Set;

public class ErrorSetListener extends AbstractErrorListener {
    private Set<String> stringSet;

    public ErrorSetListener(Set<String> stringSet, String errorMsg, EditText... editTexts) {
        super(errorMsg, editTexts);
        this.stringSet = stringSet;
    }

    @Override
    public boolean matches() {
        return stringSet.contains(editTexts[0].getText().toString());
    }
}
