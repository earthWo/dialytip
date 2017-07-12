package win.whitelife.dailytips.pages.createtippage

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.service.DaemonService
import win.whitelife.dailytips.util.SnakeUtil

/**
 * Created by wuzefeng on 2017/7/5.
 */
class EditTipPresent(context: Activity,view:View,tipId:Long):EditTipContract.Present{


    var context: Activity?=null
    var view: View?=null
    var tipModule: TipModule?=null
    var tid:Long?=null
    var tipView: EditTipContract.View?=null

    init {
        this.context=context
        this.tid=tipId
        tipModule= TipModule()
        this.view=view
        this.tipView=context as EditTipContract.View
    }

    override fun saveTip(title: String, type: Int, hintType: Int, hintTime: String?, hintOtherTimes: List<Long>?, tipContent: List<String>,tip: Tip) {

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

        val tip= tipModule!!.editTip(title,type!!,hintTime,hintType!!,hintOtherTimes,tipContent,tip)
        tipModule!!.saveTip(context!!,tip)
        context!!.startService(Intent(context, DaemonService::class.java))
        context!!.setResult(100)
        context!!.finish()
    }

    override fun onStart() {
        tipView!!.getTipCallBack(tipModule!!.getTip(context!!,tid!!))
    }

    override fun deleteTip(tip: Tip) {
        tipModule!!.deleteTip(context!!,tip)
        SnakeUtil.show(view!!,"删除成功！")
        context!!.setResult(100)
        context!!.finish()
    }

}