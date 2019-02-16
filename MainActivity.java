package com.bw.ymy.zy1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.bw.ymy.zy1.Adapter;
import com.bw.ymy.zy1.R;
import com.bw.ymy.zy1.b.User;
import com.bw.ymy.zy1.bean.Home;
import com.bw.ymy.zy1.presenter.IPresenterlpl;
import com.bw.ymy.zy1.refit.Retfit;
import com.bw.ymy.zy1.view.IView;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements IView {




    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.goods)
    EditText goods;

    @BindView(R.id.but1)
    Button but1;


    UserDao userDao;
    //数据库  看上图
    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase database;
    private  DaoMaster master;
    private DaoSession session;
    private IPresenterlpl iPresenterlpl;
    User user;


    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定
        ButterKnife.bind(this);


        iPresenterlpl=new IPresenterlpl(this);
        //数据库
        setGreendao();
        //获取布局
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new Adapter(this);
        recyclerView.setAdapter(adapter);
        //网络
            lodata();
    }
    //数据库
  /*  public  void  setDatabase()
    {
        helper=new DaoMaster.DevOpenHelper(this,"sport-db",null);
        database=helper.getWritableDatabase();
        master=new DaoMaster(database);
        session=master.newSession();
        userDao=session.getUserDao();
    }*/
    private void lodata() {
            //判断是否网络
        boolean b=Retfit.newwork(this);
        if(b)
        {
            iPresenterlpl.get("commodity/v1/findCommodityByKeyword?keyword=卫衣&page=1&count=10",Home.class);
        }else
        {
            //不是网络  数据库显示
            List<Home.ResultBean> beans=new ArrayList<>();

            List<User> list1=userDao.queryBuilder().list();
            for (int i=0;i<list1.size();i++)
            {
                Home.ResultBean da=new Home.ResultBean();
                da.setCommodityName(list1.get(i).getCommodityName());
                da.setMasterPic(list1.get(i).getMasterPic());
                da.setPrice(list1.get(i).getPrice());
                da.setCommodityId((int) list1.get(i).getId());
                beans.add(da);
                  //第二种
               /* User user=list1.get(i);
                Home.ResultBean d=new Home.ResultBean(user.setMasterPic());*/
            }
            //显示数据库
            adapter.setlist(beans);
            adapter.notifyDataSetChanged();
        }

    }
//点击搜索
    @OnClick(R.id.but1)
    public   void  onclicks(View v)
    {
        switch (v.getId())
        {


            case  R.id.but1:

                String name= goods.getText().toString();
                if(name.length()==0)
                {
                    Toast.makeText(this, "请输入搜索商品", Toast.LENGTH_SHORT).show();
                }else
                {
                    String url="commodity/v1/findCommodityByKeyword?keyword=%s&page=1&count=10";
                    iPresenterlpl.get(String.format(url,name),Home.class);
               }
                break;

        }
    }

    //创建数据库
public  void  setGreendao()
{
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "user");
    SQLiteDatabase database = helper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(database);
    DaoSession daoSession = daoMaster.newSession();
    userDao = daoSession.getUserDao();
}

    @Override
    public void onSuccess(Object data) {

        if(data instanceof Home)
        {
            Home bean= (Home) data;

            adapter.setlist(bean.getResult());
            //存入数据库
            List<Home.ResultBean> beanList=bean.getResult();
            for (int i=0;i<beanList.size();i++)
            {
               User user=new User();
               user.setCommodityName(beanList.get(i).getCommodityName());
               user.setPrice(beanList.get(i).getPrice());
               user.setMasterPic(beanList.get(i).getMasterPic());
               user.setId(beanList.get(i).getCommodityId());

               /*第二种存入
                Home.ResultBean data1=bean.getResult().get(i);
                User user1=new User(data1.getCommodityId())*/
             //  userdao
                userDao.insertOrReplace(user);
            }
        }
    }
}
