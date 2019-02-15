package com.bw.ymy.zy1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;



import com.bw.ymy.zy1.Adapter;
import com.bw.ymy.zy1.R;
import com.bw.ymy.zy1.bean.Home;
import com.bw.ymy.zy1.presenter.IPresenterlpl;
import com.bw.ymy.zy1.view.IView;



public class MainActivity extends AppCompatActivity implements IView {


    private RecyclerView recyclerView;
    private IPresenterlpl iPresenterlpl;

    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.view);
        iPresenterlpl=new IPresenterlpl(this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
      gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);


        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new Adapter(this);
        recyclerView.setAdapter(adapter);

        iPresenterlpl.get("commodity/v1/findCommodityByKeyword?keyword=卫衣&page=1&count=10",Home.class);

    }

    @Override
    public void onSuccess(Object data) {

        if(data instanceof Home)
        {
            Home bean= (Home) data;

            adapter.setlist(bean.getResult());

        }

    }
}
