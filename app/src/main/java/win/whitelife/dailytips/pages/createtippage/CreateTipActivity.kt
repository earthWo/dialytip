package win.whitelife.dailytips.pages.createtippage

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.view.View
import android.widget.CompoundButton
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.TimePicker
import win.whitelife.dailytips.R
import win.whitelife.dailytips.base.BaseActivity
import win.whitelife.dailytips.bean.hintTypes
import win.whitelife.dailytips.databinding.ActivityCreateTipBinding
import win.whitelife.dailytips.util.DialogUtil
import win.whitelife.dailytips.util.SnakeUtil
import win.whitelife.dailytips.widget.CreateTipAddTimeDialog
import win.whitelife.dailytips.widget.CreateTipContentView
import win.whitelife.dailytips.widget.InputUtil

/**
 * Created by wuzefeng on 2017/7/4.
 */
class CreateTipActivity: BaseActivity(),View.OnClickListener,DialogInterface.OnClickListener,
        TimePickerDialog.OnTimeSetListener,CompoundButton.OnCheckedChangeListener,
        DatePickerDialog.OnDateSetListener{


    var hintType=-1

    private var type=0

    private var binding: ActivityCreateTipBinding?=null

    private var hintTypeDialog:Dialog?=null

    private var hintTimeDialog:Dialog?=null

    private var dateDialog:CreateTipAddTimeDialog?=null

    private var present: CreateTipContract.Present?=null

    private var contentView: CreateTipContentView?=null

    private var hintTime:String?=null

    private var dayDialog:Dialog?=null

    private var hintDate: String?=null

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        val mTime=if(minute<10) "0"+minute else minute
        val hTime=if(hour<10) "0"+hour else hour
        hintTime=""+hTime+":"+mTime
        if(hintType==2||hintType==3){
            hintTime=hintDate+" "+hintTime
        }
        binding!!.tvHintTime.text=hintTime
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.tv_hint_type->hintTypeDialog!!.show()
            R.id.tv_hint_time->{
                if(hintType==-1)SnakeUtil.show(binding!!.tvHintTime,"请选择类型")else showHintTimeDialog()
            }
            R.id.tv_tool_bar_save->saveTip()
        }
    }

    private fun saveTip() {
        InputUtil.hideInput(binding!!.etTitle,this)
        val title=binding!!.etTitle.text.toString()
        val hintOtherTime=if(type==6) dateDialog!!.getHintOtherTime()  else null
        present!!.createNewTip(title,type,hintType,hintTime,hintOtherTime,contentView!!.getHintOtherTime())
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        type=if(p1)0 else 1
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        if(p1!=-1&&p1!=-2){
            hintType=p1
            binding!!.tvHintType.text=hintTypes[p1]
            hintTime=null
            binding!!.tvHintTime.text=null
        }
        if(p1==0){
            binding!!.llHintTime.visibility=View.GONE
        }else{
            binding!!.llHintTime.visibility=View.VISIBLE
        }
        hintTypeDialog!!.dismiss()
    }

    override fun init() {
        present=CreateTipPresent(this,binding!!.root)
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val month=if((p2+1)<10)"0"+(p2+1) else ""+(p2+1)
        val day=if(p3<10)"0"+p3 else ""+p3
        hintDate= "$p1.$month.$day"
        hintTimeDialog!!.show()
    }

    override fun findViews() {
        binding=DataBindingUtil.setContentView(this, R.layout.activity_create_tip)
        binding!!.click=this
        hintTypeDialog=DialogUtil.createTipHintTypeDialog(this,hintTypes,this)
        hintTimeDialog=DialogUtil.createTipHintTimeDialog(this,this)
        dateDialog= CreateTipAddTimeDialog(this,binding!!.tvHintTime)
        contentView=findViewById(R.id.ct_content)
        dayDialog=DialogUtil.createTipHintDateDialog(this,this)
        setFinishView(binding!!.layoutToolBar.ivToolBarBack)
        val button=findViewById<RadioButton>(R.id.rb_1)
        button.setOnCheckedChangeListener(this)
        button.isChecked = true
    }

    fun showHintTimeDialog(){
        if(hintType==1||hintType==4||hintType==5){
            hintTimeDialog!!.show()
        } else if(hintType==6){
            dateDialog!!.show()
        }else{
            dayDialog!!.show()
        }
    }

}