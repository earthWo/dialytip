package win.whitelife.dailytips.pages.mainpage

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import win.whitelife.dailytips.R
import win.whitelife.dailytips.base.BaseFragment
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.databinding.FragmentMainBinding
import win.whitelife.dailytips.databinding.ItemTodayTipBinding
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.pages.createtippage.MainFragmentContract
import win.whitelife.dailytips.util.TimeUtil
import java.sql.Time

/**
 * Created by wuzefeng on 2017/7/3.
 */

class MainFragment: BaseFragment(),View.OnClickListener{

    private var binding: FragmentMainBinding?=null

    private var dataList=ArrayList<Tip>()

    private var tipModule:TipModule?=null

    override fun initRootView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Unit {
        rootView=inflater!!.inflate(R.layout.fragment_main,container,false)
        binding =FragmentMainBinding.bind(rootView)
        binding!!.click=this
     }

    override fun init() {
        present=MainPresent(this.activity)
        tipModule= TipModule()
        dataList=tipModule!!.getAllTips(this.context)!!
        binding!!.vpTip.adapter=adapter
    }


    private var present: MainFragmentContract.Present?=null;

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.fab_create_tip->present!!.jumpToCreateTipPage()
            R.id.cv_tip->present!!.jumpToTipInfoPage(p0.tag as Tip)
        }
    }

    fun updateData(){
        dataList=tipModule!!.getAllTips(this.context)!!
        adapter.notifyDataSetChanged()
    }



    val adapter =object :PagerAdapter(){

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return `object`==view
        }

        override fun getCount(): Int {
           return dataList.size
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {

        }

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            val itemTodayBinding:ItemTodayTipBinding=ItemTodayTipBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_today_tip,container,false))
            itemTodayBinding.timeUtil=TimeUtil
            itemTodayBinding.tip=dataList[position]
            itemTodayBinding.click=this@MainFragment
            container!!.addView(itemTodayBinding.root)
            return itemTodayBinding.root
        }
    }

}