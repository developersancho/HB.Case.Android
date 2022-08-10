package com.developersancho.framework.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.developersancho.framework.extensions.findClass
import com.developersancho.framework.extensions.getBinding

internal fun <V : ViewBinding> BaseActivity<V>.getBinding(): V {
    return findClass().getBinding(layoutInflater)
}

internal fun <V : ViewBinding> BaseFragment<V>.getBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
): V {
    return findClass().getBinding(inflater, container)
}