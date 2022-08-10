package com.developersancho.framework.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setItemDecoration(left: Int, top: Int, right: Int, bottom: Int) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = context.dp2px(left)
            outRect.top = context.dp2px(top)
            outRect.right = context.dp2px(right)
            outRect.bottom = context.dp2px(bottom)
        }
    })
}