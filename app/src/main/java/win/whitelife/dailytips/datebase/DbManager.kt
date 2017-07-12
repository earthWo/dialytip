package win.whitelife.dailytips.datebase

import android.content.Context
import org.greenrobot.greendao.AbstractDaoMaster
import org.greenrobot.greendao.AbstractDaoSession
import win.whitelife.dailytips.bean.DaoMaster
import win.whitelife.dailytips.bean.DaoSession

/**
 * Created by wuzefeng on 2017/7/10.
 */
class DbManager private constructor(context: Context){

    private val dbName="tip_db"

    private var openHelp: DbHelper?=null

    private var daoSession: DaoSession?=null

    init {
        openHelp= DbHelper(context,dbName,null)
        daoSession=DaoMaster(openHelp!!.writableDb).newSession()
    }

    companion object {

        private var daoManager: DbManager?=null

        fun getInstance(context: Context): DbManager?{
            if(daoManager==null){
                daoManager=DbManager(context)
            }
            return daoManager
        }
    }

    fun getDaoSession(): DaoSession?{
        return daoSession
    }

}