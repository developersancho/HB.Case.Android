package com.developersancho.navigation

enum class AnimationType {
    NO_ANIM,
    DEFAULT,
    ENTER_FROM_LEFT,
    ENTER_FROM_LEFT_WITH_SCALE,
    ENTER_FROM_RIGHT,
    ENTER_FROM_RIGHT_WITH_SCALE,
    ENTER_FROM_BOTTOM;

    companion object {
        fun getAnimation(type: AnimationType): List<Int> {
            when (type) {
                DEFAULT -> return listOf(
                    R.anim.fragnav_slide_in_right,
                    R.anim.fragnav_slide_out_left,
                    R.anim.fragnav_slide_in_left,
                    R.anim.fragnav_slide_out_right
                )
                ENTER_FROM_LEFT -> return listOf(
                    R.anim.fragnav_anim_in_from_pop,
                    R.anim.fragnav_anim_out_from_pop,
                    R.anim.fragnav_anim_in,
                    R.anim.fragnav_anim_out
                )
                ENTER_FROM_LEFT_WITH_SCALE -> return listOf(
                    R.anim.fragnav_anim_scale_in_from_pop,
                    R.anim.fragnav_anim_scale_out_from_pop,
                    R.anim.fragnav_anim_scale_in,
                    R.anim.fragnav_anim_scale_out
                )
                ENTER_FROM_RIGHT -> return listOf(
                    R.anim.fragnav_anim_in,
                    R.anim.fragnav_anim_out,
                    R.anim.fragnav_anim_in_from_pop,
                    R.anim.fragnav_anim_out_from_pop
                )
                ENTER_FROM_RIGHT_WITH_SCALE -> return listOf(
                    R.anim.fragnav_anim_scale_in,
                    R.anim.fragnav_anim_scale_out,
                    R.anim.fragnav_anim_scale_in_from_pop,
                    R.anim.fragnav_anim_scale_out_from_pop
                )
                ENTER_FROM_BOTTOM -> return listOf(
                    R.anim.fragnav_anim_vertical_in_long,
                    R.anim.fragnav_anim_vertical_out_long,
                    R.anim.fragnav_anim_vertical_in_from_pop_long,
                    R.anim.fragnav_anim_vertical_out_from_pop_long
                )
                NO_ANIM -> return listOf()
            }
        }
    }
}
