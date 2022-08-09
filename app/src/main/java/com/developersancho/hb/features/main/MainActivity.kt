package com.developersancho.hb.features.main

import android.os.Bundle
import com.developersancho.framework.base.BaseActivity
import com.developersancho.framework.extensions.showSnackBar
import com.developersancho.hb.R
import com.developersancho.hb.databinding.ActivityMainBinding
import com.developersancho.hb.features.search.SearchFragment
import com.developersancho.navigation.navigateFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var backPressedOnce = false

    override fun onViewReady(bundle: Bundle?) {
        navigateFragment(SearchFragment.newInstance(), clearBackStack = true)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            if (backPressedOnce) {
                finish()
                return
            }
            backPressedOnce = true
            showSnackBar(binding.rootView, getString(R.string.app_exit_label))

            safeLaunch {
                delay(2000)
                backPressedOnce = false
            }
        }
    }
}