package win.whitelife.dailytips.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.text.format.DateFormat.is24HourFormat
import android.app.TimePickerDialog
import android.view.LayoutInflater
import win.whitelife.dailytips.R
import java.text.DateFormat
import java.util.*


/**
 * Created by wuzefeng on 2017/7/6.
 */
object DialogUtil{



    fun createTipHintTypeDialog(context:Context,hintTypes:Array<String>,click: DialogInterface.OnClickListener): Dialog {
        val builder= AlertDialog.Builder(context)
        builder.setNegativeButton("取消", click)
        builder.setTitle("选择提示类型")
        builder.setItems(hintTypes,click)
        return builder.create()
    }

    fun createTipDeleteDialog(context:Context,click: DialogInterface.OnClickListener): Dialog {
        val builder= AlertDialog.Builder(context)
        builder.setNegativeButton("取消", click)
        builder.setPositiveButton("确定", click)
        builder.setTitle("是否确认删除该提醒")
        return builder.create()
    }


    fun createTipHintTimeDialog(context:Context,click: TimePickerDialog.OnTimeSetListener): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(context, click, hour, minute,
                true)
    }


    fun createTipHintDateDialog(context:Context,click: DatePickerDialog.OnDateSetListener): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(context, click, year, month,day)
    }



}