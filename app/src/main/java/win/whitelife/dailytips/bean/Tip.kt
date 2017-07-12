package win.whitelife.dailytips.bean

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id

/**
 * Created by wuzefeng on 2017/7/4.
 */


/**
 * id：编号
 * title:标题
 * createTime:创建时间
 * updateTime:更新时间
 * type:tip类型（0：待办事项，1：待解决事项）
 * hintTime:提醒时间
 * hintType:提醒类型（0:不提醒，1：每天提醒，2：每周提醒，3：每月提醒，4：工作日提醒，5：休息日提醒，6：特定时间提醒）
 * hintOtherTimes：特定时间
 * tipContent：提醒内容
 */
data class Tip(var id: Long?, var title: String?=null, var createTime: Long,
               var updateTime: Long, var type: Int, var hintTime: String?=null,
               var hintType: Int, var hintOtherTimes:List<Long>?=null, var tipContent: List<String>?=null)

var hintTypes=arrayOf("不提醒","每天提醒","每周提醒","每月提醒","工作日提醒","休息日提醒","特定时间提醒")
