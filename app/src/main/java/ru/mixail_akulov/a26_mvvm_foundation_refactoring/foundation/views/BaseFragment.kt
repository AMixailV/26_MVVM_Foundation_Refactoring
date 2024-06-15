package ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views

import androidx.fragment.app.Fragment

/**
 * Base class for all fragments
 */
abstract class BaseFragment : Fragment() {

    /**
     * View-model that manages this fragment
     */
    abstract val viewModel: BaseViewModel

    /**
     * Вызовите этот метод, когда элементы управления действиями (например, панель инструментов)
     * должны быть повторно отображены.
     */
    fun notifyScreenUpdates() {
        (requireActivity() as FragmentsHolder).notifyScreenUpdates()
    }
}