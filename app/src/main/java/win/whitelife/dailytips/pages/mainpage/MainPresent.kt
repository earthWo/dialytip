package win.whitelife.dailytips.pages.mainpage

import android.app.Activity
import android.content.Intent
import win.whitelife.dailytips.base.BasePresent
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.pages.createtippage.CreateTipActivity
import win.whitelife.dailytips.pages.createtippage.MainFragmentContract
import win.whitelife.dailytips.pages.edittippage.EditTipActivity
import win.whitelife.dailytips.pages.tipinfopage.TipInfoActivity

/**
 * Created by wuzefeng on 2017/7/5.
 */
class MainPresent: MainFragmentContract.Present{


    override fun onStart() {
    }

    private var activity: Activity?=null

    constructor(activity: Activity?) {
        this.activity = activity
    }


    override fun jumpToCreateTipPage() {
        val intent=Intent(activity,CreateTipActivity::class.java)
        this.activity?.startActivityForResult(intent,0)
    }


    override fun jumpToTipInfoPage(tip: Tip) {
        val intent=Intent(activity,TipInfoActivity::class.java)
        intent.putExtra("TIP",tip.id)
        this.activity?.startActivityForResult(intent,0)
    }

}