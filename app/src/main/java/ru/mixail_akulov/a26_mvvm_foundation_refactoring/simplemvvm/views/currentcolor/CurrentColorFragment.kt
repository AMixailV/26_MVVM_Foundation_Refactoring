package ru.mixail_akulov.a26_mvvm_foundation_refactoring.simplemvvm.views.currentcolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.databinding.FragmentCurrentColorBinding
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views.BaseFragment
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views.BaseScreen
import ru.mixail_akulov.a26_mvvm_foundation_refactoring.foundation.views.screenViewModel

class CurrentColorFragment : BaseFragment() {

    // no arguments for this screen
    class Screen : BaseScreen

    override val viewModel by screenViewModel<CurrentColorViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentCurrentColorBinding.inflate(inflater, container, false)

        viewModel.currentColor.observe(viewLifecycleOwner) {
            binding.colorView.setBackgroundColor(it.value)
        }

        binding.changeColorButton.setOnClickListener {
            viewModel.changeColor()
        }

        return binding.root
    }


}