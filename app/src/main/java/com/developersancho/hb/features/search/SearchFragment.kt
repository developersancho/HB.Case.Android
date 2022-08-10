package com.developersancho.hb.features.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.developersancho.framework.base.mvi.BaseMviFragment
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extensions.cast
import com.developersancho.hb.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseMviFragment<FragmentSearchBinding, SearchViewState, SearchViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    override val viewModel: SearchViewModel by viewModels()

    override fun onViewReady(bundle: Bundle?) {
        initAdapter()
    }

    private fun initAdapter() {

    }

    override fun renderViewState(viewState: BaseViewState<*>) {
        when (viewState) {
            is BaseViewState.Empty -> Unit
            is BaseViewState.Error -> Unit
            is BaseViewState.Loading -> Unit
            is BaseViewState.Success -> {
                val data = viewState.cast<BaseViewState.Success<SearchViewState>>().data

            }
        }
    }

    override fun onViewListener() {
        super.onViewListener()
    }
}