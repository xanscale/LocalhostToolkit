package localhost.toolkit.text;

import java.util.ArrayList;

public class ErrorListenerList extends ArrayList<ErrorListenerInterface> {
	public boolean matches() {
		boolean matches = true;
		for (ErrorListenerInterface editTextErrorListener : this)
			matches = editTextErrorListener.matches() && matches;
		return matches;
	}
}
