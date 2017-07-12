package win.whitelife.dailytips.datebase.migrations

import android.database.sqlite.SQLiteDatabase

/**
 * Created by wuzefeng on 2017/7/10.
 */
interface Migration{

    fun migrate(sqLiteDatabase: SQLiteDatabase?)

}