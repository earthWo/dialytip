package win.whitelife.dailytips.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by wuzefeng on 2017/7/4.
 */
abstract class BaseFragment: Fragment(){

    var rootView: View?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initRootView(inflater,container,savedInstanceState)
        init()
        return if(rootView==null) super.onCreateView(inflater, container, savedInstanceState) else rootView
    }

    abstract fun init()


    abstract fun initRootView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):Unit


}