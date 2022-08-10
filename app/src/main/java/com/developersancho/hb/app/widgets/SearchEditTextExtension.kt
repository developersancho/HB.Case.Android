package com.developersancho.hb.app.widgets

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import com.developersancho.framework.extensions.orZero
import com.developersancho.hb.R

private const val DRAWABLE_RIGHT = 2

fun AppCompatEditText.onSearchQueryRightIconChanged(isLight: Boolean = false) {
    addTextChangedListener {
        val searchBar = if (isLight) {
            R.drawable.ic_search_bar_small_gray
        } else {
            R.drawable.ic_search_bar_small
        }

        val closeCircle = if (isLight) {
            R.drawable.ic_close_circle_gray
        } else {
            R.drawable.ic_close_circle_red_tint
        }

        if (it.toString().isEmpty()) {
            setCompoundDrawablesWithIntrinsicBounds(searchBar, 0, 0, 0)
        } else {
            setCompoundDrawablesWithIntrinsicBounds(
                searchBar,
                0,
                closeCircle,
                0
            )
        }
    }

    setOnTouchListener(object : View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (event?.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (
                            right - compoundDrawables[DRAWABLE_RIGHT]?.bounds?.width()
                                .orZero()
                            ) &&
                    text?.isEmpty() == false
                ) {
                    this@onSearchQueryRightIconChanged.text?.clear()
                    return true
                }
            }
            return false
        }
    })
}