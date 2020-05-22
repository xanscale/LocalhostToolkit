package localhost.toolkit.text;

import java.util.ArrayList;

public class ErrorListenerList extends ArrayList<AbstractErrorListener> {
    public boolean matches(boolean showError) {
        boolean matches = true;
        for (AbstractErrorListener editTextErrorListener : this)
            matches = editTextErrorListener.matches(showError) && matches;
        return matches;
    }

    public boolean matches() {
        boolean matches = true;
        for (AbstractErrorListener editTextErrorListener : this)
            matches = editTextErrorListener.matches() && matches;
        return matches;
    }
}
