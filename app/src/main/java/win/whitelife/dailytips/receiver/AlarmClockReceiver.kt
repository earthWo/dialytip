package win.whitelife.dailytips.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.pages.alarmclockpage.AlarmClockActivity

/**
 * Created by wuzefeng on 2017/7/12.
 */
class AlarmClockReceiver: BroadcastReceiver(){


    override fun onReceive(context: Context?, intent: Intent?) {

        val i=Intent(context, AlarmClockActivity::class.java)
        val tid=intent!!.getLongExtra("TIP",0)
        if(TipModule().getTip(context!!,tid)==null){
            return
        }else{
            i.putExtra("TIP",tid)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(i)
        }
    }


}