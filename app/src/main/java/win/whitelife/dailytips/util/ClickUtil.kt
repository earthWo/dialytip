package win.whitelife.dailytips.util

import android.app.Activity
import android.view.View

/**
 * Created by wuzefeng on 2017/7/7.
 */
class ClickUtil{

    companion object {

        var activityFinishClick= View.OnClickListener { p0 ->
                (p0.context as Activity).finish()
        }



    }

}