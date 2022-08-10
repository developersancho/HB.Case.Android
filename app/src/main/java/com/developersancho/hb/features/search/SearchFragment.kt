package com.developersancho.hb.features.search

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.developersancho.framework.base.mvi.BaseMviFragment
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extensions.*
import com.developersancho.hb.app.widgets.onSearchQueryRightIconChanged
import com.developersancho.hb.databinding.FragmentSearchBinding
import com.developersancho.hb.features.detail.DetailFragment
import com.developersancho.navigation.AnimationType
import com.developersancho.navigation.navigateFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseMviFragment<FragmentSearchBinding, SearchViewState, SearchViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    private val adapter = SearchAdapter()

    override val viewModel: SearchViewModel by viewModels()

    override fun onViewReady(bundle: Bundle?) {
        initAdapter()
        binding.etSearch.onSearchQueryRightIconChanged()
        binding.etSearch.hideKeyboard()
    }

    private fun initAdapter() {
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.setItemDecoration(left = 8, top = 12, right = 8, bottom = 0)
        binding.rvSearch.adapter = adapter
        adapter.onClickItem = {
            navigateFragment(DetailFragment.newInstance(it), animation = AnimationType.DEFAULT)
        }
    }

    override fun renderViewState(viewState: BaseViewState<*>) {
        when (viewState) {
            is BaseViewState.Empty -> {
                // todo: can be develop empty view
                binding.pbLoading.gone()
            }
            is BaseViewState.Error -> {
                // todo: can be develop error view
                binding.pbLoading.gone()
            }
            is BaseViewState.Loading -> {
                // todo: can be develop loading view
                binding.pbLoading.visible()
            }
            is BaseViewState.Success -> {
                binding.pbLoading.gone()
                val data = viewState.cast<BaseViewState.Success<SearchViewState>>().data
                adapter.submitData(lifecycle, data.pagedData)
            }
        }
    }

    override fun onViewListener() {
        super.onViewListener()
        binding.etSearch.textChanges()
            .debounce(300)
            .onEach { text ->
                takeIf { viewModel.searchKeyword != text.toString() }?.let {
                    viewModel.onTriggerEvent(SearchEvent.SearchByText(keyword = text.toString()))
                }
            }
            .launchIn(lifecycleScope)

        binding.rbMovie.setOnClickListener {
            viewModel.onTriggerEvent(
                SearchEvent.SearchByFilterType(filterType = SearchFilterType.MOVIE)
            )
        }

        binding.rbMusic.setOnClickListener {
            viewModel.onTriggerEvent(
                SearchEvent.SearchByFilterType(filterType = SearchFilterType.MUSIC)
            )
        }

        binding.rbEbook.setOnClickListener {
            viewModel.onTriggerEvent(
                SearchEvent.SearchByFilterType(filterType = SearchFilterType.EBOOK)
            )
        }

        binding.rbPodcast.setOnClickListener {
            viewModel.onTriggerEvent(
                SearchEvent.SearchByFilterType(filterType = SearchFilterType.PODCAST)
            )
        }
    }
}