package win.whitelife.dailytips.pages.alarmclockpage

import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.View
import win.whitelife.dailytips.R
import win.whitelife.dailytips.base.BaseActivity
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.databinding.ActivityAlarmClockBinding
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.pages.tipinfopage.TipInfoActivity
import win.whitelife.dailytips.service.DaemonService

/**
 * Created by wuzefeng on 2017/7/12.
 */
class AlarmClockActivity: BaseActivity(),View.OnClickListener{

    private var binding: ActivityAlarmClockBinding?=null

    private var tip: Tip?=null

    override fun init() {
        startService(Intent(this,DaemonService::class.java))
        tip=TipModule().getTip(this,intent.getLongExtra("TIP",0))
    }


    override fun findViews() {
        binding=DataBindingUtil.setContentView(this, R.layout.activity_alarm_clock)
        binding!!.tip=tip
        binding!!.click=this
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_info->{
                val intent=Intent(this,TipInfoActivity::class.java)
                intent.putExtra("TIP",tip!!.id)
                startActivity(intent)
                finish()
            }
            R.id.tv_finish->finish()
        }
    }



}