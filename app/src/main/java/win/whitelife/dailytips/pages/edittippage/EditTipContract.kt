package win.whitelife.dailytips.pages.createtippage

import win.whitelife.dailytips.base.BasePresent
import win.whitelife.dailytips.base.BaseView
import win.whitelife.dailytips.bean.Tip

/**
 * Created by wuzefeng on 2017/7/4.
 */
open class EditTipContract{

    interface View:BaseView<Present>{
        fun getTipCallBack(tip: Tip?)
    }
    interface Present:BasePresent{
        fun saveTip(title:String,type: Int,hintType: Int,hintTime: String?,hintOtherTimes: List<Long>?,tipContent: List<String>,tip: Tip)
        fun deleteTip(tip: Tip)
    }


}