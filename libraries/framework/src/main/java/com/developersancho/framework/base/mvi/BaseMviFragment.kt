package com.developersancho.framework.base.mvi

import androidx.viewbinding.ViewBinding
import com.developersancho.framework.base.BaseFragment
import com.developersancho.framework.extensions.observeFlowStart

abstract class BaseMviFragment<VB : ViewBinding, STATE : BaseViewState<*>, VM : MviViewModel<STATE, *>> :
    BaseFragment<VB>() {

    abstract val viewModel: VM

    abstract fun renderViewState(viewState: BaseViewState<*>)

    override fun observeUi() {
        super.observeUi()
        observeFlowStart(viewModel.uiState, ::renderViewState)
    }
}