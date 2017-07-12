package win.whitelife.dailytips.pages.edittippage

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
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.bean.hintTypes
import win.whitelife.dailytips.databinding.ActivityEditTipBinding
import win.whitelife.dailytips.pages.createtippage.EditTipContract
import win.whitelife.dailytips.pages.createtippage.EditTipPresent
import win.whitelife.dailytips.util.DialogUtil
import win.whitelife.dailytips.util.SnakeUtil
import win.whitelife.dailytips.widget.CreateTipAddTimeDialog
import win.whitelife.dailytips.widget.CreateTipContentView
import win.whitelife.dailytips.widget.InputUtil

/**
 * Created by wuzefeng on 2017/7/11.
 */
class EditTipActivity: BaseActivity(), View.OnClickListener, DialogInterface.OnClickListener,
        TimePickerDialog.OnTimeSetListener, CompoundButton.OnCheckedChangeListener,EditTipContract.View,
        DatePickerDialog.OnDateSetListener{


    var hintType=-1

    private var type=0

    private var binding: ActivityEditTipBinding?=null

    private var hintTypeDialog: Dialog?=null

    private var hintTimeDialog: Dialog?=null

    private var dateDialog: CreateTipAddTimeDialog?=null

    private var hintTimes:ArrayList<String>?=ArrayList()

    private var present: EditTipContract.Present?=null

    private var contentView: CreateTipContentView?=null

    private var hintTime:String?=null

    private var tip: Tip?=null

    private var tipDeleteDialog: Dialog?=null

    private var dayDialog:Dialog?=null

    override fun findViews() {
        binding= DataBindingUtil.setContentView(this, R.layout.activity_edit_tip)
        setFinishView(binding!!.layoutToolBar.ivToolBarBack)
        binding!!.click=this
        hintTypeDialog= DialogUtil.createTipHintTypeDialog(this,hintTypes,this)
        hintTimeDialog= DialogUtil.createTipHintTimeDialog(this,this)
        dateDialog= CreateTipAddTimeDialog(this,binding!!.tvHintTime)
        contentView=findViewById(R.id.ct_content)
        setFinishView(binding!!.layoutToolBar.ivToolBarBack)
        val button=findViewById<RadioButton>(R.id.rb_1)
        button.setOnCheckedChangeListener(this)
        button.isChecked = true
        dayDialog=DialogUtil.createTipHintDateDialog(this,this)
        tipDeleteDialog=DialogUtil.createTipDeleteDialog(this, DialogInterface.OnClickListener { p0, p1 ->
            if(p1==-1){
                present!!.deleteTip(tip!!)
            }
        })
    }

    override fun init() {
        present= EditTipPresent(this,binding!!.root,intent.getLongExtra("TIP",0))
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.tv_hint_type->hintTypeDialog!!.show()
            R.id.tv_hint_time->{
                if(hintType==-1) SnakeUtil.show(binding!!.tvHintTime,"请选择类型")else showHintTimeDialog()
            }
            R.id.tv_tool_bar_save->saveTip()
            R.id.tv_delete->tipDeleteDialog!!.show()
        }
    }

    private fun saveTip() {
        InputUtil.hideInput(binding!!.etTitle,this)
        val title=binding!!.etTitle.text.toString()
        val hintOtherTime=if(type==6) dateDialog!!.getHintOtherTime()  else null
        present!!.saveTip(title,type,hintType,hintTime,hintOtherTime,contentView!!.getHintOtherTime(),tip!!)

    }

    override fun onStart() {
        super.onStart()
        present!!.onStart()
    }

    override fun onClick(p0: DialogInterface?, p1: Int) {
        if(p1!=-1&&p1!=-2){
            hintType=p1
            binding!!.tvHintType.text= hintTypes[p1]
            hintTime=null
            binding!!.tvHintTime.text=null
        }
        if(p1==0){
            binding!!.llHintTime.visibility=View.GONE
        }else{
            binding!!.llHintTime.visibility=View.VISIBLE
            hintTimes!!.clear()
        }
        hintTypeDialog!!.dismiss()
    }

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

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val month=if((p2+1)<10)"0"+(p2+1) else ""+(p2+1)
        val day=if(p3<10)"0"+p3 else ""+p3
        hintDate= "$p1.$month.$day"
        hintTimeDialog!!.show()
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        type=if(p1)0 else 1
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

    override fun getTipCallBack(tip: Tip?) {
        if(tip==null)return
        this.tip=tip
        binding!!.etTitle.setText(tip!!.title)
        binding!!.rb1.isChecked=tip.type==0
        binding!!.rb2.isChecked=tip.type!=0
        binding!!.tvHintType.text = hintTypes[tip.hintType]
        type=tip.type
        hintType=tip.hintType
        hintTime=tip.hintTime
        if(tip.hintType==6){
            dateDialog!!.setHintOtherTime(tip.hintOtherTimes!!)
            binding!!.tvHintTime.text= tip.tipContent!![0]
        }else if(tip.hintType==0){
            binding!!.llHintTime.visibility=View.GONE
        }else{
            binding!!.tvHintTime.text=tip.hintTime
        }
        binding!!.ctContent.setHintContent(tip.tipContent!!)
    }


}