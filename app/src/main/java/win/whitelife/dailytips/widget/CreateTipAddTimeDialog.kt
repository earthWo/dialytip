package win.whitelife.dailytips.widget

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import win.whitelife.dailytips.R

/**
 * Created by wuzefeng on 2017/7/10.
 */
class CreateTipAddTimeDialog(context: Context,view: TextView): Dialog(context){


    var contentView: CreateTipAddTimeView?=null
    init {
        this.setTitle("选择提示时间")
        this.setContentView(R.layout.dialog_create_tip_time)
        contentView=findViewById(R.id.cv_add_time)
        this.setOnDismissListener {
            view.text=contentView!!.getFirstTime()+"等"
        }
    }


    fun getHintOtherTime(): List<Long>{
       return contentView!!.getHintOtherTime()
    }

    fun setHintOtherTime(times: List<Long>){
        contentView!!.setHintOtherTime(times)
    }

}