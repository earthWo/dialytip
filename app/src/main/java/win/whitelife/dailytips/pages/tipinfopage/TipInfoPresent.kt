package win.whitelife.dailytips.pages.createtippage

import android.app.Activity
import android.content.Intent
import android.view.View
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.pages.edittippage.EditTipActivity

/**
 * Created by wuzefeng on 2017/7/5.
 */
class TipInfoPresent(context: Activity,view:View,tipId:Long):TipInfoContract.Present{

    var context: Activity?=null
    var view: View?=null
    var tipModule: TipModule?=null
    var tid:Long?=null
    var tipView: TipInfoContract.View?=null

    init {
        this.context=context
        this.tid=tipId
        tipModule= TipModule()
        this.view=view
        this.tipView=context as TipInfoContract.View
    }


    override fun onStart() {
        tipView!!.getTipCallback(tipModule!!.getTip(context!!,tid!!))
    }

    override fun jumpToEditPage() {
        val intent= Intent(context, EditTipActivity::class.java)
        intent.putExtra("TIP",tid)
        this.context?.startActivityForResult(intent,0)
    }



}