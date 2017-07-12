package win.whitelife.dailytips.pages.mainpage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import win.whitelife.dailytips.R
import win.whitelife.dailytips.base.BaseFragment

/**
 * Created by wuzefeng on 2017/7/3.
 */

class SettingFragment: BaseFragment(){


    override fun initRootView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) {
        rootView=inflater!!.inflate(R.layout.fragment_setting
                ,container,false)
    }


    override fun init() {
    }


}