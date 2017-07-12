package win.whitelife.dailytips.pages.mainpage

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import win.whitelife.dailytips.R
import win.whitelife.dailytips.base.BaseFragment
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.databinding.FragmentTipsBinding
import win.whitelife.dailytips.databinding.ItemTipBinding
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.pages.tipinfopage.TipInfoActivity
import win.whitelife.dailytips.util.TimeUtil

/**
 * Created by wuzefeng on 2017/7/3.
 */

class TipsFragment: BaseFragment(),View.OnClickListener{

    private var binding:FragmentTipsBinding?=null

    private var dataList=ArrayList<Tip>()

    private var tipModule:TipModule?=null


    override fun init() {
        tipModule= TipModule()
        dataList=tipModule!!.getAllTips(this.context)!!
        binding!!.rvTips.adapter=adapter
    }

    override fun initRootView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) {
        rootView=inflater!!.inflate(R.layout.fragment_tips,container,false)
        binding = FragmentTipsBinding.bind(rootView)
        binding!!.rvTips.layoutManager=LinearLayoutManager(this.context)

    }

    fun updateData(){
        dataList=tipModule!!.getAllTips(this.context)!!
        adapter.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        val intent= Intent(activity, TipInfoActivity::class.java)
        intent.putExtra("TIP",p0!!.tag as Long)
        this.activity?.startActivityForResult(intent,0)
    }



    inner class TipHolder(view: View): RecyclerView.ViewHolder(view){

        var itemTipBinding: ItemTipBinding?=null

        init {
            itemTipBinding=ItemTipBinding.bind(view)
            itemTipBinding!!.timeUtil=TimeUtil
            itemTipBinding!!.click=this@TipsFragment
        }
    }


    private val adapter = object : RecyclerView.Adapter<TipHolder>() {

        override fun onBindViewHolder(holder: TipHolder?, position: Int) {
            holder!!.itemTipBinding!!.tip=dataList[position]
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipHolder? {
            return TipHolder(LayoutInflater.from(context).inflate(R.layout.item_tip,parent,false))
        }


        override fun getItemCount(): Int {
            return dataList.size
        }
    }

}