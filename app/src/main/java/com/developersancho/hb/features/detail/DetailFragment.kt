package com.developersancho.hb.features.detail

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
    }
}