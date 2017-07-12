package win.whitelife.dailytips.pages.tipinfopage

import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import win.whitelife.dailytips.R
import win.whitelife.dailytips.base.BaseActivity
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.databinding.ActivityTipBinding
import win.whitelife.dailytips.pages.createtippage.TipInfoContract
import win.whitelife.dailytips.pages.createtippage.TipInfoPresent
import win.whitelife.dailytips.util.TimeUtil

/**
 * Created by wuzefeng on 2017/7/11.
 */
class TipInfoActivity: BaseActivity(),View.OnClickListener,TipInfoContract.View{

    private var binding:ActivityTipBinding?=null

    private var present: TipInfoContract.Present?=null


    override fun findViews() {
        binding=DataBindingUtil.setContentView(this, R.layout.activity_tip)
        binding!!.click=this
        setFinishView(binding!!.layoutToolBar.ivToolBarBack)
    }

    override fun init() {
        present=TipInfoPresent(this,binding!!.root,intent.getLongExtra("TIP",0))
        present!!.onStart()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_tool_bar_edit->present!!.jumpToEditPage()
        }
    }

    override fun getTipCallback(tip: Tip?) {
        if(tip==null)return
        binding!!.tip=tip
        binding!!.timeUtil=TimeUtil

        binding!!.llTipContain.removeAllViews()

        for(content in tip!!.tipContent!!){

            val v=LayoutInflater.from(this).inflate(R.layout.item_tip_content,binding!!.llTipContain,false)
            v.findViewById<TextView>(R.id.tv_tip_content).text=content
            binding!!.llTipContain.addView(v)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        edit=true
        present!!.onStart()
    }

    private var edit=false


    override fun finish() {
        if(edit)setResult(100)
        super.finish()
    }




}