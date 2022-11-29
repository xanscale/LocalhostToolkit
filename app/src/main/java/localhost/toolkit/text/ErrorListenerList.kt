package localhost.toolkit.text

class ErrorListenerList : ArrayList<AbstractErrorListener>() {
    fun matches(showError: Boolean): Boolean {
        var matches = true
        forEach { matches = it.matches(showError) && matches }
        return matches
    }

    fun matches(): Boolean {
        var matches = true
        forEach { matches = it.matches() && matches }
        return matches
    }
}