package org.sopt.app3.planfit.core.ui.activity

import android.app.Activity
import android.view.View
import org.sopt.app3.planfit.core.ui.context.hideKeyboard

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}