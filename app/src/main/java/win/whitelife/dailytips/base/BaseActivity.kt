package win.whitelife.dailytips.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by wuzefeng on 2017/7/6.
 */

abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViews()
        init()
    }

    abstract fun findViews()

    abstract fun init()

    fun setFinishView(view: View){
        view.setOnClickListener { view ->finish()  }
    }


}