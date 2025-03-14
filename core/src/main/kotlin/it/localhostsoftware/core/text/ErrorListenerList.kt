package it.localhostsoftware.core.text

class ErrorListenerList : ArrayList<AbstractErrorListener>() {
    fun matches(showError: Boolean) = all { it.matches(showError) }
    fun matches() = all { it.matches() }
}