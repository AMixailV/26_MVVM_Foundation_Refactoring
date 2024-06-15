package ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views

import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.ActivityScopeViewModel

/**
 * Implement this interface in the activity.
 */
interface FragmentsHolder {

    /**
     * Called when activity views should be re-drawn.
     */
    fun notifyScreenUpdates()

    /**
     * Get the current implementations of dependencies from activity VM scope.
     */
    fun getActivityScopeViewModel(): ActivityScopeViewModel

}