package com.developersancho.framework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null

    val binding: VB
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")

    protected fun requireBinding(): VB = requireNotNull(_binding)

    protected var viewId: Int = -1

    abstract fun onViewReady(bundle: Bundle?)

    open fun onViewListener() {}

    open fun observeUi() {}

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewId = binding.root.id
        observeUi()
        onViewReady(savedInstanceState)
        onViewListener()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun checkArgument(argsKey: String): Boolean {
        return requireArguments().containsKey(argsKey)
    }

    protected fun requireCompatActivity(): AppCompatActivity {
        requireActivity()
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            return activity
        } else {
            throw TypeCastException("Main activity should extend from 'AppCompatActivity'")
        }
    }
}