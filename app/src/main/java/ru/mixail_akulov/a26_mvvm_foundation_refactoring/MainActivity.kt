package ru.mixail_akulov.a26_mvvm_foundation_refactoring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.ActivityScopeViewModel
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.navigator.IntermediateNavigator
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.navigator.StackFragmentNavigator
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.uiactions.AndroidUiActions
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.utils.viewModelCreator
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views.FragmentsHolder
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.views.currentcolor.CurrentColorFragment

/**
 * Это приложение представляет собой приложение с одним действием.
 * MainActivity — это контейнер для всех экранов.
 */
class MainActivity : AppCompatActivity(), FragmentsHolder {

    private lateinit var navigator: StackFragmentNavigator

    private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            uiActions = AndroidUiActions(applicationContext),
            navigator = IntermediateNavigator()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = StackFragmentNavigator(
            activity = this,
            containerId = R.id.fragmentContainer,
            defaultTitle = getString(R.string.app_name),
            animations = StackFragmentNavigator.Animations(
                enterAnim = R.anim.enter,
                exitAnim = R.anim.exit,
                popEnterAnim = R.anim.pop_enter,
                popExitAnim = R.anim.pop_exit
            ),
            initialScreenCreator = { CurrentColorFragment.Screen() }
        )
        navigator.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        navigator.onDestroy()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        // выполнять действия навигации только тогда, когда активность активна
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        // отложить навигационные действия, если активность не активна
        activityViewModel.navigator.setTarget(null)
    }

    override fun notifyScreenUpdates() {
        navigator.notifyScreenUpdates()
    }

    override fun getActivityScopeViewModel(): ActivityScopeViewModel {
        return activityViewModel
    }
}