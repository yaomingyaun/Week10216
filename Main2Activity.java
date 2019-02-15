package com.bw.ymy.zy1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.ymy.zy1.bean.BannerBean;
import com.bw.ymy.zy1.presenter.IPresenterlpl;
import com.bw.ymy.zy1.view.IView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements IView {



    private String pid;
    private Banner banner;
    IPresenterlpl iPresenterlpl;
    private TextView tit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    banner=findViewById(R.id.banner);
    tit=findViewById(R.id.tit);
    iPresenterlpl=new IPresenterlpl(this);
        Intent intent=getIntent();
        pid=intent.getStringExtra("pid");

        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {


                 Uri uri=Uri.parse((String) path);
                 imageView.setImageURI(uri);


            }

            @Override
            public ImageView createImageView(Context context) {

            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
               GenericDraweeHierarchyBuilder draweeHierarchyBuilder=new GenericDraweeHierarchyBuilder(getResources());
                return simpleDraweeView;
            }
        });
        String u="http://172.17.8.100/small/commodity/v1/findCommodityDetailsById?commodityId="+pid+"";
        iPresenterlpl.get(u,BannerBean.class);
    }

    @Override
    public void onSuccess(Object data) {

        if(data instanceof BannerBean)
        {
            BannerBean bannerBean= (BannerBean) data;
            List<String> list=new ArrayList<>();
            String[]  split=bannerBean.getResult().getPicture().split("\\,");
            for (int i=0;i<split.length;i++)
            {
                list.add(split[i]);
            }
            banner.setImages(list);
            banner.start();

            tit.setText(bannerBean.getResult().getCategoryName());
        }
    }
}
