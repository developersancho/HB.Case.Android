package com.developersancho.framework.base

import androidx.viewbinding.ViewBinding
import com.developersancho.framework.extensions.findClass
import com.developersancho.framework.extensions.getBinding

internal fun <V : ViewBinding> BaseActivity<V>.getBinding(): V {
    return findClass().getBinding(layoutInflater)
}