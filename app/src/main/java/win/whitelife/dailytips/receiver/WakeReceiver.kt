package win.whitelife.dailytips.receiver

import android.app.Notification
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import win.whitelife.dailytips.service.DaemonService

/**
 * Created by wuzefeng on 2017/7/12.
 */
class WakeReceiver: BroadcastReceiver(){

    override fun onReceive(p0: Context?, p1: Intent?) {
        if("win.whitelife.dailytip.wake".equals(p1!!.action)){
            p0!!.startService(Intent(p0,WakeInnerService::class.java))
        }
    }



    class WakeInnerService: Service(){
        val GRAY_SERVICE_ID=1001

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            //发送一个不显示的通知
            if(Build.VERSION.SDK_INT<18){
                startForeground(GRAY_SERVICE_ID,Notification())
            }else{
                //开启两个，关闭一个
                startService(Intent(this, DaemonService.DaemonInnerService::class.java))
                startForeground(GRAY_SERVICE_ID,Notification())
            }


            return super.onStartCommand(intent, flags, startId)
        }



        override fun onBind(p0: Intent?): IBinder?{
            return null
        }

    }


    class WakeGreyService:Service(){

        val GRAY_SERVICE_ID=1001

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            startForeground(GRAY_SERVICE_ID, Notification())
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }

        override fun onBind(p0: Intent?): IBinder? {
            return null
        }

    }



}