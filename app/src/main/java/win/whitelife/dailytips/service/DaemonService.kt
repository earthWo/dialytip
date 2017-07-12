package win.whitelife.dailytips.service

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import com.coolerfall.daemon.Daemon
import win.whitelife.dailytips.module.TipModule
import win.whitelife.dailytips.receiver.AlarmClockReceiver
import win.whitelife.dailytips.util.TimeUtil

/**
 * Created by wuzefeng on 2017/7/11.
 */
class DaemonService: Service() {

    val GRAY_SERVICE_ID = 1001

    val REQUEST_CODE = 1002

    override fun onCreate() {
        super.onCreate()
        grayGuard()
        //使用第三方方法唤醒（适用于4.4以下设备）
        Daemon.run(this, DaemonService::class.java, Daemon.INTERVAL_ONE_MINUTE)
    }

    /**
     * 5.0以上设备灰色保活
     */
    private fun grayGuard() {

        //发送一个不显示的通知
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(GRAY_SERVICE_ID, Notification())
        } else {
            //开启两个，关闭一个
            startService(Intent(this, DaemonInnerService::class.java))
            startForeground(GRAY_SERVICE_ID, Notification())
        }

        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent()
        intent.action = "win.whitelife.dailytip.wake"

        val pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startAlarmClock()
        return super.onStartCommand(intent, START_FLAG_RETRY, startId)
    }


    /**
     * 开启闹钟
     */
    fun startAlarmClock() {
        val listTip = TipModule().getAllTips(this)
        for (tip in listTip!!) {
            //需要提示

            when (tip.hintType) {
                0 -> {
                }//不需要提示
                1 -> {
                    sendAlarmClock(TimeUtil.getNextTimeDay(tip.hintTime!!), tip.id!!)
                }//每天提示
                2 -> {
                    val longTime = TimeUtil.strToDateLong(tip.hintTime, "yyyy.MM.dd HH:mm")
                    //如果初始的时间大于当前时间
                    if (longTime > System.currentTimeMillis()) {
                        sendAlarmClock(longTime, tip.id!!)
                    } else {
                        sendAlarmClock(TimeUtil.getNextTimeWeek(longTime), tip.id!!)
                    }
                }//每周提示
                3 -> {
                    val longTime = TimeUtil.strToDateLong(tip.hintTime, "yyyy.MM.dd HH:mm")
                    //如果初始的时间大于当前时间
                    if (longTime > System.currentTimeMillis()) {
                        sendAlarmClock(longTime, tip.id!!)
                    } else {
                        sendAlarmClock(TimeUtil.getNextTimeMonth(longTime), tip.id!!)
                    }
                }//每月提示
                4 -> {
                    sendAlarmClock(TimeUtil.getNextTimeWeekday(tip.hintTime!!), tip.id!!)
                }//工作日提示
                5 -> {
                    sendAlarmClock(TimeUtil.getNextTimeWeekend(tip.hintTime!!), tip.id!!)
                }//休息日提示
                6 -> {
                    sendAlarmClock(TimeUtil.getNextTimeSeveralTime(tip.hintOtherTimes!!), tip.id!!)
                }//特定日期提示
            }
        }
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    /**
     * 发送闹钟通知
     */
    fun sendAlarmClock(time: Long, tipId: Long) {

        val intent = Intent(this, AlarmClockReceiver::class.java)
        intent.putExtra("TIP", tipId)

        val pendingIntent = PendingIntent.getBroadcast(this, tipId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        }

    }


    class DaemonInnerService : Service() {

        val GRAY_SERVICE_ID = 1001

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            startForeground(GRAY_SERVICE_ID, Notification())
            stopForeground(true)
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }

        override fun onBind(p0: Intent?): IBinder? {
            return null
        }
    }
}