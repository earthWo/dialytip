package win.whitelife.dailytips.bean;

import com.google.gson.Gson;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import java.util.List;

/**
 * Created by wuzefeng on 2017/7/10.
 */
@Entity
public class TipBean {
    @Id(autoincrement = true)
    private Long id;

    private String title;

    private long createTime;

    private long updateTime;

    private int type;

    private String hintTime;

    private int hintType;

    private String hintOtherTimes;

    private String tipContent;





    public TipBean(Tip tip) {
        if(tip.getId()!=null)this.id =tip.getId();
        this.title = tip.getTitle();
        this.createTime = tip.getCreateTime();
        this.updateTime = tip.getUpdateTime();
        this.type = tip.getType();
        this.hintTime = tip.getHintTime();
        this.hintType = tip.getHintType();
        this.hintOtherTimes =  new Gson().toJson(tip.getHintOtherTimes());
        this.tipContent = new Gson().toJson(tip.getTipContent());
    }




    @Generated(hash = 2141424574)
    public TipBean(Long id, String title, long createTime, long updateTime, int type, String hintTime, int hintType, String hintOtherTimes, String tipContent) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
        this.hintTime = hintTime;
        this.hintType = hintType;
        this.hintOtherTimes = hintOtherTimes;
        this.tipContent = tipContent;
    }




    @Generated(hash = 573643247)
    public TipBean() {
    }




    public Long getId() {
        return this.id;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHintTime() {
        return this.hintTime;
    }

    public void setHintTime(String hintTime) {
        this.hintTime = hintTime;
    }

    public int getHintType() {
        return this.hintType;
    }

    public void setHintType(int hintType) {
        this.hintType = hintType;
    }


    public String getTipContent() {
        return this.tipContent;
    }

    public void setTipContent(String tipContent) {
        this.tipContent = tipContent;
    }

    public String getHintOtherTimes() {
        return this.hintOtherTimes;
    }

    public void setHintOtherTimes(String hintOtherTimes) {
        this.hintOtherTimes = hintOtherTimes;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Tip conversionTip(){
        Gson gson=new Gson();

        Tip tip=new Tip(id,title,createTime,updateTime,type,hintTime,hintType,gson.fromJson(hintOtherTimes,List.class), gson.fromJson(tipContent, List.class));
        return tip;
    }


}
