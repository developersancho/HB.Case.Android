package com.developersancho.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB

    abstract fun onViewReady(bundle: Bundle?)

    open fun onViewListener() {}

    open fun observeUi() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::binding.isInitialized.not()) {
            binding = getBinding()
            setContentView(binding.root)
        }
        observeUi()
        onViewReady(savedInstanceState)
        onViewListener()
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(block = block)
    }
}