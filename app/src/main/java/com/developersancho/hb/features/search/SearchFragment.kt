package com.developersancho.hb.features.search

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}