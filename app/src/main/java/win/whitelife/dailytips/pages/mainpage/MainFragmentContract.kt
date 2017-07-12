package win.whitelife.dailytips.pages.createtippage

import win.whitelife.dailytips.base.BasePresent
import win.whitelife.dailytips.base.BaseView
import win.whitelife.dailytips.bean.Tip

/**
 * Created by wuzefeng on 2017/7/4.
 */
open class MainFragmentContract{

    interface View:BaseView<Present>{

    }
    interface Present:BasePresent{

        fun jumpToCreateTipPage()

        fun jumpToTipInfoPage(tip: Tip)
    }


}