package win.whitelife.dailytips.widget

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by wuzefeng on 2017/7/10.
 */
class InputUtil{

    companion object {

        fun hideInput(view: View,activity: Activity){
            val im=activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

        }
    }
}