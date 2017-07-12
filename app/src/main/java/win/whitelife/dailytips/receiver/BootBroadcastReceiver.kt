package win.whitelife.dailytips.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import win.whitelife.dailytips.service.DaemonService

/**
 * 接收启动通知，开启闹钟通知
 * Created by wuzefeng on 2017/7/12.
 */
class BootBroadcastReceiver: BroadcastReceiver(){


    override fun onReceive(p0: Context?, p1: Intent?) {
        p0!!.startService(Intent(p0,DaemonService::class.java))
    }

}