package win.whitelife.dailytips.module

import android.content.Context
import win.whitelife.dailytips.bean.Tip
import win.whitelife.dailytips.bean.TipBean
import win.whitelife.dailytips.bean.TipBeanDao
import win.whitelife.dailytips.datebase.DbManager

/**
 * Created by wuzefeng on 2017/7/10.
 */
class TipModule{

    fun createTip(title: String?=null, type: Int, hintTime: String?=null,
                   hintType: Int, hintOtherTimes:List<Long>?=null, tipContent: List<String>?=null): Tip {
        val createTime=System.currentTimeMillis()
        val updateTime=System.currentTimeMillis()
        var tip: Tip=Tip(null,title,createTime,updateTime,type,hintTime,hintType,hintOtherTimes,tipContent)
        return tip
    }


    fun editTip(title: String?=null, type: Int, hintTime: String?=null,
                  hintType: Int, hintOtherTimes:List<Long>?=null, tipContent: List<String>?=null,tip: Tip): Tip {
        tip.title=title
        tip.type=type
        tip.hintTime=hintTime
        tip.hintType=hintType
        tip.hintOtherTimes=hintOtherTimes
        tip.tipContent=tipContent
        tip.updateTime=System.currentTimeMillis()
        return tip
    }



    fun saveTip(context: Context,tip: Tip){
        val dao: TipBeanDao=DbManager.getInstance(context)!!.getDaoSession()!!.tipBeanDao
        dao.insertOrReplace(TipBean(tip))
    }

    fun deleteTip(context: Context,tip: Tip){
        val dao: TipBeanDao=DbManager.getInstance(context)!!.getDaoSession()!!.tipBeanDao
        dao.delete(TipBean(tip))
    }

    fun getTip(context: Context,id: Long):Tip?{
        val dao: TipBeanDao=DbManager.getInstance(context)!!.getDaoSession()!!.tipBeanDao
        var tip=dao.load(id)
        return if(tip!=null)tip.conversionTip() else null
    }

    fun getAllTips(context: Context):ArrayList<Tip>?{
        val dao: TipBeanDao=DbManager.getInstance(context)!!.getDaoSession()!!.tipBeanDao
        val list=dao.queryBuilder().orderDesc(TipBeanDao.Properties.CreateTime).list()
        val tips:ArrayList<Tip>?= ArrayList()
        for(tipBean in list){
            tips!!.add(tipBean.conversionTip())
        }
        return tips
    }


}