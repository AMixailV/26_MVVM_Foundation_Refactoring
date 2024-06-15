package ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.utils

typealias ResourceAction<T> = (T) -> Unit

/**
 * Actions queue, where actions are executed only if resource exists. If it doesn't then
 * action is added to queue and waits until resource becomes available.
 */
class ResourceActions<T> {

    var resource: T? = null
        set(newValue) {
            field = newValue
            if (newValue != null) {
                actions.forEach { it(newValue) }
                actions.clear()
            }
        }

    private val actions = mutableListOf<ResourceAction<T>>()

    /**
     * Вызывайте действие только тогда, когда [resource] существует (не нуль).
     * В противном случае действие откладывается до тех пор, пока [resource] не будет присвоено какое-либо ненулевое значение.
     */
    operator fun invoke(action: ResourceAction<T>) {
        val resource = this.resource
        if (resource == null) {
            actions += action
        } else {
            action(resource)
        }
    }

    fun clear() {
        actions.clear()
    }
}