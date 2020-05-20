package localhost.toolkit.text;

import java.util.ArrayList;

public class ErrorListenerList extends ArrayList<AbstractErrorListener> {
	public boolean matches(boolean hidden) {
		boolean matches = true;
		for (AbstractErrorListener editTextErrorListener : this)
			matches = editTextErrorListener.matches(hidden) && matches;
		return matches;
	}
}
