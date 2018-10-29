package localhost.toolkit.text;

public class ErrorDisjunctionListener implements ErrorListenerInterface {
	private ErrorListenerInterface a, b;

	public ErrorDisjunctionListener(ErrorListenerInterface a, ErrorListenerInterface b) {
		this.a = a;
		this.b = b;
	}

	@Override public boolean matches() {
		return a.matches() || b.matches();
	}

	@Override public String getValue() {
		return a.matches() ? a.getValue() : b.matches() ? b.getValue() : null;
	}
}
