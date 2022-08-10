package com.developersancho.hb.features.detail

import androidx.fragment.app.Fragment
import com.developersancho.data.model.dto.SearchItemDto
import com.developersancho.framework.extensions.withArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    companion object {
        const val ARG_SEARCH_ITEM_DTO = "arg_search_item_dto"

        @JvmStatic
        fun newInstance(searchItemDto: SearchItemDto) = DetailFragment().withArgs {
            putParcelable(ARG_SEARCH_ITEM_DTO, searchItemDto)
        }
    }
}