package com.developersancho.hb.features.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.viewModels
import coil.load
import coil.size.Scale
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.base.mvi.BaseMviFragment
import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.extensions.cast
import com.developersancho.framework.extensions.toReleaseDate
import com.developersancho.framework.extensions.visible
import com.developersancho.framework.extensions.withArgs
import com.developersancho.hb.R
import com.developersancho.hb.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseMviFragment<FragmentDetailBinding, DetailViewState, DetailViewModel>() {
    companion object {
        const val ARG_SEARCH_ITEM_DTO = "arg_search_item_dto"

        @JvmStatic
        fun newInstance(searchItemDto: SearchItemDto) = DetailFragment().withArgs {
            putParcelable(ARG_SEARCH_ITEM_DTO, searchItemDto)
        }
    }

    override val viewModel: DetailViewModel by viewModels()

    override fun onViewReady(bundle: Bundle?) {
        viewModel.onTriggerEvent(DetailEvent.LoadDetail)
    }

    override fun renderViewState(viewState: BaseViewState<*>) {
        when (viewState) {
            is BaseViewState.Empty -> Unit
            is BaseViewState.Error -> Unit
            is BaseViewState.Loading -> Unit
            is BaseViewState.Success -> {
                val data = viewState.cast<BaseViewState.Success<DetailViewState>>().data
                val dto = data.dto
                setDetailInformation(dto)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailInformation(dto: SearchItemDto?) {
        binding.ivArtNetwork.load(dto?.artworkUrl100) {
            scale(Scale.FILL)
            error(R.drawable.ic_error_image)
        }
        dto?.trackName?.let {
            binding.clTrackName.visible()
            binding.tvTrackName.text = it
        }
        dto?.collectionName?.let {
            binding.clCollectionName.visible()
            binding.tvCollectionName.text = it
        }
        dto?.artistName?.let {
            binding.clArtistName.visible()
            binding.tvArtistName.text = it
        }
        dto?.description?.let {
            binding.clDescription.visible()
            binding.tvDescription.text = it
        }
        dto?.collectionPrice?.let {
            binding.clPrice.visible()
            binding.tvPrice.text = "${it}-${dto.currency}"
        }
        dto?.releaseDate?.let {
            binding.clReleaseDate.visible()
            binding.tvReleaseDate.text = it.toReleaseDate()
        }
    }

    override fun onViewListener() {
        super.onViewListener()
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}