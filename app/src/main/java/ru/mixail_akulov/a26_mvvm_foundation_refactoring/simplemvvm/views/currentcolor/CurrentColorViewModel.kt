package ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.views.currentcolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.R
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.navigator.Navigator
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.uiactions.UiActions
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views.BaseViewModel
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.model.colors.ColorListener
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.model.colors.ColorsRepository
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.model.colors.NamedColor
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.views.changecolor.ChangeColorFragment

class CurrentColorViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val colorsRepository: ColorsRepository
) : BaseViewModel() {

    private val _currentColor = MutableLiveData<NamedColor>()
    val currentColor: LiveData<NamedColor> = _currentColor

    private val colorListener: ColorListener = {
        _currentColor.postValue(it)
    }

    // --- example of listening results via model layer

    init {
        colorsRepository.addListener(colorListener)
    }

    override fun onCleared() {
        super.onCleared()
        colorsRepository.removeListener(colorListener)
    }

    // --- example of listening results directly from the screen

    override fun onResult(result: Any) {
        super.onResult(result)
        if (result is NamedColor) {
            val message = uiActions.getString(R.string.changed_color, result.name)
            uiActions.toast(message)
        }
    }

    // ---

    fun changeColor() {
        val currentColor = currentColor.value ?: return
        val screen = ChangeColorFragment.Screen(currentColor.id)
        navigator.launch(screen)
    }

}