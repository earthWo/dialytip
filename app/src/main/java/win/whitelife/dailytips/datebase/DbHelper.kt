package win.whitelife.dailytips.datebase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import win.whitelife.dailytips.bean.DaoMaster.OpenHelper
import win.whitelife.dailytips.datebase.migrations.Migration
import java.util.*


/**
 * Created by wuzefeng on 2017/7/10.
 */
class DbHelper(context: Context,name: String,factory: SQLiteDatabase.CursorFactory?): OpenHelper(context,name,factory) {

    init {

    }

    private val ALL_MIGRATIONS = TreeMap<Int,Migration>()


    override fun onCreate(db: SQLiteDatabase?) {
        super.onCreate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        val migrations=ALL_MIGRATIONS.subMap(oldVersion,newVersion)
        executeMigrations(db,migrations.keys)
    }


    fun executeMigrations(paramSQLiteDatabase: SQLiteDatabase?,migrationVersions: Set<Int>?){
        if(migrationVersions!=null){
            for (version in migrationVersions) {
                ALL_MIGRATIONS[version]!!.migrate(paramSQLiteDatabase)
            }
        }
    }

}