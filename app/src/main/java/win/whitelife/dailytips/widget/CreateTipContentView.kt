package win.whitelife.dailytips.widget

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import win.whitelife.dailytips.R
import win.whitelife.dailytips.util.SnakeUtil
import java.util.ArrayList

/**
 * Created by wuzefeng on 2017/7/7.
 */
class CreateTipContentView(context: Context, attrs: AttributeSet?,defStyleAtt: Int):LinearLayout(context, attrs,defStyleAtt) ,View.OnClickListener {

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.fl_add_content->addItemView(null)
            R.id.iv_add_content->
                if(this.childCount>2)this.removeView(v.parent as View)else SnakeUtil.show(this,"至少需要一条内容")
        }
    }
    init {
        this.orientation= VERTICAL
        addAddView()
        addItemView(null)
    }

    constructor(context: Context, attrs: AttributeSet): this(context,attrs,0)

    constructor(context: Context): this(context,null,0)

    fun addItemView(content: String?){
        var item=LayoutInflater.from(context).inflate(R.layout.item_create_tip_content,this,false)
        addView(item,this.childCount-1)
        item.findViewById<ImageView>(R.id.iv_add_content).setOnClickListener(this)
        item.findViewById<EditText>(R.id.et_content).setText(content)
    }

    fun addAddView(){
        var item=LayoutInflater.from(context).inflate(R.layout.item_add_tip_content,this,false)
        item.setOnClickListener(this)
        addView(item)
    }

    fun getHintOtherTime(): List<String>{
        val listTime = ArrayList<String>()
        (0..this.childCount-1)
                .map { this.getChildAt(it).findViewById<TextView>(R.id.et_content) }
                .filter { it !=null && !TextUtils.isEmpty(it.text) }
                .forEach { listTime.add(it.text.toString()) }
        return listTime
    }

    fun setHintContent(contents: List<String>){
        removeAllViews()
        addAddView()
        for(content in contents){
            addItemView(content)
        }
    }

}