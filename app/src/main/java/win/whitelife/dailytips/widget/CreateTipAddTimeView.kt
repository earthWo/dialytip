package win.whitelife.dailytips.widget

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import win.whitelife.dailytips.R
import win.whitelife.dailytips.util.DialogUtil
import win.whitelife.dailytips.util.SnakeUtil
import win.whitelife.dailytips.util.TimeUtil
import java.util.ArrayList

/**
 * Created by wuzefeng on 2017/7/10.
 */
class CreateTipAddTimeView(context: Context, attrs: AttributeSet?, defStyleAtt: Int): LinearLayout(context, attrs,defStyleAtt) , View.OnClickListener
        , TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    private var timeDialog: Dialog?=null

    private var dateDialog:Dialog?=null

    private var selectText: TextView?=null

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_set_time->{
                dateDialog!!.show()
                selectText=v as TextView
            }
            R.id.fl_add_time->addItemView(null)
            R.id.iv_remove_time->
                if(this.childCount>2)this.removeView(v.parent as View)else SnakeUtil.show(this,"至少需要一个时间")
        }
    }

    init {
        this.orientation= VERTICAL
        addAddView()
        addItemView(null)
        timeDialog= DialogUtil.createTipHintTimeDialog(context,this)
        dateDialog=DialogUtil.createTipHintDateDialog(context,this)
    }

    constructor(context: Context, attrs: AttributeSet): this(context,attrs,0)

    constructor(context: Context): this(context,null,0)

    fun addItemView(content: String?){
        var item= LayoutInflater.from(context).inflate(R.layout.item_create_tip_time,this,false)
        addView(item,this.childCount-1)
        item.findViewById<ImageView>(R.id.iv_remove_time).setOnClickListener(this)
        item.findViewById<TextView>(R.id.tv_set_time).setOnClickListener(this)
        item.findViewById<TextView>(R.id.tv_set_time).text=content
    }

    fun addAddView(){
        var item= LayoutInflater.from(context).inflate(R.layout.item_add_tip_time,this,false)
        item.setOnClickListener(this)
        addView(item)
    }

    private var hintTime:String?=null

    private var hintDate:String?=null


    override fun onTimeSet(p: TimePicker?, hour: Int, minute: Int) {
        val mTime=if(minute<10) "0"+minute else minute
        val hTime=if(hour<10) "0"+hour else hour
        hintTime=" $hTime:$mTime"
        selectText!!.text = hintDate+hintTime
    }


    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        hintDate=""+p1+"年"+(p2+1)+"月"+p3+"日"
        timeDialog!!.show()
    }

    fun getFirstTime():String?{
        (0..this.childCount-1)
                .map { this.getChildAt(it).findViewById<TextView>(R.id.tv_set_time) }
                .filter { it !=null && it.text!=null }
                .forEach { return it.text.toString() }
        return null
    }

    fun getHintOtherTime(): List<Long>{
        val listTime = ArrayList<Long>()
        (0..this.childCount-1)
                .map { this.getChildAt(it).findViewById<TextView>(R.id.tv_set_time) }
                .filter { it !=null && it.text!=null }
                .forEach { listTime.add( TimeUtil.strToDateLong(it.text.toString(),"yyyy年MM月ss日 HH:mm")) }
        return listTime
    }

    fun setHintOtherTime(times: List<Long>){
        for(time in times){
            addItemView(TimeUtil.longToDateStr(time,"yyyy年MM月ss日 HH:mm"))
        }
    }




}