package ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.views.changecolor

import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.model.colors.NamedColor

/**
 * Represents list item for the color; it may be selected or not
 */
data class NamedColorListItem(
    val namedColor: NamedColor,
    val selected: Boolean
)