package win.whitelife.dailytips.pages.createtippage

import win.whitelife.dailytips.base.BasePresent
import win.whitelife.dailytips.base.BaseView

/**
 * Created by wuzefeng on 2017/7/4.
 */
open class CreateTipContract{

    interface View:BaseView<Present>
    interface Present:BasePresent{
        fun createNewTip(title:String,type: Int,hintType: Int,hintTime: String?,hintOtherTimes: List<Long>?,tipContent: List<String>)
    }


}