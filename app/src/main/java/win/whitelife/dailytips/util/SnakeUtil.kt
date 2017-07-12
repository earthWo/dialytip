package win.whitelife.dailytips.util

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by wuzefeng on 2017/7/10.
 */
class SnakeUtil{



    companion object {

        fun show(view: View, text:String):Unit{
            Snackbar.make(view,text,500).show()
        }

    }
}