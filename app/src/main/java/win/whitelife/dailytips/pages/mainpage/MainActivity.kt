package win.whitelife.dailytips.pages.mainpage

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import win.whitelife.dailytips.R
import win.whitelife.dailytips.databinding.ActivityMainBinding
import win.whitelife.dailytips.service.DaemonService

/**
 * Created by wuzefeng on 2017/7/3.
 */
class MainActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var binding: ActivityMainBinding?=null

    private var title:ObservableField<String> = ObservableField()

    private var mainFragment: MainFragment ?=null

    private var settingFragment: SettingFragment ?=null

    private var tipsFragment: TipsFragment ?=null


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_main->{
                if(mainFragment==null){
                    mainFragment= MainFragment()
                    addFragment(mainFragment)
                }
                switchFragment(mainFragment)
            }

            R.id.navigation_tips->{
                if(tipsFragment==null){
                    tipsFragment= TipsFragment()
                    addFragment(tipsFragment)
                }
                switchFragment(tipsFragment)
            }
            R.id.navigation_setting->{
                if(settingFragment==null){
                    settingFragment= SettingFragment()
                    addFragment(settingFragment)
                }
                switchFragment(settingFragment)
            }
        }
        title.set(item.title.toString())
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        title.set("首页")
        binding!!.title=title
        binding!!.bnMain.setOnNavigationItemSelectedListener(this)
        mainFragment=MainFragment()
        addFragment(mainFragment)
        currentFragment=mainFragment
        startService(Intent(this, DaemonService::class.java))
    }


    private var ft: FragmentTransaction?=null


    private var currentFragment: Fragment?=null

    fun switchFragment(fragment: Fragment?){
        ft= supportFragmentManager.beginTransaction();
        if(currentFragment!=null){
            ft!!.hide(currentFragment)
        }
        currentFragment = fragment
        ft!!.show(fragment)
        ft!!.commitAllowingStateLoss()
    }

    fun addFragment(fragment: Fragment?){
        ft=supportFragmentManager.beginTransaction()
        if(ft!!.isEmpty){
            ft!!.add(R.id.fl_main,fragment)
        }else{
            ft!!.replace(R.id.fl_main,fragment)
        }
        ft!!.commit()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(mainFragment!=null)mainFragment!!.updateData()
        if(tipsFragment!=null)tipsFragment!!.updateData()
    }

}