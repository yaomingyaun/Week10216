package com.bw.ymy.zy1.b;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = false)
    private  long id;
    private String commodityName;
    private String masterPic;
    private int price;
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 2050656278)
    public User(long id, String commodityName, String masterPic, int price) {
        this.id = id;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
    }
    @Generated(hash = 586692638)
    public User() {
    }


}
