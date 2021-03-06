package com.farahaniconsulting.shopo.ui.util.text

import android.content.Context
import androidx.annotation.StringRes

class ResText(@StringRes val stringRes: Int) :
    Text {
    override fun getText(context: Context): CharSequence = context.getText(stringRes)

    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        else -> stringRes == (other as ResText).stringRes
    }

    override fun hashCode() = stringRes
}