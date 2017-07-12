package win.whitelife.dailytips.pages.createtippage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.service.DaemonService
import win.whitelife.dailytips.util.SnakeUtil

/**
 * Created by wuzefeng on 2017/7/5.
 */
class CreateTipPresent(context: Activity,view:View):CreateTipContract.Present{

    var context: Activity?=null
    var view: View?=null
    var tipModule: TipModule?=null

    init {
        this.context=context
        tipModule= TipModule()
        this.view=view
    }

    override fun createNewTip(title: String, type: Int, hintType: Int, hintTime: String?, hintOtherTimes: List<Long>?, tipContent: List<String>) {

        if(TextUtils.isEmpty(title)){
            SnakeUtil.show(view!!,"标题不能为空")
            return
        }

        if(hintType==-1){
            SnakeUtil.show(view!!,"提醒类型不能为空")
            return
        }

        if((type==6&&(hintOtherTimes==null||hintOtherTimes!!.isEmpty()))||hintTime==null){
            SnakeUtil.show(view!!,"时间不能为空")
            return
        }


        if(tipContent.isEmpty()){
            SnakeUtil.show(view!!,"提醒内容不能为空")
            return
        }



        val tip= tipModule!!.createTip(title,type!!,hintTime,hintType!!,hintOtherTimes,tipContent)
        tipModule!!.saveTip(context!!,tip)

        context!!.startService(Intent(context,DaemonService::class.java))
        context!!.setResult(100)
        context!!.finish()
    }


    override fun onStart() {


    }

}