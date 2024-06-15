package ru.mixail_akulov.a26_mvvm_foundation_refactoring

import android.app.Application
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.BaseApplication
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.model.Repository
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.model.colors.InMemoryColorsRepository

/**
 * Здесь мы храним экземпляры классов слоя модели.
 */
class App : Application(), BaseApplication {

    /**
     * Размещайте здесь свои репозитории, сейчас у нас всего 1 репозиторий
     */
    override val repositories: List<Repository> = listOf<Repository>(
        InMemoryColorsRepository()
    )

}