package win.whitelife.dailytips.pages.createtippage

import win.whitelife.dailytips.base.BasePresent
import win.whitelife.dailytips.base.BaseView
import win.whitelife.dailytips.bean.Tip

/**
 * Created by wuzefeng on 2017/7/4.
 */
open class TipInfoContract{

    interface View:BaseView<Present>{
        fun getTipCallback(tip: Tip?)

    }
    interface Present:BasePresent{
        fun jumpToEditPage()
    }


}