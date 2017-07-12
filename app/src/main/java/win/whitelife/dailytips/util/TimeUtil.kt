package win.whitelife.dailytips.util

import win.whitelife.dailytips.bean.Tip
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by wuzefeng on 2017/7/10.
 */
object TimeUtil{

    fun strToDateLong(strDate: String?,style: String): Long{
        if(strDate==null){
            return 0
        }
        val format=SimpleDateFormat(style)
        return format.parse(strDate).time
    }


    fun longToDateStr(longDate: Long?,style: String): String{
        if(longDate==null){
            return ""
        }
        val format=SimpleDateFormat(style)
        val date= Date(longDate)
        return format.format(date)
    }

    fun tipHintTime(tip: Tip):String{
        when(tip.hintType){
            0->return "不提醒"
            1->return "每天 "+tip.hintTime
            2->return "每周 "+tip.hintTime
            2->return "每月 "+tip.hintTime
            4->return "工作日 "+tip.hintTime
            5->return "休息日 "+tip.hintTime
            6->return longToDateStr(tip.hintOtherTimes?.get(0),"yyyy.MM.dd HH:mm")+"等"
        }

        return ""
    }


    fun getNextTimeDay(time: String): Long{

        val hour=Integer.valueOf(time.subSequence(0,time.indexOf(":")).toString())
        val minute=Integer.valueOf(time.subSequence(time.indexOf(":")+1,time.length).toString())

        //当前时间
        val todayAlarm=Calendar.getInstance()
        //设置今天要闹钟的时间
        todayAlarm.set(Calendar.HOUR_OF_DAY,hour)
        todayAlarm.set(Calendar.MINUTE,minute)
        //如果这个时间还没到
        if(todayAlarm.timeInMillis>=System.currentTimeMillis()){
            return todayAlarm.timeInMillis
        }else{
            //添加一天
            todayAlarm.add(Calendar.DATE,1)
            return todayAlarm.timeInMillis
        }

    }

    fun getNextTimeWeek(time: Long): Long{

        //设定的初始时间
        val calendar=Calendar.getInstance()
        calendar.timeInMillis=time

        //当前时间
        val weekAlarm=Calendar.getInstance()
        //设置本周要闹钟的时间
        weekAlarm.set(Calendar.DAY_OF_WEEK,calendar.get(Calendar.DAY_OF_WEEK))
        weekAlarm.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY))
        weekAlarm.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE))
        weekAlarm.set(Calendar.SECOND,0)
        //如果这个时间还没到
        if(weekAlarm.timeInMillis>=System.currentTimeMillis()){
            return weekAlarm.timeInMillis
        }else{
            //添加一天
            weekAlarm.add(Calendar.WEEK_OF_YEAR,1)
            return weekAlarm.timeInMillis
        }

    }

    fun getNextTimeMonth(time: Long): Long{

        //设定的初始时间
        val calendar=Calendar.getInstance()
        calendar.timeInMillis=time

        //当前时间
        val monthAlarm=Calendar.getInstance()
        //设置本月要闹钟的时间
        monthAlarm.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH))
        monthAlarm.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY))
        monthAlarm.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE))
        //如果这个时间还没到
        if(monthAlarm.timeInMillis>=System.currentTimeMillis()){
            return monthAlarm.timeInMillis
        }else{
            //添加一天
            monthAlarm.add(Calendar.MONTH,1)
            return monthAlarm.timeInMillis
        }

    }

    fun getNextTimeWeekday(time: String): Long{
        val hour=Integer.valueOf(time.subSequence(0,time.indexOf(":")).toString())
        val minute=Integer.valueOf(time.subSequence(time.indexOf(":")+1,time.length).toString())
        //设定的初始时间
        val weekdayAlarm=Calendar.getInstance()

        if(weekdayAlarm.get(Calendar.DAY_OF_WEEK) in 1..5){
            //设置当天要闹钟的时间
            weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
            weekdayAlarm.set(Calendar.MINUTE,minute)
            weekdayAlarm.set(Calendar.SECOND,0)
            //如果这个时间还没到
            if(weekdayAlarm.timeInMillis>=System.currentTimeMillis()){
                return weekdayAlarm.timeInMillis
            }else if(weekdayAlarm.get(Calendar.DAY_OF_WEEK)==5){//如果是周五
                //下周一
                weekdayAlarm.add(Calendar.WEEK_OF_YEAR,1)
                weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
                weekdayAlarm.set(Calendar.MINUTE,minute)
                weekdayAlarm.set(Calendar.SECOND,0)
                return weekdayAlarm.timeInMillis
            }else{
                //下一天
                weekdayAlarm.add(Calendar.DAY_OF_WEEK,1)
                weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
                weekdayAlarm.set(Calendar.MINUTE,minute)
                weekdayAlarm.set(Calendar.SECOND,0)
                return weekdayAlarm.timeInMillis
            }
        }else{
            //下周一
            weekdayAlarm.add(Calendar.WEEK_OF_YEAR,1)
            weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
            weekdayAlarm.set(Calendar.MINUTE,minute)
            weekdayAlarm.set(Calendar.SECOND,0)
            return weekdayAlarm.timeInMillis
        }
    }

    fun getNextTimeWeekend(time: String): Long{
        val hour=Integer.valueOf(time.subSequence(0,time.indexOf(":")).toString())
        val minute=Integer.valueOf(time.subSequence(time.indexOf(":")+1,time.length).toString())
        //设定的初始时间
        val weekdayAlarm=Calendar.getInstance()

        if(weekdayAlarm.get(Calendar.DAY_OF_WEEK)==0){
            //设置当天要闹钟的时间
            weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
            weekdayAlarm.set(Calendar.MINUTE,minute)
            weekdayAlarm.set(Calendar.SECOND,0)
            //如果这个时间还没到
            if(weekdayAlarm.timeInMillis>=System.currentTimeMillis()){
                return weekdayAlarm.timeInMillis
            }else{
                // 设置周六
                weekdayAlarm.set(Calendar.DAY_OF_WEEK,6)
                weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
                weekdayAlarm.set(Calendar.MINUTE,minute)
                weekdayAlarm.set(Calendar.SECOND,0)
                return weekdayAlarm.timeInMillis
            }
        }else if(weekdayAlarm.get(Calendar.DAY_OF_WEEK)==6){
            //设置当天要闹钟的时间
            weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
            weekdayAlarm.set(Calendar.MINUTE,minute)
            weekdayAlarm.set(Calendar.SECOND,0)
            //如果这个时间还没到
            if(weekdayAlarm.timeInMillis>=System.currentTimeMillis()){
                return weekdayAlarm.timeInMillis
            }else{
                // 设置下周日
                weekdayAlarm.add(Calendar.WEEK_OF_YEAR,1)
                weekdayAlarm.set(Calendar.WEEK_OF_YEAR,0)
                weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
                weekdayAlarm.set(Calendar.MINUTE,minute)
                weekdayAlarm.set(Calendar.SECOND,0)
                return weekdayAlarm.timeInMillis
            }
        }else{
            // 设置这周六
            weekdayAlarm.set(Calendar.WEEK_OF_YEAR,6)
            weekdayAlarm.set(Calendar.HOUR_OF_DAY,hour)
            weekdayAlarm.set(Calendar.MINUTE,minute)
            weekdayAlarm.set(Calendar.SECOND,0)
            return weekdayAlarm.timeInMillis
        }
    }


    fun getNextTimeSeveralTime(times: List<Long>):Long{
        val now=System.currentTimeMillis()
        var returnTime: Long=0
        times
                .asSequence()
                .filter { it >=now && (returnTime.toInt()==0|| it <returnTime) }
                .forEach { returnTime= it }

        return returnTime
    }
}